package com.wonderplay.academy.ui.components

import android.content.Context
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale

/**
 * State manager for controlling Leo the Lion Avatar's animations & TTS speech.
 */
class LionAvatarState(context: Context) : TextToSpeech.OnInitListener {
    var isSpeaking by mutableStateOf(false)
        private set

    var speechText by mutableStateOf("Hi! I'm Leo! Tap me to hear the sound!")

    private var tts: TextToSpeech = TextToSpeech(context, this)

    override onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts.language = Locale.US
            tts.setSpeechRate(0.75f) // Calibrated slow rate for toddlers
            tts.setPitch(1.15f)

            tts.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
                override fun onStart(utteranceId: String?) {
                    isSpeaking = true
                }

                override fun onDone(utteranceId: String?) {
                    isSpeaking = false
                }

                @Deprecated("Deprecated in Java API")
                override fun onError(utteranceId: String?) {
                    isSpeaking = false
                }
            })
        }
    }

    /**
     * Speaks the provided text via Android TTS and triggers Leo's mouth movement.
     */
    fun speak(text: String) {
        speechText = text
        val paramsMap = HashMap<String, String>()
        paramsMap[TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID] = "LEO_SPEECH_ID"
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, paramsMap)
    }

    fun shutdown() {
        tts.stop()
        tts.shutdown()
    }
}

@Composable
fun rememberLionAvatarState(): LionAvatarState {
    val context = LocalContext.current
    val state = remember { LionAvatarState(context) }
    DisposableEffect(Unit) {
        onDispose {
            state.shutdown()
        }
    }
    return state
}

/**
 * Main Standalone Composable for the Full-Standing Lion Avatar Guide ("Leo").
 *
 * @param modifier Modifier for external positioning.
 * @param avatarState Controls the speech & talking mouth animations.
 * @param onAvatarClick Callback when child taps on the lion.
 */
@Composable
fun LionAvatarGuide(
    modifier: Modifier = Modifier,
    avatarState: LionAvatarState = rememberLionAvatarState(),
    onAvatarClick: () -> Unit = {}
) {
    // ----------------------------------------------------
    // ANIMATION ENGINE DEFINITIONS
    // ----------------------------------------------------

    // 1. Idle Gentle Body Bounce Animation
    val infiniteTransition = rememberInfiniteTransition(label = "IdleBounce")
    val bodyOffsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -10f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "OffsetY"
    )

    // 2. Tail Wagging Rotation
    val tailRotation by infiniteTransition.animateFloat(
        initialValue = -8f,
        targetValue = 18f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "TailRotation"
    )

    // 3. Dynamic Mouth Opening Scale (Triggers when `avatarState.isSpeaking` is true)
    val mouthScaleY by animateFloatAsState(
        targetValue = if (avatarState.isSpeaking) 2.2f else 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(140, easing = FastOutLinearInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "MouthTalking"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .offset(y = bodyOffsetY.dp)
            .clickable {
                onAvatarClick()
                avatarState.speak(avatarState.speechText)
            }
    ) {
        // ----------------------------------------------------
        // FLOATING SPEECH BUBBLE
        // ----------------------------------------------------
        SpeechBubble(text = avatarState.speechText)

        Spacer(modifier = Modifier.height(6.dp))

        // ----------------------------------------------------
        // FULL-BODY STANDING LION VECTOR CANVAS
        // ----------------------------------------------------
        Canvas(
            modifier = Modifier
                .width(140.dp)
                .height(170.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            // Color Palette Design Tokens
            val maneColor = Color(0xFFE67E22)
            val maneDark = Color(0xFFD35400)
            val bodyColor = Color(0xFFF1C40F)
            val darkOrange = Color(0xFFF39C12)
            val innerSnout = Color(0xFFFDEBD0)
            val eyeColor = Color(0xFF2C3E50)
            val cheekColor = Color(0xFFF1948A)

            // A. DRAW TAIL WITH WAG ROTATION
            rotate(degrees = tailRotation, pivot = Offset(canvasWidth * 0.75f, canvasHeight * 0.7f)) {
                val tailPath = Path().apply {
                    moveTo(canvasWidth * 0.75f, canvasHeight * 0.7f)
                    cubicTo(
                        canvasWidth * 0.95f, canvasHeight * 0.65f,
                        canvasWidth * 0.88f, canvasHeight * 0.5f,
                        canvasWidth * 0.92f, canvasHeight * 0.35f
                    )
                }
                drawPath(
                    path = tailPath,
                    color = maneColor,
                    style = Stroke(width = 12f)
                )
                drawCircle(
                    color = maneDark,
                    radius = 16f,
                    center = Offset(canvasWidth * 0.92f, canvasHeight * 0.35f)
                )
            }

            // B. FEET & PAWS
            drawOval(
                color = darkOrange,
                topLeft = Offset(canvasWidth * 0.28f, canvasHeight * 0.88f),
                size = Size(canvasWidth * 0.18f, canvasHeight * 0.1f)
            )
            drawOval(
                color = darkOrange,
                topLeft = Offset(canvasWidth * 0.54f, canvasHeight * 0.88f),
                size = Size(canvasWidth * 0.18f, canvasHeight * 0.1f)
            )

            // C. LION BODY & TUMMY
            val bodyPath = Path().apply {
                moveTo(canvasWidth * 0.35f, canvasHeight * 0.55f)
                quadraticBezierTo(
                    canvasWidth * 0.25f, canvasHeight * 0.88f,
                    canvasWidth * 0.35f, canvasHeight * 0.88f
                )
                lineTo(canvasWidth * 0.65f, canvasHeight * 0.88f)
                quadraticBezierTo(
                    canvasWidth * 0.75f, canvasHeight * 0.88f,
                    canvasWidth * 0.65f, canvasHeight * 0.55f
                )
                close()
            }
            drawPath(path = bodyPath, color = bodyColor)
            drawPath(path = bodyPath, color = darkOrange, style = Stroke(width = 4f))

            // Tummy Patch
            drawOval(
                color = innerSnout,
                topLeft = Offset(canvasWidth * 0.4f, canvasHeight * 0.68f),
                size = Size(canvasWidth * 0.2f, canvasHeight * 0.18f)
            )

            // D. ARMS / FRONT PAWS
            drawOval(
                color = darkOrange,
                topLeft = Offset(canvasWidth * 0.22f, canvasHeight * 0.6f),
                size = Size(canvasWidth * 0.12f, canvasHeight * 0.18f)
            )
            drawOval(
                color = darkOrange,
                topLeft = Offset(canvasWidth * 0.66f, canvasHeight * 0.6f),
                size = Size(canvasWidth * 0.12f, canvasHeight * 0.18f)
            )

            // E. FLUFFY CIRCULAR MANE BACKGROUND
            val maneCenterX = canvasWidth * 0.5f
            val maneCenterY = canvasHeight * 0.35f
            val maneRadius = canvasWidth * 0.32f

            drawCircle(color = maneColor, radius = maneRadius, center = Offset(maneCenterX, maneCenterY))
            
            // Fluffy mane tufts
            val tuftPositions = listOf(
                Offset(canvasWidth * 0.3f, canvasHeight * 0.18f),
                Offset(canvasWidth * 0.7f, canvasHeight * 0.18f),
                Offset(canvasWidth * 0.2f, canvasHeight * 0.35f),
                Offset(canvasWidth * 0.8f, canvasHeight * 0.35f),
                Offset(canvasWidth * 0.3f, canvasHeight * 0.5f),
                Offset(canvasWidth * 0.7f, canvasHeight * 0.5f),
                Offset(canvasWidth * 0.5f, canvasHeight * 0.54f)
            )
            for (pos in tuftPositions) {
                drawCircle(color = maneDark, radius = 24f, center = pos)
            }

            // F. EARS
            drawCircle(color = bodyColor, radius = 18f, center = Offset(canvasWidth * 0.32f, canvasHeight * 0.18f))
            drawCircle(color = maneColor, radius = 10f, center = Offset(canvasWidth * 0.32f, canvasHeight * 0.18f))
            drawCircle(color = bodyColor, radius = 18f, center = Offset(canvasWidth * 0.68f, canvasHeight * 0.18f))
            drawCircle(color = maneColor, radius = 10f, center = Offset(canvasWidth * 0.68f, canvasHeight * 0.18f))

            // G. HEAD FACE BASE
            drawCircle(color = bodyColor, radius = canvasWidth * 0.22f, center = Offset(maneCenterX, maneCenterY))

            // H. EYES & HIGHLIGHTS
            drawOval(
                color = eyeColor,
                topLeft = Offset(canvasWidth * 0.41f, canvasHeight * 0.3f),
                size = Size(18f, 26f)
            )
            drawOval(
                color = eyeColor,
                topLeft = Offset(canvasWidth * 0.53f, canvasHeight * 0.3f),
                size = Size(18f, 26f)
            )
            drawCircle(color = Color.White, radius = 4f, center = Offset(canvasWidth * 0.43f, canvasHeight * 0.32f))
            drawCircle(color = Color.White, radius = 4f, center = Offset(canvasWidth * 0.55f, canvasHeight * 0.32f))

            // I. SNOUT & NOSE
            drawOval(
                color = innerSnout,
                topLeft = Offset(canvasWidth * 0.41f, canvasHeight * 0.36f),
                size = Size(canvasWidth * 0.18f, canvasHeight * 0.1f)
            )
            val nosePath = Path().apply {
                moveTo(canvasWidth * 0.46f, canvasHeight * 0.38f)
                lineTo(canvasWidth * 0.54f, canvasHeight * 0.38f)
                lineTo(canvasWidth * 0.5f, canvasHeight * 0.42f)
                close()
            }
            drawPath(path = nosePath, color = maneDark)

            // J. ANIMATED TALKING MOUTH
            val mouthCenterY = canvasHeight * 0.44f
            val mouthPath = Path().apply {
                moveTo(canvasWidth * 0.45f, mouthCenterY)
                quadraticBezierTo(
                    canvasWidth * 0.5f, mouthCenterY + (12f * mouthScaleY),
                    canvasWidth * 0.55f, mouthCenterY
                )
            }
            drawPath(
                path = mouthPath,
                color = eyeColor,
                style = Stroke(width = 5f)
            )

            // K. ROSY CHEEKS
            drawCircle(color = cheekColor.copy(alpha = 0.6f), radius = 12f, center = Offset(canvasWidth * 0.35f, canvasHeight * 0.37f))
            drawCircle(color = cheekColor.copy(alpha = 0.6f), radius = 12f, center = Offset(canvasWidth * 0.65f, canvasHeight * 0.37f))
        }
    }
}

/**
 * Speech Bubble Composable positioned above Leo the Lion.
 */
@Composable
private fun SpeechBubble(text: String) {
    Box(
        modifier = Modifier
            .widthIn(max = 180.dp)
            .shadow(4.dp, shape = RoundedCornerShape(16.dp))
            .background(Color.White, shape = RoundedCornerShape(16.dp))
            .border(2.dp, Color(0xFFFFC107), shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037),
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
    }
}