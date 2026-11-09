package com.wonderplay.academy.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.*

class TtsManager(context: Context) {
    private var tts: TextToSpeech? = null
    private var isInitialized = false

    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                tts?.language = Locale.US
                tts?.setSpeechRate(0.65f) // Slow, extremely clear pronunciation for kids
                tts?.setPitch(1.55f) // Cheerful, kid-friendly pitch
                isInitialized = true
            }
        }
    }

    fun speak(text: String, isTamil: Boolean = false) {
        if (!isInitialized) return
        if (isTamil) {
            tts?.language = Locale("ta", "IN")
        } else {
            tts?.language = Locale.US
        }
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}
