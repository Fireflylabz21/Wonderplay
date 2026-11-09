package com.wonderplay.academy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wonderplay.academy.audio.AudioSynthesizer
import com.wonderplay.academy.audio.TtsManager
import com.wonderplay.academy.data.model.QuizQuestion
import com.wonderplay.academy.ui.components.AruneshAvatarGuide
import com.wonderplay.academy.ui.components.LionAvatarGuide
import com.wonderplay.academy.ui.components.RoseAvatarGuide
import com.wonderplay.academy.ui.components.TejuAvatarGuide
import com.wonderplay.academy.ui.components.rememberAdvancedLionAvatarState

@Composable
fun GameScreen(
    questions: List<QuizQuestion>,
    ttsManager: TtsManager,
    onStarEarned: () -> Unit,
    currentAvatarName: String = "Leo"
) {
    var qIdx by remember { mutableStateOf(0) }
    var feedbackMessage by remember { mutableStateOf("") }
    val q = questions[qIdx]
    val lionAvatarState = rememberAdvancedLionAvatarState()

    LaunchedEffect(qIdx) {
        ttsManager.speak(q.question)
        lionAvatarState.speak(q.question)
    }

    // Catalog Layout Hub (40% Top Avatar, 60% Bottom Quiz Card Hub) matching Learn Screen UI
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Top Half (40%): Avatar Guide
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f),
            contentAlignment = Alignment.Center
        ) {
            when (currentAvatarName) {
                "Arunesh" -> AruneshAvatarGuide(
                    onAvatarClick = { ttsManager.speak(q.question) }
                )
                "Teju" -> TejuAvatarGuide(
                    onAvatarClick = { ttsManager.speak(q.question) }
                )
                "Rose" -> RoseAvatarGuide(
                    onAvatarClick = { ttsManager.speak(q.question) }
                )
                else -> LionAvatarGuide(
                    avatarState = lionAvatarState,
                    modifier = Modifier.size(130.dp)
                ) {
                    lionAvatarState.speak(q.question)
                }
            }
        }

        // Second Half (60%): Quiz Question & Options Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f),
            contentAlignment = Alignment.TopCenter
        ) {
            Card(
                shape = RoundedCornerShape(32.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = q.question,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B),
                        textAlign = TextAlign.Center
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        q.options.forEach { option ->
                            Button(
                                onClick = {
                                    if (option.correct) {
                                        AudioSynthesizer.playChime(659.25)
                                        ttsManager.speak("Wonderful! That is correct!")
                                        lionAvatarState.speak("Wonderful! That is correct!")
                                        feedbackMessage = "🎉 Correct! Great job!"
                                        onStarEarned()
                                        android.os.Handler(android.os.Looper.getMainLooper()).postDelayed({
                                            feedbackMessage = ""
                                            qIdx = (qIdx + 1) % questions.size
                                        }, 1200)
                                    } else {
                                        AudioSynthesizer.playChime(220.00)
                                        ttsManager.speak("Oops! Try again!")
                                        lionAvatarState.speak("Oops! Try again!")
                                        feedbackMessage = "💡 Try another one!"
                                    }
                                },
                                shape = RoundedCornerShape(20.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFEF3C7)),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                                    modifier = Modifier.padding(6.dp)
                                ) {
                                    Text(text = option.icon, fontSize = 24.sp)
                                    Text(text = option.label, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF92400E))
                                }
                            }
                        }
                    }

                    if (feedbackMessage.isNotEmpty()) {
                        Text(
                            text = feedbackMessage,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFD97706),
                            textAlign = TextAlign.Center
                        )
                    }

                    Button(
                        onClick = {
                            qIdx = (qIdx + 1) % questions.size
                            feedbackMessage = ""
                            AudioSynthesizer.playChime(523.25)
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF59E0B)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Next Question", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    }
                }
            }
        }
    }
}
