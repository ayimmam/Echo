package org.ethioware.echo.pilot

import android.Manifest
import android.media.AudioDeviceInfo
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.SystemClock
import androidx.annotation.RequiresPermission

internal const val CAPTURE_RATE = 16_000

/** The signal check and lesson recorder must request and verify the same input. */
internal object MicrophoneInput {
    private val externalTypes = setOf(
        AudioDeviceInfo.TYPE_WIRED_HEADSET,
        AudioDeviceInfo.TYPE_USB_HEADSET,
        AudioDeviceInfo.TYPE_USB_DEVICE,
    )

    fun device(manager: AudioManager, external: Boolean): AudioDeviceInfo? =
        manager.getDevices(AudioManager.GET_DEVICES_INPUTS).firstOrNull {
            if (external) it.type in externalTypes else it.type == AudioDeviceInfo.TYPE_BUILTIN_MIC
        }

    @RequiresPermission(Manifest.permission.RECORD_AUDIO)
    fun open(device: AudioDeviceInfo, bufferFloor: Int): AudioRecord {
        val minimum = AudioRecord.getMinBufferSize(
            CAPTURE_RATE, AudioFormat.CHANNEL_IN_MONO, AudioFormat.ENCODING_PCM_16BIT,
        )
        check(minimum > 0) { "This phone does not support the recording format." }
        val record = AudioRecord.Builder().setAudioSource(MediaRecorder.AudioSource.VOICE_RECOGNITION)
            .setAudioFormat(AudioFormat.Builder().setSampleRate(CAPTURE_RATE)
                .setChannelMask(AudioFormat.CHANNEL_IN_MONO)
                .setEncoding(AudioFormat.ENCODING_PCM_16BIT).build())
            .setBufferSizeInBytes(maxOf(minimum * 2, bufferFloor)).build()
        try {
            check(record.state == AudioRecord.STATE_INITIALIZED && record.setPreferredDevice(device)) {
                "Could not select the microphone."
            }
            return record
        } catch (error: Exception) {
            record.release()
            throw error
        }
    }

    /** Discard startup samples across the route transition before accepting any signal. */
    fun awaitRoute(record: AudioRecord, deviceId: Int, scratch: ShortArray, cancelled: () -> Boolean): Boolean {
        val deadline = SystemClock.elapsedRealtime() + 2000
        while (!cancelled() && SystemClock.elapsedRealtime() < deadline) {
            val before = record.routedDevice?.id
            val count = record.read(scratch, 0, scratch.size, AudioRecord.READ_NON_BLOCKING)
            if (count < 0) return false
            if (count < scratch.size && before == deviceId && record.routedDevice?.id == deviceId &&
                record.activeRecordingConfiguration?.isClientSilenced != true) return true
            Thread.sleep(10)
        }
        return false
    }
}
