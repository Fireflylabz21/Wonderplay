package com.wonderplay.academy.audio

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlin.math.sin

object AudioSynthesizer {

    fun playChime(frequency: Double, durationMs: Int = 350) {
        Thread {
            try {
                val sampleRate = 44100
                val numSamples = (sampleRate * durationMs) / 1000
                val sample = ByteArray(numSamples * 2)
                var angle = 0.0
                val angleIncrement = 2.0 * Math.PI * frequency / sampleRate

                for (i in 0 until numSamples) {
                    val envelope = 1.0 - (i.toDouble() / numSamples)
                    val wave = (sin(angle) + 0.5 * sin(angle * 2)).coerceIn(-1.0, 1.0)
                    val valShort = (wave * envelope * 32767).toInt().toShort()
                    sample[2 * i] = (valShort.toInt() and 0x00ff).toByte()
                    sample[2 * i + 1] = ((valShort.toInt() and 0xff00) ushr 8).toByte()
                    angle += angleIncrement
                }

                val audioTrack = AudioTrack.Builder()
                    .setAudioAttributes(
                        AudioAttributes.Builder()
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .build()
                    )
                    .setAudioFormat(
                        AudioFormat.Builder()
                            .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                            .setSampleRate(sampleRate)
                            .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                            .build()
                    )
                    .setBufferSizeInBytes(sample.size)
                    .setTransferMode(AudioTrack.MODE_STATIC)
                    .build()

                audioTrack.write(sample, 0, sample.size)
                audioTrack.play()
                Thread.sleep(durationMs.toLong())
                audioTrack.stop()
                audioTrack.release()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }
}
