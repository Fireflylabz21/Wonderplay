package com.wonderplay.academy.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wonderplay.academy.audio.TtsManager
import com.wonderplay.academy.data.repository.CurriculumRepository
import com.wonderplay.academy.ui.screens.*

class MainActivity : ComponentActivity() {
    private lateinit var ttsManager: TtsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        ttsManager = TtsManager(this)

        setContent {
            var showSplash by remember { mutableStateOf(true) }

            if (showSplash) {
                SplashScreen(onSplashFinished = { showSplash = false })
            } else {
                WonderPlayApp(ttsManager = ttsManager)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ttsManager.shutdown()
    }
}

@Composable
fun WonderPlayApp(ttsManager: TtsManager) {
    var currentTab by remember { mutableStateOf("learn") }
    var currentPhaseKey by remember { mutableStateOf("phase1") }
    var currentLanguage by remember { mutableStateOf("English") }
    var screenTimerMinutes by remember { mutableStateOf(30) }
    var starsCount by remember { mutableStateOf(24) }
    var kidAvatar by remember { mutableStateOf(AvatarItem("Arunesh", drawableRes = com.wonderplay.academy.R.drawable.arunesh_avatar)) }
    var kidName by remember { mutableStateOf("Arunesh") }

    var showParentGate by remember { mutableStateOf(false) }
    var showParentDashboard by remember { mutableStateOf(false) }
    var showAvatarModal by remember { mutableStateOf(false) }
    var showHabitsModal by remember { mutableStateOf(false) }
    var isImmersiveMode by remember { mutableStateOf(false) }

    // Dynamic Background Theme mapping based on chosen avatar
    val dynamicBgColor = when (kidAvatar.name) {
        "Arunesh" -> Color(0xFFFFF7ED) // Warm orange golden tone matching Arunesh's shirt
        "Teju" -> Color(0xFFFDF2F8) // Soft Rose Pink tone
        "Rose" -> Color(0xFFFFF1F2) // Gentle Red/Rose tone
        "Leo" -> Color(0xFFF9F8F3) // Safari Honey
        "Panda" -> Color(0xFFF0FDF4) // Bamboo Green
        "Bunny" -> Color(0xFFFDF2F8) // Blossom Pink
        "Fox" -> Color(0xFFFFFBEB) // Autumn Amber
        "Owl" -> Color(0xFFF5F3FF) // Midnight Lilac
        "Unicorn" -> Color(0xFFFAF5FF) // Magic Lavender
        "Dolphin" -> Color(0xFFF0F9FF) // Ocean Aquamarine
        "Dino" -> Color(0xFFF4FDF4) // Prehistoric Moss
        else -> Color(0xFFF9F8F3)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = dynamicBgColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            // Header Top Bar with Habits Menu at Top Right (Hidden in Immersive Mode)
            if (!isImmersiveMode) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(dynamicBgColor)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFFFEF3C7))
                                .clickable { showAvatarModal = true },
                            contentAlignment = Alignment.Center
                        ) {
                            if (kidAvatar.drawableRes != null) {
                                Image(
                                    painter = painterResource(id = kidAvatar.drawableRes!!),
                                    contentDescription = kidAvatar.name,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .clip(CircleShape)
                                )
                            } else {
                                Text(text = kidAvatar.emoji ?: "🦁", fontSize = 24.sp)
                            }
                        }

                        Column {
                            Text(text = kidName, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                Text("⭐", fontSize = 12.sp)
                                Text(text = "$starsCount", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD97706))
                            }
                        }
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                        // Parent Settings Icon Button (Locked behind Parent Gate)
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color(0xFF1E293B))
                                .clickable { showParentGate = true },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "⚙️", fontSize = 18.sp)
                        }
                    }
                }
            }

            // Main Content Viewport
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                when (currentTab) {
                    "learn" -> FlashcardScreen(
                        currentPhaseKey = currentPhaseKey,
                        phases = CurriculumRepository.phases,
                        ttsManager = ttsManager,
                        onStarEarned = { starsCount++ },
                        onImmersiveChanged = { isImmersiveMode = it },
                        currentAvatarName = kidAvatar.name
                    )
                    "stories" -> StoryScreen(
                        stories = CurriculumRepository.stories,
                        ttsManager = ttsManager,
                        onStarEarned = { starsCount += 2 },
                        onImmersiveChanged = { isImmersiveMode = it },
                        currentAvatarName = kidAvatar.name
                    )
                    "games" -> GameScreen(
                        questions = CurriculumRepository.quizQuestions,
                        ttsManager = ttsManager,
                        onStarEarned = { starsCount++ },
                        currentAvatarName = kidAvatar.name
                    )
                    "rhymes" -> RhymeScreen(
                        ttsManager = ttsManager,
                        onStarEarned = { starsCount++ },
                        onImmersiveChanged = { isImmersiveMode = it },
                        currentAvatarName = kidAvatar.name
                    )
                }
            }

            // Bottom Navigation Dock (Hidden in Immersive Mode)
            if (!isImmersiveMode) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = dynamicBgColor,
                    tonalElevation = 8.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        DockButton("🔤", "Learn", currentTab == "learn") { currentTab = "learn" }
                        DockButton("📖", "Stories", currentTab == "stories") { currentTab = "stories" }
                        DockButton("🎯", "Games", currentTab == "games") { currentTab = "games" }
                        DockButton("🎤", "Rhymes", currentTab == "rhymes") { currentTab = "rhymes" }
                    }
                }
            }
        }

        // Modals
        if (showParentGate) {
            ParentGateDialog(
                onDismiss = { showParentGate = false },
                onVerified = {
                    showParentGate = false
                    showParentDashboard = true
                }
            )
        }

        if (showParentDashboard) {
            ParentDashboardDialog(
                onDismiss = { showParentDashboard = false },
                currentPhaseKey = currentPhaseKey,
                onPhaseChanged = { currentPhaseKey = it },
                currentLanguage = currentLanguage,
                onLanguageChanged = { currentLanguage = it },
                screenTimerMinutes = screenTimerMinutes,
                onTimerChanged = { screenTimerMinutes = it },
                onOpenHabits = {
                    showParentDashboard = false
                    showHabitsModal = true
                }
            )
        }

        if (showAvatarModal) {
            AvatarDialog(
                onDismiss = { showAvatarModal = false },
                onAvatarSelected = { avatar ->
                    kidAvatar = avatar
                    kidName = avatar.name
                    showAvatarModal = false
                }
            )
        }

        if (showHabitsModal) {
            AlertDialog(
                onDismissRequest = { showHabitsModal = false },
                shape = RoundedCornerShape(28.dp),
                title = { Text("Daily Routine Habits ⭐", fontWeight = FontWeight.Bold) },
                text = {
                    Box(modifier = Modifier.height(300.dp)) {
                        HabitScreen(
                            habits = CurriculumRepository.habits,
                            ttsManager = ttsManager,
                            onStarEarned = { starsCount++ },
                            currentAvatarName = kidAvatar.name
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = { showHabitsModal = false }, shape = RoundedCornerShape(16.dp)) {
                        Text("Done")
                    }
                }
            )
        }
    }
}

@Composable
fun DockButton(icon: String, label: String, selected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selected) Color(0xFFFEF3C7) else Color.Transparent
        ),
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier.width(70.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = icon, fontSize = 22.sp)
            Text(
                text = label,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = if (selected) Color(0xFF92400E) else Color(0xFF64748B)
            )
        }
    }
}
