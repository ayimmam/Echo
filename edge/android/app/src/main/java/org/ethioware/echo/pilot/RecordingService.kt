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
import android.media.AudioManager
import android.media.AudioRecord
import android.media.AudioRecordingConfiguration
import android.media.MediaCodec
import android.media.MediaCodecInfo
import android.media.MediaFormat
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
import java.nio.ByteOrder
import java.util.concurrent.atomic.AtomicBoolean
import kotlin.math.abs
import org.ethioware.echo.MainActivity

data class RecordingStatus(
    val running: Boolean = false,
    val seconds: Long = 0,
    val message: String = "",
    val lessonId: String? = null,
    val stopping: Boolean = false,
    val levels: List<Int> = emptyList(),
    val failed: Boolean = false,
)
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
        if (intent?.action == STOP) {
            stop.set(true)
            if (worker == null) stopSelf()
            else if (RecordingMonitor.state.running) RecordingMonitor.state = RecordingMonitor.state.copy(stopping = true)
            return START_NOT_STICKY
        }
        if (worker != null) return START_NOT_STICKY
        if (intent?.action != START || checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            publish(RecordingStatus(message = "Microphone permission is needed to record.", failed = true))
            stopSelf(); return START_NOT_STICKY
        }
        val am = intent.getStringExtra("locale") == "am"
        getSystemService(NotificationManager::class.java).createNotificationChannel(
            NotificationChannel(CHANNEL, if (am) "የክፍል ቅጂ" else "Classroom recording", NotificationManager.IMPORTANCE_LOW))
        try {
            if (Build.VERSION.SDK_INT >= 30) startForeground(3100, notification(am), ServiceInfo.FOREGROUND_SERVICE_TYPE_MICROPHONE)
            else startForeground(3100, notification(am))
        }
        catch (_: Exception) {
            publish(RecordingStatus(message = "Android blocked microphone access. Open Echo and check permissions.", failed = true))
            stopSelf(); return START_NOT_STICKY
        }
        stop.set(false)
        val external = intent.getBooleanExtra("external", true)
        val profile = runCatching { ClassProfile.parse(org.json.JSONObject(intent.getStringExtra("profile") ?: "")) }.getOrNull()
        if (profile == null || !profile.valid()) {
            publish(RecordingStatus(message = "Check your class details and try again.", failed = true))
            stopSelf(); return START_NOT_STICKY
        }
        RecordingMonitor.state = RecordingStatus(running = true)
        worker = Thread { capture(profile, external) }.also { it.start() }
        return START_NOT_STICKY
    }
    private fun notification(am: Boolean): Notification {
        val open = PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        val end = PendingIntent.getService(this, 1, Intent(this, RecordingService::class.java).setAction(STOP), PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        return Notification.Builder(this, CHANNEL).setSmallIcon(android.R.drawable.ic_btn_speak_now)
            .setContentTitle(if (am) "Echo እየቀዳ ነው" else "Echo is recording")
            .setContentText(if (am) "ድምጹ በዚህ ስልክ ላይ ይቆያል። ትምህርቱ ሲያበቃ አቁምን ይንኩ።" else "Audio stays on this phone. Tap Stop when the lesson ends.")
            .setContentIntent(open).setOngoing(true).setOnlyAlertOnce(true)
            .addAction(Notification.Action.Builder(null, if (am) "አቁምና አስቀምጥ" else "Stop and save", end).build()).build()
    }
    private fun publish(value: RecordingStatus) {
        main.post { if (!value.running || !stop.get()) RecordingMonitor.state = value }
    }
    private fun capture(profile: ClassProfile, external: Boolean) {
        var recorder: AudioRecord? = null
        var recordingCallback: AudioManager.AudioRecordingCallback? = null
        var encoder: MediaCodec? = null
        var store: PilotStore? = null
        var lesson: LocalLesson? = null
        var captureStartElapsed: Long? = null
        var captureEndElapsed: Long? = null
        var reason = ""
        try {
            store = PilotRuntime.store(this)
            val storage = StatFs(filesDir.path)
            check(storage.availableBytes >= 32 * 1024 * 1024) { "Free at least 32 MB before recording." }
            val device = MicrophoneInput.device(getSystemService(AudioManager::class.java), external)
                ?: error(if (external) "Connect a wired or USB microphone." else "Phone microphone unavailable.")
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                throw SecurityException("Microphone permission was revoked.")
            }
            recorder = MicrophoneInput.open(device, 65_536)
            val silenced = AtomicBoolean(false)
            recordingCallback = object : AudioManager.AudioRecordingCallback() {
                override fun onRecordingConfigChanged(configs: List<AudioRecordingConfiguration>) {
                    if (configs.any { it.isClientSilenced }) silenced.set(true)
                }
            }
            recorder.registerAudioRecordingCallback({ it.run() }, recordingCallback)
            encoder = MediaCodec.createEncoderByType(MediaFormat.MIMETYPE_AUDIO_AAC)
            encoder.configure(MediaFormat.createAudioFormat(MediaFormat.MIMETYPE_AUDIO_AAC, CAPTURE_RATE, 1).apply {
                setInteger(MediaFormat.KEY_AAC_PROFILE, MediaCodecInfo.CodecProfileLevel.AACObjectLC)
                setInteger(MediaFormat.KEY_BIT_RATE, 32_000)
                setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, 4096)
            }, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE)
            encoder.start()
            wake = getSystemService(PowerManager::class.java).newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Echo:recording")
                .apply { acquire(2 * 60 * 60 * 1000L + 60_000) }
            recorder.startRecording()
            check(recorder.recordingState == AudioRecord.RECORDSTATE_RECORDING) { "Microphone did not start." }
            val pcm = ShortArray(1024)
            check(MicrophoneInput.awaitRoute(recorder, device.id, pcm, stop::get)) {
                if (stop.get()) "Recording was stopped before audio was saved." else "Could not verify the selected microphone. Reconnect it and try again."
            }
            lesson = store.create(profile)
            val start = SystemClock.elapsedRealtime()
            captureStartElapsed = start
            var totalSamples = 0L
            var chunkSamples = 0L
            var clipped = 0L
            var quiet = 0L
            val chunk = ByteArrayOutputStream(128_000)
            val encoded = ByteArray(8192)
            val header = ByteArray(7)
            var frames = 0L
            var committedSamples = 0L
            var encodedSamples = 0L
            var lastProgress = -1L
            var lastInput = start
            var inputIndex = -1
            val peaks = IntArray(24)
            var peakIndex = 0
            val info = MediaCodec.BufferInfo()
            fun commit() {
                if (frames == 0L) return
                // AAC-LC has 1024 decoded samples/frame. Bound coverage by captured input.
                val coveredSamples = minOf(encodedSamples, totalSamples)
                val duration = (coveredSamples - committedSamples) * 1000 / CAPTURE_RATE
                if (duration > 0) {
                    store.append(lesson.id, chunk.toByteArray(), duration, chunkSamples, clipped, quiet,
                        commitElapsedMs = SystemClock.elapsedRealtime() - start,
                        savedSamples = coveredSamples - committedSamples)
                    committedSamples = coveredSamples
                }
                chunk.reset(); frames = 0; chunkSamples = 0; clipped = 0; quiet = 0
            }
            fun drain(eos: Boolean = false): Boolean {
                val deadline = SystemClock.elapsedRealtime() + if (eos) 1500 else 20
                do {
                    val index = encoder.dequeueOutputBuffer(info, if (eos) 10_000 else 0)
                    if (index >= 0) {
                        if (info.size > 0 && info.flags and MediaCodec.BUFFER_FLAG_CODEC_CONFIG == 0) {
                            val data = encoder.getOutputBuffer(index)!!
                            data.position(info.offset); data.limit(info.offset + info.size)
                            writeAdtsHeader(header, info.size)
                            data.get(encoded, 0, info.size)
                            chunk.write(header)
                            chunk.write(encoded, 0, info.size)
                            frames++
                            encodedSamples += 1024
                        }
                        val ended = info.flags and MediaCodec.BUFFER_FLAG_END_OF_STREAM != 0
                        encoder.releaseOutputBuffer(index, false)
                        if (ended) return true
                    } else if (!eos && index == MediaCodec.INFO_TRY_AGAIN_LATER) break
                } while (SystemClock.elapsedRealtime() < deadline)
                return false
            }
            while (!stop.get()) {
                val elapsed = SystemClock.elapsedRealtime() - start
                if (elapsed >= 2 * 60 * 60 * 1000L) { reason = "Two-hour recording limit reached."; break }
                if (elapsed / 1000 != lastProgress) {
                    storage.restat(filesDir.path)
                    if (storage.availableBytes < 8 * 1024 * 1024) { reason = "Storage is nearly full."; break }
                    if (recorder.activeRecordingConfiguration?.isClientSilenced == true) silenced.set(true)
                }
                if (silenced.get()) { reason = "Android interrupted microphone access."; break }
                if (recorder.routedDevice?.id != device.id) {
                    reason = "Microphone route changed. Reconnect it before starting a new lesson."; break
                }
                if (inputIndex < 0) inputIndex = encoder.dequeueInputBuffer(10_000)
                if (inputIndex >= 0) {
                    val count = recorder.read(pcm, 0, pcm.size, AudioRecord.READ_NON_BLOCKING)
                    if (count < 0) error("Microphone stopped delivering audio.")
                    if (count > 0) {
                        if (silenced.get()) { reason = "Android interrupted microphone access."; break }
                        if (recorder.routedDevice?.id != device.id) {
                            reason = "Microphone route changed. Reconnect it before starting a new lesson."; break
                        }
                        val input = encoder.getInputBuffer(inputIndex)!!
                        input.clear()
                        input.order(ByteOrder.LITTLE_ENDIAN)
                        var peak = 0
                        for (i in 0 until count) {
                            val amplitude = abs(pcm[i].toInt())
                            input.putShort(pcm[i])
                            if (amplitude >= 32700) clipped++
                            if (amplitude < 32) quiet++
                            peak = maxOf(peak, amplitude)
                        }
                        peaks[peakIndex] = peak
                        peakIndex = (peakIndex + 1) % peaks.size
                        encoder.queueInputBuffer(inputIndex, 0, count * 2, totalSamples * 1_000_000 / CAPTURE_RATE, 0)
                        inputIndex = -1
                        totalSamples += count
                        chunkSamples += count
                        lastInput = SystemClock.elapsedRealtime()
                    } else {
                        Thread.sleep(15)
                    }
                }
                drain()
                check(chunk.size() <= 256_000 && totalSamples - encodedSamples <= CAPTURE_RATE * 3L) {
                    "Audio encoder could not keep up. Completed excerpts are saved."
                }
                if (SystemClock.elapsedRealtime() - lastInput > 3000) error("No audio arrived from the microphone.")
                if (chunkSamples >= CAPTURE_RATE * 30L) commit()
                val seconds = elapsed / 1000
                if (seconds != lastProgress) {
                    lastProgress = seconds
                    publish(RecordingStatus(running = true, seconds = seconds, lessonId = lesson.id,
                        levels = List(peaks.size) { peaks[(peakIndex + it) % peaks.size] }))
                }
            }
            captureEndElapsed = SystemClock.elapsedRealtime()
            recorder.stop()
            var endInput = if (inputIndex >= 0) inputIndex else encoder.dequeueInputBuffer(10_000)
            val finalDeadline = SystemClock.elapsedRealtime() + 1500
            while (endInput < 0 && SystemClock.elapsedRealtime() < finalDeadline) {
                drain()
                endInput = encoder.dequeueInputBuffer(10_000)
            }
            val finishedEncoding = if (endInput >= 0) {
                encoder.queueInputBuffer(endInput, 0, 0, totalSamples * 1_000_000 / CAPTURE_RATE, MediaCodec.BUFFER_FLAG_END_OF_STREAM)
                drain(true)
            } else false
            if (!finishedEncoding && reason.isEmpty()) reason = "Some ending audio could not be saved."
            commit()
        } catch (error: Exception) {
            if (captureEndElapsed == null) captureEndElapsed = SystemClock.elapsedRealtime()
            reason = when (error) {
                is SecurityException -> "Microphone permission or private storage access was revoked."
                else -> error.message?.take(180) ?: "Recording stopped unexpectedly."
            }
        } finally {
            recordingCallback?.let { runCatching { recorder?.unregisterAudioRecordingCallback(it) } }
            runCatching { recorder?.release() }; runCatching { encoder?.stop() }; runCatching { encoder?.release() }
            if (wake?.isHeld == true) wake?.release()
            lesson?.let { row ->
                val attempted = captureStartElapsed?.let { (captureEndElapsed ?: SystemClock.elapsedRealtime()) - it }
                runCatching { requireNotNull(store).finish(row.id, reason, attempted) }
                    .onSuccess { reason = it.reason }
                    .onFailure { reason = "Could not finalize the lesson. Completed chunks will be recovered on restart." }
            }
            runCatching { store?.auditEvent("capture", "record_attempt", if (reason.isEmpty()) "saved" else "interrupted") }
            main.post {
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
                RecordingMonitor.state = RecordingStatus(
                    message = reason.ifEmpty { "Lesson saved on this phone." }, lessonId = lesson?.id,
                    failed = reason.isNotEmpty(),
                )
            }
        }
    }
    override fun onDestroy() { stop.set(true); super.onDestroy() }
    companion object {
        const val START = "org.ethioware.echo.RECORD"
        const val STOP = "org.ethioware.echo.STOP"
        private const val CHANNEL = "echo-recording"
        private fun writeAdtsHeader(header: ByteArray, payload: Int) {
            val size = payload + 7
            require(size < 8192)
            // AAC-LC, 16 kHz sampling-frequency index 8, mono, no CRC.
            header[0] = 0xff.toByte()
            header[1] = 0xf1.toByte()
            header[2] = ((1 shl 6) or (8 shl 2)).toByte()
            header[3] = ((1 shl 6) or (size shr 11)).toByte()
            header[4] = (size shr 3).toByte()
            header[5] = ((size shl 5) or 0x1f).toByte()
            header[6] = 0xfc.toByte()
        }
    }
}
