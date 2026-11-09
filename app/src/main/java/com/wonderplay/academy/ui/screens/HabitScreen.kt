package com.wonderplay.academy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wonderplay.academy.audio.AudioSynthesizer
import com.wonderplay.academy.audio.TtsManager
import com.wonderplay.academy.data.model.Habit
import com.wonderplay.academy.ui.components.AruneshAvatarGuide
import com.wonderplay.academy.ui.components.LionAvatarGuide
import com.wonderplay.academy.ui.components.RoseAvatarGuide
import com.wonderplay.academy.ui.components.TejuAvatarGuide
import com.wonderplay.academy.ui.components.rememberAdvancedLionAvatarState

@Composable
fun HabitScreen(
    habits: MutableList<Habit>,
    ttsManager: TtsManager,
    onStarEarned: () -> Unit,
    currentAvatarName: String = "Leo"
) {
    var habitList by remember { mutableStateOf(habits.toList()) }
    val lionAvatarState = rememberAdvancedLionAvatarState()

    // Catalog Hub Layout (40% Top Avatar, 60% Bottom Card Tiles Grid) matching Learn Screen UI
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
                    onAvatarClick = { ttsManager.speak("Complete your daily habits and earn stars!") }
                )
                "Teju" -> TejuAvatarGuide(
                    onAvatarClick = { ttsManager.speak("Complete your daily habits and earn stars!") }
                )
                "Rose" -> RoseAvatarGuide(
                    onAvatarClick = { ttsManager.speak("Complete your daily habits and earn stars!") }
                )
                else -> LionAvatarGuide(
                    avatarState = lionAvatarState,
                    modifier = Modifier.size(130.dp)
                ) {
                    lionAvatarState.speak("Complete your daily habits and earn stars!")
                }
            }
        }

        // Second Half (60%): Habit Cards Grid
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(habitList) { habit ->
                    Card(
                        shape = RoundedCornerShape(28.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (habit.done) Color(0xFFF0FDF4) else Color.White
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        modifier = Modifier
                            .height(140.dp)
                            .fillMaxWidth()
                            .clickable {
                                habit.done = !habit.done
                                AudioSynthesizer.playChime(659.25)
                                if (habit.done) {
                                    ttsManager.speak("Wonderful job! ${habit.name} completed!")
                                    lionAvatarState.speak("Wonderful! ${habit.name} completed!")
                                    onStarEarned()
                                }
                                habitList = habitList.toList()
                            }
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(RoundedCornerShape(16.dp))
                                    .background(if (habit.done) Color(0xFFDCFCE7) else Color(0xFFFEF3C7)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = if (habit.done) "✅" else habit.icon, fontSize = 24.sp)
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = habit.name,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E293B),
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                            Text(
                                text = if (habit.done) "Completed!" else habit.tip,
                                fontSize = 10.sp,
                                color = if (habit.done) Color(0xFF166534) else Color(0xFFD97706),
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}
