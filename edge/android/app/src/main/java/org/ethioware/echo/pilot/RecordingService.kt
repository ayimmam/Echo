package org.ethioware.echo.pilot

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ServiceInfo
import android.media.AudioDeviceInfo
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioRecord
import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.media.MediaRecorder
import android.os.IBinder
import android.os.PowerManager
import android.os.SystemClock
import android.os.StatFs
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import android.os.Handler
import android.os.Looper
import android.os.Build
import java.io.ByteArrayOutputStream
import java.util.concurrent.atomic.AtomicBoolean
import org.ethioware.echo.MainActivity

data class RecordingStatus(val running: Boolean = false, val seconds: Long = 0, val message: String = "", val lessonId: String? = null)
object RecordingMonitor {
    var state by mutableStateOf(RecordingStatus()); internal set
}

/** Recorder owns the microphone, not the Activity. It never starts itself after reboot/process death. */
class RecordingService : Service() {
    private val stop = AtomicBoolean(false)
    private val main = Handler(Looper.getMainLooper())
    private var worker: Thread? = null
    private var wake: PowerManager.WakeLock? = null
    override fun onBind(intent: Intent?): IBinder? = null
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == STOP) { stop.set(true); if (worker == null) stopSelf(); return START_NOT_STICKY }
        if (worker != null) return START_NOT_STICKY
        if (intent?.action != START || checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            stopSelf(); return START_NOT_STICKY
        }
        getSystemService(NotificationManager::class.java).createNotificationChannel(
            NotificationChannel(CHANNEL, "Classroom recording", NotificationManager.IMPORTANCE_LOW))
        try {
            if (Build.VERSION.SDK_INT >= 30) startForeground(3100, notification(), ServiceInfo.FOREGROUND_SERVICE_TYPE_MICROPHONE)
            else startForeground(3100, notification())
        }
        catch (_: Exception) {
            publish(RecordingStatus(message = "Android blocked microphone access. Open Echo and check permissions."))
            stopSelf(); return START_NOT_STICKY
        }
        stop.set(false)
        val external = intent.getBooleanExtra("external", true)
        val profile = runCatching { ClassProfile.parse(org.json.JSONObject(intent.getStringExtra("profile") ?: "")) }.getOrNull()
        if (profile == null || !profile.valid()) { stopSelf(); return START_NOT_STICKY }
        publish(RecordingStatus(true, message = "Checking microphone…"))
        worker = Thread { capture(profile, external) }.also { it.start() }
        return START_NOT_STICKY
    }
    private fun notification(): Notification {
        val open = PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        val end = PendingIntent.getService(this, 1, Intent(this, RecordingService::class.java).setAction(STOP), PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        return Notification.Builder(this, CHANNEL).setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .setContentTitle("Echo is recording").setContentText("Audio stays on this phone. Tap Stop when the lesson ends.")
            .setContentIntent(open).setOngoing(true).setOnlyAlertOnce(true)
            .addAction(Notification.Action.Builder(null, "Stop and save", end).build()).build()
    }
    private fun publish(value: RecordingStatus) { main.post { RecordingMonitor.state = value } }
    private fun capture(profile: ClassProfile, external: Boolean) {
        var recorder: AudioRecord? = null
        var encoder: MediaCodec? = null
        var store: PilotStore? = null
        var lesson: LocalLesson? = null
        var reason = ""
        try {
            store = PilotRuntime.store(this)
            check(StatFs(filesDir.path).availableBytes > 32 * 1024 * 1024) { "Free at least 32 MB before recording." }
            val devices = getSystemService(AudioManager::class.java).getDevices(AudioManager.GET_DEVICES_INPUTS)
            val device = devices.firstOrNull {
                if (external) it.type in setOf(AudioDeviceInfo.TYPE_WIRED_HEADSET, AudioDeviceInfo.TYPE_USB_HEADSET, AudioDeviceInfo.TYPE_USB_DEVICE)
                else it.type == AudioDeviceInfo.TYPE_BUILTIN_MIC
            } ?: error(if (external) "Connect a wired or USB microphone. Recording did not start." else "Phone microphone unavailable.")
            val minimum = AudioRecord.getMinBufferSize(RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT)
            check(minimum > 0) { "This phone does not support the recording format." }
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                throw SecurityException("Microphone permission was revoked.")
            }
            recorder = AudioRecord.Builder().setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION)
                .setAudioFormat(AudioFormat.Builder().setSampleRate(RATE).setChannelMask(AudioFormat.CHANNEL_IN_MONO)
                    .setEncoding(AudioFormat.ENCODING_PCM_16BIT).build())
                .setBufferSizeInBytes(maxOf(minimum * 4, 65_536)).build()
            check(recorder.state == AudioRecord.STATE_INITIALIZED && recorder.setPreferredDevice(device)) { "Could not select the microphone." }
            encoder = MediaCodec.createEncoderByType(MediaFormat.MIMETYPE_AUDIO_AAC)
            encoder.configure(MediaFormat.createAudioFormat(MediaFormat.MIMETYPE_AUDIO_AAC, RATE, 1).apply {
                setInteger(MediaFormat.KEY_AAC_PROFILE, MediaCodecInfo.CodecProfileLevel.AACObjectLC)
                setInteger(MediaFormat.KEY_BIT_RATE, 32_000)
                setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, 4096)
            }, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE)
            encoder.start()
            lesson = store.create(profile)
            wake = getSystemService(PowerManager::class.java).newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Echo:recording")
                .apply { acquire(2 * 60 * 60 * 1000L + 60_000) }
            recorder.startRecording()
            check(recorder.recordingState == AudioRecord.RECORDSTATE_RECORDING) { "Microphone did not start." }
            val start = SystemClock.elapsedRealtime()
            val pcm = ShortArray(1024)
            var totalSamples = 0L
            var chunkSamples = 0L
            var clipped = 0L
            var quiet = 0L
            val chunk = ByteArrayOutputStream()
            var frames = 0L
            var committedSamples = 0L
            var encodedSamples = 0L
            var lastProgress = -1L
            var lastInput = start
            val info = MediaCodec.BufferInfo()
            fun commit() {
                if (frames == 0L) return
                // AAC-LC has 1024 decoded samples/frame. Bound coverage by captured input.
                val coveredSamples = minOf(encodedSamples, totalSamples)
                val duration = (coveredSamples - committedSamples) * 1000 / RATE
                if (duration > 0) {
                    store.append(lesson.id, chunk.toByteArray(), duration, chunkSamples, clipped, quiet)
                    committedSamples = coveredSamples
                }
                chunk.reset(); frames = 0; chunkSamples = 0; clipped = 0; quiet = 0
            }
            fun drain(eos: Boolean = false) {
                val deadline = SystemClock.elapsedRealtime() + if (eos) 1500 else 20
                do {
                    val index = encoder.dequeueOutputBuffer(info, if (eos) 10_000 else 0)
                    if (index >= 0) {
                        if (info.size > 0 && info.flags and MediaCodec.BUFFER_FLAG_CODEC_CONFIG == 0) {
                            val data = encoder.getOutputBuffer(index)!!
                            data.position(info.offset); data.limit(info.offset + info.size)
                            val bytes = ByteArray(info.size); data.get(bytes)
                            chunk.write(adtsHeader(info.size)); chunk.write(bytes); frames++; encodedSamples += 1024
                        }
                        val ended = info.flags and MediaCodec.BUFFER_FLAG_END_OF_STREAM != 0
                        encoder.releaseOutputBuffer(index, false)
                        if (ended) break
                    } else if (!eos && index == MediaCodec.INFO_TRY_AGAIN_LATER) break
                } while (SystemClock.elapsedRealtime() < deadline)
            }
            while (!stop.get()) {
                val elapsed = SystemClock.elapsedRealtime() - start
                if (elapsed >= 2 * 60 * 60 * 1000L) { reason = "Two-hour recording limit reached."; break }
                if (elapsed / 1000 != lastProgress && StatFs(filesDir.path).availableBytes < 8 * 1024 * 1024) {
                    reason = "Storage is nearly full."; break
                }
                if (recorder.activeRecordingConfiguration?.isClientSilenced == true) { reason = "Android interrupted microphone access."; break }
                val route = recorder.routedDevice
                if ((route != null && route.id != device.id) || (route == null && elapsed > 3000)) {
                    reason = "Microphone route changed. Reconnect it before starting a new lesson."; break
                }
                val inputIndex = encoder.dequeueInputBuffer(10_000)
                if (inputIndex >= 0) {
                    val count = recorder.read(pcm, 0, pcm.size, AudioRecord.READ_NON_BLOCKING)
                    if (count < 0) error("Microphone stopped delivering audio.")
                    val input = encoder.getInputBuffer(inputIndex)!!
                    input.clear(); input.order(java.nio.ByteOrder.LITTLE_ENDIAN)
                    for (i in 0 until count) {
                        val sample = pcm[i].toInt()
                        input.putShort(pcm[i])
                        if (kotlin.math.abs(sample) >= 32700) clipped++
                        if (kotlin.math.abs(sample) < 32) quiet++
                    }
                    encoder.queueInputBuffer(inputIndex, 0, count * 2, totalSamples * 1_000_000 / RATE, 0)
                    totalSamples += count; chunkSamples += count
                    if (count > 0) lastInput = SystemClock.elapsedRealtime() else Thread.sleep(10)
                }
                drain()
                check(chunk.size() <= 256_000 && totalSamples - encodedSamples <= RATE * 3L) {
                    "Audio encoder could not keep up. Completed excerpts are saved."
                }
                if (SystemClock.elapsedRealtime() - lastInput > 3000) error("No audio arrived from the microphone.")
                if (chunkSamples >= RATE * 30L) commit()
                val seconds = elapsed / 1000
                if (seconds != lastProgress) {
                    lastProgress = seconds
                    publish(RecordingStatus(true, seconds, if (external) "External microphone · Screen may be locked" else "Phone microphone · Screen may be locked", lesson.id))
                }
            }
            recorder.stop()
            var endInput = encoder.dequeueInputBuffer(10_000)
            val finalDeadline = SystemClock.elapsedRealtime() + 1500
            while (endInput < 0 && SystemClock.elapsedRealtime() < finalDeadline) {
                drain()
                endInput = encoder.dequeueInputBuffer(10_000)
            }
            if (endInput >= 0) {
                encoder.queueInputBuffer(endInput, 0, 0, totalSamples * 1_000_000 / RATE, MediaCodec.BUFFER_FLAG_END_OF_STREAM)
                drain(true)
            } else if (reason.isEmpty()) reason = "The final audio frames could not be completed. Earlier excerpts are saved."
            commit()
        } catch (error: Exception) {
            reason = when (error) {
                is SecurityException -> "Microphone permission or private storage access was revoked."
                else -> error.message?.take(180) ?: "Recording stopped unexpectedly."
            }
        } finally {
            runCatching { recorder?.release() }; runCatching { encoder?.stop() }; runCatching { encoder?.release() }
            if (wake?.isHeld == true) wake?.release()
            lesson?.let { row ->
                if (reason.isEmpty() && runCatching { store?.lessons()?.find { it.id == row.id }?.capturedMs == 0L }.getOrDefault(false)) {
                    reason = "No completed audio was saved. Check the microphone and try again."
                }
                runCatching { store?.finish(row.id, reason) }.onFailure { reason = "Could not finalize the lesson. Completed chunks will be recovered on restart." }
            }
            publish(RecordingStatus(message = if (reason.isEmpty()) "Lesson saved on this phone." else reason, lessonId = lesson?.id))
            main.post { LocalAnalysis.schedule(this); stopForeground(STOP_FOREGROUND_REMOVE); stopSelf() }
        }
    }
    override fun onDestroy() { stop.set(true); super.onDestroy() }
    companion object {
        const val START = "org.ethioware.echo.RECORD"
        const val STOP = "org.ethioware.echo.STOP"
        private const val CHANNEL = "echo-recording"
        private const val RATE = 16000
        fun adtsHeader(payload: Int): ByteArray {
            val size = payload + 7
            require(size < 8192)
            // AAC-LC, 16 kHz sampling-frequency index 8, mono, no CRC.
            return byteArrayOf(0xff.toByte(), 0xf1.toByte(), ((1 shl 6) or (8 shl 2)).toByte(),
                ((1 shl 6) or (size shr 11)).toByte(), (size shr 3).toByte(), ((size shl 5) or 0x1f).toByte(), 0xfc.toByte())
        }
    }
}
