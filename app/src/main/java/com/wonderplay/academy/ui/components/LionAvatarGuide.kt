package com.wonderplay.academy.ui.components

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.util.Locale

enum class Viseme {
    CLOSED, OPEN_A, ROUND_O, SMILE_E, THINKING
}

class AdvancedLionAvatarState(context: Context) : TextToSpeech.OnInitListener {
    var isSpeaking by mutableStateOf(false)
        private set

    var currentViseme by mutableStateOf(Viseme.CLOSED)
        private set

    var speechText by mutableStateOf("Hi! I'm Leo, your learning guide!")

    var gazeTarget by mutableStateOf(Offset.Zero)
        private set

    var isWaving by mutableStateOf(false)

    private var tts: TextToSpeech = TextToSpeech(context, this)

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.US
            tts.setSpeechRate(0.82f)
            tts.setPitch(1.65f) // Cheerful kid voice

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

    fun speak(text: String, waving: Boolean = false) {
        speechText = text
        isWaving = waving
        val paramsMap = HashMap<String, String>()
        paramsMap[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "ADVANCED_LEO_ID"
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
fun rememberAdvancedLionAvatarState(): AdvancedLionAvatarState {
    val context = LocalContext.current
    val state = remember { AdvancedLionAvatarState(context) }
    DisposableEffect(Unit) {
        onDispose { state.shutdown() }
    }
    return state
}

@Composable
fun LionAvatarGuide(
    modifier: Modifier = Modifier,
    avatarState: AdvancedLionAvatarState = rememberAdvancedLionAvatarState(),
    onAvatarClick: () -> Unit = {}
) {
    // 1. 3D Body Bouncing & Breathing
    val infiniteTransition = rememberInfiniteTransition(label = "3DBodyAnimation")
    val bodyOffsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -12f,
        animationSpec = infiniteRepeatable(
            animation = tween(1400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "OffsetY"
    )

    val breathScaleY by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.07f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Breath"
    )

    // 2. 3D Head Tilting & Expression
    val headTiltAngle by infiniteTransition.animateFloat(
        initialValue = -3f,
        targetValue = 4f,
        animationSpec = infiniteRepeatable(
            animation = tween(1800, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "HeadTilt"
    )

    // 3. Tail Wagging
    val tailRotation by infiniteTransition.animateFloat(
        initialValue = -15f,
        targetValue = 25f,
        animationSpec = infiniteRepeatable(
            animation = tween(900, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Tail"
    )

    // 4. Hand Waving & Gesturing
    val handWaveAngle by infiniteTransition.animateFloat(
        initialValue = -30f,
        targetValue = 35f,
        animationSpec = infiniteRepeatable(
            animation = tween(400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "HandWave"
    )

    // 5. Randomized Eye Blinking
    var isBlinking by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        while (true) {
            delay((2500..5000).random().toLong())
            isBlinking = true
            delay(140)
            isBlinking = false
        }
    }

    // 6. Viseme Speech Cycling
    LaunchedEffect(avatarState.isSpeaking) {
        if (avatarState.isSpeaking) {
            val visemes = listOf(Viseme.OPEN_A, Viseme.ROUND_O, Viseme.SMILE_E, Viseme.OPEN_A)
            while (avatarState.isSpeaking) {
                avatarState.updateViseme(visemes.random())
                delay(160)
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
                    onAvatarClick()
                    avatarState.speak(avatarState.speechText, waving = true)
                }
            }
    ) {
        SpeechBubble(text = avatarState.speechText)

        Spacer(modifier = Modifier.height(6.dp))

        Canvas(
            modifier = Modifier
                .width(150.dp)
                .height(185.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            val maneColor = Color(0xFFF39C12)
            val maneDark = Color(0xFFD35400)
            val bodyColor = Color(0xFFF1C40F)
            val darkOrange = Color(0xFFE67E22)
            val innerSnout = Color(0xFFFDEBD0)
            val eyeColor = Color(0xFF2C3E50)
            val cheekColor = Color(0xFFF1948A)

            // A. 3D TAIL WAG
            rotate(degrees = tailRotation, pivot = Offset(canvasWidth * 0.8f, canvasHeight * 0.7f)) {
                val tailPath = Path().apply {
                    moveTo(canvasWidth * 0.8f, canvasHeight * 0.7f)
                    cubicTo(
                        canvasWidth * 1.05f, canvasHeight * 0.62f,
                        canvasWidth * 0.92f, canvasHeight * 0.45f,
                        canvasWidth * 0.98f, canvasHeight * 0.3f
                    )
                }
                drawPath(path = tailPath, color = maneColor, style = Stroke(width = 14f))
                drawCircle(color = maneDark, radius = 18f, center = Offset(canvasWidth * 0.98f, canvasHeight * 0.3f))
            }

            // B. FEET & PAWS
            drawOval(
                color = darkOrange,
                topLeft = Offset(canvasWidth * 0.26f, canvasHeight * 0.9f),
                size = Size(canvasWidth * 0.2f, canvasHeight * 0.09f)
            )
            drawOval(
                color = darkOrange,
                topLeft = Offset(canvasWidth * 0.54f, canvasHeight * 0.9f),
                size = Size(canvasWidth * 0.2f, canvasHeight * 0.09f)
            )

            // C. RESPIRATORY BREATHING BODY TORSO
            scale(scaleX = 1f, scaleY = breathScaleY, pivot = Offset(canvasWidth * 0.5f, canvasHeight * 0.85f)) {
                val bodyPath = Path().apply {
                    moveTo(canvasWidth * 0.35f, canvasHeight * 0.55f)
                    quadraticBezierTo(
                        canvasWidth * 0.22f, canvasHeight * 0.92f,
                        canvasWidth * 0.36f, canvasHeight * 0.92f
                    )
                    lineTo(canvasWidth * 0.64f, canvasHeight * 0.92f)
                    quadraticBezierTo(
                        canvasWidth * 0.78f, canvasHeight * 0.92f,
                        canvasWidth * 0.65f, canvasHeight * 0.55f
                    )
                    close()
                }
                drawPath(path = bodyPath, color = bodyColor)
                drawPath(path = bodyPath, color = darkOrange, style = Stroke(width = 5f))

                // Tummy Patch
                drawOval(
                    color = innerSnout,
                    topLeft = Offset(canvasWidth * 0.38f, canvasHeight * 0.68f),
                    size = Size(canvasWidth * 0.24f, canvasHeight * 0.2f)
                )
            }

            // D. 3D HAND WAVING & GESTURING ARMS
            // Left Arm Resting
            drawOval(
                color = darkOrange,
                topLeft = Offset(canvasWidth * 0.2f, canvasHeight * 0.62f),
                size = Size(canvasWidth * 0.14f, canvasHeight * 0.22f)
            )

            // Right Waving Hand / Arm Gesture
            rotate(degrees = if (avatarState.isWaving) handWaveAngle else 15f, pivot = Offset(canvasWidth * 0.75f, canvasHeight * 0.62f)) {
                drawOval(
                    color = darkOrange,
                    topLeft = Offset(canvasWidth * 0.68f, canvasHeight * 0.55f),
                    size = Size(canvasWidth * 0.16f, canvasHeight * 0.24f)
                )
                // Paws
                drawCircle(color = maneDark, radius = 10f, center = Offset(canvasWidth * 0.76f, canvasHeight * 0.57f))
            }

            // E. 3D TILTING HEAD & MANE
            rotate(degrees = headTiltAngle, pivot = Offset(canvasWidth * 0.5f, canvasHeight * 0.38f)) {
                val maneCenterX = canvasWidth * 0.5f
                val maneCenterY = canvasHeight * 0.38f
                val maneRadius = canvasWidth * 0.34f

                drawCircle(color = maneColor, radius = maneRadius, center = Offset(maneCenterX, maneCenterY))
                
                val tufts = listOf(
                    Offset(canvasWidth * 0.28f, canvasHeight * 0.18f),
                    Offset(canvasWidth * 0.72f, canvasHeight * 0.18f),
                    Offset(canvasWidth * 0.18f, canvasHeight * 0.38f),
                    Offset(canvasWidth * 0.82f, canvasHeight * 0.38f),
                    Offset(canvasWidth * 0.28f, canvasHeight * 0.55f),
                    Offset(canvasWidth * 0.72f, canvasHeight * 0.55f)
                )
                for (t in tufts) {
                    drawCircle(color = maneDark, radius = 26f, center = t)
                }

                // Ears
                drawCircle(color = bodyColor, radius = 20f, center = Offset(canvasWidth * 0.3f, canvasHeight * 0.18f))
                drawCircle(color = maneColor, radius = 11f, center = Offset(canvasWidth * 0.3f, canvasHeight * 0.18f))
                drawCircle(color = bodyColor, radius = 20f, center = Offset(canvasWidth * 0.7f, canvasHeight * 0.18f))
                drawCircle(color = maneColor, radius = 11f, center = Offset(canvasWidth * 0.7f, canvasHeight * 0.18f))

                // Head Base
                drawCircle(color = bodyColor, radius = canvasWidth * 0.24f, center = Offset(maneCenterX, maneCenterY))

                // Eyes & Gaze Tracking
                val leftEyeCenter = Offset(canvasWidth * 0.42f, canvasHeight * 0.34f)
                val rightEyeCenter = Offset(canvasWidth * 0.58f, canvasHeight * 0.34f)

                if (isBlinking) {
                    drawLine(color = eyeColor, start = Offset(leftEyeCenter.x - 10f, leftEyeCenter.y), end = Offset(leftEyeCenter.x + 10f, leftEyeCenter.y), strokeWidth = 4f)
                    drawLine(color = eyeColor, start = Offset(rightEyeCenter.x - 10f, rightEyeCenter.y), end = Offset(rightEyeCenter.x + 10f, rightEyeCenter.y), strokeWidth = 4f)
                } else {
                    drawOval(color = eyeColor, topLeft = Offset(leftEyeCenter.x - 10f, leftEyeCenter.y - 14f), size = Size(20f, 28f))
                    drawOval(color = eyeColor, topLeft = Offset(rightEyeCenter.x - 10f, rightEyeCenter.y - 14f), size = Size(20f, 28f))

                    val gazeX = ((avatarState.gazeTarget.x - canvasWidth / 2f) / 90f).coerceIn(-4f, 4f)
                    val gazeY = ((avatarState.gazeTarget.y - canvasHeight / 2f) / 90f).coerceIn(-4f, 4f)

                    drawCircle(color = Color.White, radius = 6f, center = Offset(leftEyeCenter.x + gazeX, leftEyeCenter.y + gazeY))
                    drawCircle(color = Color.White, radius = 6f, center = Offset(rightEyeCenter.x + gazeX, rightEyeCenter.y + gazeY))
                }

                // Snout & Nose
                drawOval(
                    color = innerSnout,
                    topLeft = Offset(canvasWidth * 0.39f, canvasHeight * 0.4f),
                    size = Size(canvasWidth * 0.22f, canvasHeight * 0.12f)
                )
                val nosePath = Path().apply {
                    moveTo(canvasWidth * 0.45f, canvasHeight * 0.42f)
                    lineTo(canvasWidth * 0.55f, canvasHeight * 0.42f)
                    lineTo(canvasWidth * 0.5f, canvasHeight * 0.47f)
                    close()
                }
                drawPath(path = nosePath, color = maneDark)

                // Viseme Lip-Sync Mouth
                val mouthCenter = Offset(canvasWidth * 0.5f, canvasHeight * 0.52f)
                when (avatarState.currentViseme) {
                    Viseme.CLOSED -> {
                        drawLine(color = eyeColor, start = Offset(mouthCenter.x - 12f, mouthCenter.y), end = Offset(mouthCenter.x + 12f, mouthCenter.y), strokeWidth = 5f)
                    }
                    Viseme.OPEN_A -> {
                        drawOval(color = eyeColor, topLeft = Offset(mouthCenter.x - 14f, mouthCenter.y - 8f), size = Size(28f, 20f))
                    }
                    Viseme.ROUND_O -> {
                        drawCircle(color = eyeColor, radius = 12f, center = mouthCenter)
                    }
                    Viseme.SMILE_E -> {
                        val smilePath = Path().apply {
                            moveTo(mouthCenter.x - 16f, mouthCenter.y - 2f)
                            quadraticBezierTo(mouthCenter.x, mouthCenter.y + 12f, mouthCenter.x + 16f, mouthCenter.y - 2f)
                        }
                        drawPath(path = smilePath, color = eyeColor, style = Stroke(width = 5f))
                    }
                    Viseme.THINKING -> {
                        drawCircle(color = eyeColor, radius = 8f, center = Offset(mouthCenter.x + 6f, mouthCenter.y))
                    }
                }

                // Rosy Cheeks
                drawCircle(color = cheekColor.copy(alpha = 0.7f), radius = 14f, center = Offset(canvasWidth * 0.33f, canvasHeight * 0.42f))
                drawCircle(color = cheekColor.copy(alpha = 0.7f), radius = 14f, center = Offset(canvasWidth * 0.67f, canvasHeight * 0.42f))
            }
        }
    }
}

@Composable
private fun SpeechBubble(text: String) {
    Box(
        modifier = Modifier
            .widthIn(max = 200.dp)
            .shadow(6.dp, shape = RoundedCornerShape(20.dp))
            .background(Color.White, shape = RoundedCornerShape(20.dp))
            .border(2.dp, Color(0xFFFFC107), shape = RoundedCornerShape(20.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037),
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }
}
