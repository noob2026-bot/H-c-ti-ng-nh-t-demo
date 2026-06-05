package com.example.util

import android.content.Context
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale
import kotlin.math.sin

class AudioHelper(context: Context) : TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    private var isTtsReady = false

    init {
        tts = TextToSpeech(context.applicationContext, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.JAPANESE)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("AudioHelper", "Japanese language is not supported or missing data in TTS")
                // Check if Google TTS or other engine can be customized - usually defaults gracefully
            } else {
                isTtsReady = true
            }
        } else {
            Log.e("AudioHelper", "TTS Initialization failed")
        }
    }

    fun speak(text: String, speed: Float = 0.85f) {
        if (isTtsReady) {
            try {
                tts?.apply {
                    setSpeechRate(speed)
                    speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
                }
            } catch (e: Exception) {
                Log.e("AudioHelper", "Error playing Speech Synthesis", e)
            }
        }
    }

    fun playSfx(correct: Boolean) {
        Thread {
            try {
                val sampleRate = 44100
                val durationMs = if (correct) 250 else 300
                val numSamples = durationMs * sampleRate / 1000
                val generatedSnd = ByteArray(2 * numSamples)

                val frequencies = if (correct) listOf(587.33, 880.00) else listOf(220.00, 146.83)
                
                for (i in 0 until numSamples) {
                    val progress = i.toDouble() / numSamples
                    val freq = if (correct) {
                        if (progress < 0.3) frequencies[0] else frequencies[1]
                    } else {
                        if (progress < 0.45) frequencies[0] else frequencies[1]
                    }
                    val t = i.toDouble() / sampleRate
                    val envelope = if (progress > 0.85) (1.0 - progress) / 0.15 else 1.0
                    val sample = sin(2.0 * Math.PI * freq * t) * 32767.0 * 0.18 * envelope
                    val valSigned = sample.toInt().toShort()
                    
                    generatedSnd[2 * i] = (valSigned.toInt() and 0x00ff).toByte()
                    generatedSnd[2 * i + 1] = ((valSigned.toInt() and 0xff00) ushr 8).toByte()
                }

                val audioTrack = AudioTrack(
                    AudioManager.STREAM_MUSIC,
                    sampleRate,
                    AudioFormat.CHANNEL_OUT_MONO,
                    AudioFormat.ENCODING_PCM_16BIT,
                    generatedSnd.size,
                    AudioTrack.MODE_STATIC
                )
                audioTrack.write(generatedSnd, 0, generatedSnd.size)
                audioTrack.play()
                
                Thread.sleep(durationMs.toLong() + 100)
                audioTrack.stop()
                audioTrack.release()
            } catch (e: Exception) {
                Log.e("AudioHelper", "Error synthesizing sound effect", e)
            }
        }.start()
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}
