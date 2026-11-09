package com.wonderplay.academy.ui.components

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wonderplay.academy.R
import kotlinx.coroutines.delay
import java.util.Locale

class AruneshAvatarState(context: Context) : TextToSpeech.OnInitListener {
    var isSpeaking by mutableStateOf(false)
        private set

    var currentViseme by mutableStateOf(Viseme.CLOSED)
        private set

    var speechText by mutableStateOf("Hi! I'm Arunesh! Let's learn together!")

    var gazeTarget by mutableStateOf(Offset.Zero)
        private set

    var isWaving by mutableStateOf(false)

    private var tts: TextToSpeech = TextToSpeech(context, this)

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.US
            tts.setSpeechRate(0.75f) // Slow, clear pronunciation for kids
            tts.setPitch(1.55f) // Cheerful kid voice

            tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    isSpeaking = true
                    isWaving = true
                }

                override fun onDone(utteranceId: String?) {
                    isSpeaking = false
                    isWaving = false
                    currentViseme = Viseme.CLOSED
                }

                @Deprecated("Deprecated in Java API")
                override fun onError(utteranceId: String?) {
                    isSpeaking = false
                    isWaving = false
                    currentViseme = Viseme.CLOSED
                }
            })
        }
    }

    fun speak(text: String, waving: Boolean = true) {
        speechText = text
        isWaving = waving
        val paramsMap = HashMap<String, String>()
        paramsMap[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "ARUNESH_AVATAR_ID"
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, paramsMap)
    }

    fun updateGaze(target: Offset) {
        gazeTarget = target
    }

    fun updateViseme(viseme: Viseme) {
        currentViseme = viseme
    }

    fun shutdown() {
        tts.stop()
        tts.shutdown()
    }
}

@Composable
fun rememberAruneshAvatarState(): AruneshAvatarState {
    val context = LocalContext.current
    val state = remember { AruneshAvatarState(context) }
    DisposableEffect(Unit) {
        onDispose { state.shutdown() }
    }
    return state
}

@Composable
fun AruneshAvatarGuide(
    modifier: Modifier = Modifier,
    avatarState: AruneshAvatarState = rememberAruneshAvatarState(),
    initialSpeechText: String? = null,
    onAvatarClick: () -> Unit = {}
) {
    LaunchedEffect(initialSpeechText) {
        if (!initialSpeechText.isNullOrEmpty()) {
            avatarState.speechText = initialSpeechText
        }
    }
    // 1. 3D Body Bouncing & Respiratory Breathing Animation
    val infiniteTransition = rememberInfiniteTransition(label = "AruneshFullAvatarAnimation")
    val bodyOffsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -10f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "OffsetY"
    )

    val breathScaleY by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Breath"
    )

    // 2. 3D Head Tilting Angle
    val headTiltAngle by infiniteTransition.animateFloat(
        initialValue = -2f,
        targetValue = 3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "HeadTilt"
    )

    // 3. Hand Waving & Gesturing
    val handWaveAngle by infiniteTransition.animateFloat(
        initialValue = -15f,
        targetValue = 25f,
        animationSpec = infiniteRepeatable(
            animation = tween(400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "HandWave"
    )

    // 4. Randomized Eye Blinking
    var isBlinking by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) {
            delay((2800..5200).random().toLong())
            isBlinking = true
            delay(140)
            isBlinking = false
        }
    }

    // 5. Viseme Speech Lip-Sync Cycling
    LaunchedEffect(avatarState.isSpeaking) {
        if (avatarState.isSpeaking) {
            val visemes = listOf(Viseme.OPEN_A, Viseme.ROUND_O, Viseme.SMILE_E, Viseme.OPEN_A)
            while (avatarState.isSpeaking) {
                avatarState.updateViseme(visemes.random())
                delay(150)
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .offset(y = bodyOffsetY.dp)
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    avatarState.updateGaze(offset)
                    avatarState.speak(avatarState.speechText, waving = true)
                    onAvatarClick()
                }
            }
    ) {
        // Speech Bubble Guide
        SpeechBubble(text = avatarState.speechText)

        Spacer(modifier = Modifier.height(6.dp))

        // Enhanced 3D Avatar Container using arunesh_fullavatar.png with Live Gestures, Head Tilt, Breathing & Lip Sync
        Box(
            modifier = Modifier
                .width(160.dp)
                .height(200.dp)
                .scale(1f, breathScaleY)
                .rotate(headTiltAngle),
            contentAlignment = Alignment.Center
        ) {
            // Base High-Resolution 3D Avatar Image
            Image(
                painter = painterResource(id = R.drawable.arunesh_fullavatar),
                contentDescription = "Arunesh 3D Avatar",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )

            // Dynamic Gesture & Facial Overlays (Blinking & Viseme Lip Movements)
            Canvas(modifier = Modifier.fillMaxSize()) {
                val w = size.width
                val h = size.height

                // Left & Right Eye Blinking Overlay
                if (isBlinking) {
                    drawRect(
                        color = Color(0xFF2C3E50),
                        topLeft = Offset(w * 0.38f, h * 0.28f),
                        size = Size(18f, 4f)
                    )
                    drawRect(
                        color = Color(0xFF2C3E50),
                        topLeft = Offset(w * 0.54f, h * 0.28f),
                        size = Size(18f, 4f)
                    )
                }

                // Active Speaking Lip Movements (Visemes) Overlay
                if (avatarState.isSpeaking) {
                    val mouthCenter = Offset(w * 0.48f, h * 0.35f)
                    when (avatarState.currentViseme) {
                        Viseme.OPEN_A -> {
                            drawOval(
                                color = Color(0xFF881337),
                                topLeft = Offset(mouthCenter.x - 9f, mouthCenter.y - 5f),
                                size = Size(18f, 14f)
                            )
                        }
                        Viseme.ROUND_O -> {
                            drawCircle(
                                color = Color(0xFF881337),
                                radius = 8f,
                                center = mouthCenter
                            )
                        }
                        Viseme.SMILE_E -> {
                            val smilePath = Path().apply {
                                moveTo(mouthCenter.x - 11f, mouthCenter.y - 1f)
                                quadraticBezierTo(mouthCenter.x, mouthCenter.y + 9f, mouthCenter.x + 11f, mouthCenter.y - 1f)
                            }
                            drawPath(path = smilePath, color = Color(0xFF881337), style = Stroke(width = 3.5f))
                        }
                        else -> {
                            drawLine(
                                color = Color(0xFF881337),
                                start = Offset(mouthCenter.x - 9f, mouthCenter.y),
                                end = Offset(mouthCenter.x + 9f, mouthCenter.y),
                                strokeWidth = 3.5f
                            )
                        }
                    }
                }

                // Hand Waving Sparkle / Gesture Indicator when Waving
                if (avatarState.isWaving) {
                    drawCircle(
                        color = Color(0xFFFFDE59).copy(alpha = 0.85f),
                        radius = 14f + (handWaveAngle * 0.2f),
                        center = Offset(w * 0.22f, h * 0.45f)
                    )
                }
            }
        }
    }
}

@Composable
private fun SpeechBubble(text: String) {
    Box(
        modifier = Modifier
            .widthIn(max = 210.dp)
            .shadow(6.dp, shape = RoundedCornerShape(20.dp))
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .border(2.dp, Color(0xFFF97316), shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF9A3412),
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }
}
