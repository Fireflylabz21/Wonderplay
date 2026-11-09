package com.wonderplay.academy.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wonderplay.academy.audio.TtsManager
import com.wonderplay.academy.data.model.CurriculumCategory
import com.wonderplay.academy.ui.components.AruneshAvatarGuide
import com.wonderplay.academy.ui.components.LionAvatarGuide
import com.wonderplay.academy.ui.components.RoseAvatarGuide
import com.wonderplay.academy.ui.components.TejuAvatarGuide
import com.wonderplay.academy.ui.components.rememberAdvancedLionAvatarState

@Composable
fun FlashcardScreen(
    currentPhaseKey: String,
    phases: Map<String, com.wonderplay.academy.data.model.CurriculumPhase>,
    ttsManager: TtsManager,
    onStarEarned: () -> Unit,
    onImmersiveChanged: (Boolean) -> Unit,
    currentAvatarName: String = "Leo"
) {
    val phase = phases[currentPhaseKey] ?: phases["phase1"]!!
    var selectedCategory by remember(currentPhaseKey) { mutableStateOf<CurriculumCategory?>(null) }
    var immersiveCardIdx by remember { mutableStateOf(0) }
    val lionAvatarState = rememberAdvancedLionAvatarState()

    // Handle physical device back button
    BackHandler(enabled = selectedCategory != null) {
        selectedCategory = null
    }

    LaunchedEffect(selectedCategory) {
        onImmersiveChanged(selectedCategory != null)
    }

    if (selectedCategory == null) {
        // Tier 1: Home / Catalog Hub (40% Top Avatar, 60% Bottom Card Tiles)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Top Half (40%): Dynamic Avatar Guide (Arunesh or Leo)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f),
                contentAlignment = Alignment.Center
            ) {
                when (currentAvatarName) {
                    "Arunesh" -> AruneshAvatarGuide(
                        onAvatarClick = { ttsManager.speak("Hi! I'm Arunesh! Pick a card topic to explore together!") }
                    )
                    "Teju" -> TejuAvatarGuide(
                        onAvatarClick = { ttsManager.speak("Hi! I'm Teju! Pick a card topic to explore together!") }
                    )
                    "Rose" -> RoseAvatarGuide(
                        onAvatarClick = { ttsManager.speak("Hi! I'm Rose! Pick a card topic to explore together!") }
                    )
                    else -> LionAvatarGuide(
                        avatarState = lionAvatarState,
                        modifier = Modifier.size(130.dp)
                    ) {
                        lionAvatarState.speak("Hi! Pick a card topic to explore together!")
                    }
                }
            }

            // Second Half (60%): Card Tiles Grid
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
                    items(phase.categories) { category ->
                        Card(
                            shape = RoundedCornerShape(28.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            modifier = Modifier
                                .height(130.dp)
                                .fillMaxWidth()
                                .clickable {
                                    selectedCategory = category
                                    immersiveCardIdx = 0
                                    if (currentAvatarName == "Arunesh") {
                                        ttsManager.speak("Let's explore ${category.name}!")
                                    } else {
                                        lionAvatarState.speak("Let's explore ${category.name}!")
                                    }
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
                                        .background(Color(0xFFFEF3C7)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = category.items.firstOrNull()?.icon ?: "🌟", fontSize = 24.sp)
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = category.name,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1E293B),
                                    textAlign = TextAlign.Center,
                                    maxLines = 2
                                )
                            }
                        }
                    }
                }
            }
        }
    } else {
        // Tier 2: Immersive Card Viewer (60% Content on top, 40% Avatar at bottom inside tile)
        val category = selectedCategory!!
        val card = category.items[immersiveCardIdx]

        LaunchedEffect(card) {
            if (currentAvatarName == "Arunesh") {
                ttsManager.speak(card.audioDesc)
            } else {
                lionAvatarState.speak(card.audioDesc)
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F8F3))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Big Tactile Card Display Canvas with 60% Content Top & 40% Avatar Bottom
            Card(
                shape = RoundedCornerShape(40.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 8.dp)
                    .pointerInput(Unit) {
                        detectTapGestures { offset ->
                            val width = size.width
                            val height = size.height
                            if (offset.x > width * 0.8f && offset.y < height * 0.15f) {
                                // Tap top-right home icon area -> Go home
                                selectedCategory = null
                            } else if (offset.x > width / 2f) {
                                // Tap Right Side -> Next Card (Sequential A -> B -> C...)
                                if (immersiveCardIdx < category.items.size - 1) {
                                    immersiveCardIdx++
                                } else {
                                    immersiveCardIdx = 0
                                }
                                if (currentAvatarName == "Arunesh") {
                                    ttsManager.speak(category.items[immersiveCardIdx].audioDesc)
                                } else {
                                    lionAvatarState.speak(category.items[immersiveCardIdx].audioDesc)
                                }
                                onStarEarned()
                            } else {
                                // Tap Left Side -> Previous Card
                                if (immersiveCardIdx > 0) {
                                    immersiveCardIdx--
                                } else {
                                    immersiveCardIdx = category.items.size - 1
                                }
                                if (currentAvatarName == "Arunesh") {
                                    ttsManager.speak(category.items[immersiveCardIdx].audioDesc)
                                } else {
                                    lionAvatarState.speak(category.items[immersiveCardIdx].audioDesc)
                                }
                            }
                        }
                    }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // 1. Content on top (60% proportion)
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.6f)
                        ) {
                            Text(text = card.icon, fontSize = 110.sp, modifier = Modifier.padding(bottom = 8.dp))
                            Text(text = card.title, fontSize = 64.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B), textAlign = TextAlign.Center)
                            Text(text = card.subtitle, fontSize = 28.sp, color = Color(0xFFD97706), fontWeight = FontWeight.Bold, textAlign = TextAlign.Center, modifier = Modifier.padding(top = 4.dp))
                            Text(text = card.phonics, fontSize = 20.sp, color = Color(0xFF64748B), textAlign = TextAlign.Center, modifier = Modifier.padding(top = 4.dp))
                        }

                        // 2. Avatar at bottom of content inside tile (40% proportion)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.4f),
                            contentAlignment = Alignment.Center
                        ) {
                            when (currentAvatarName) {
                                "Arunesh" -> AruneshAvatarGuide(
                                    initialSpeechText = card.audioDesc,
                                    onAvatarClick = { ttsManager.speak(card.audioDesc) }
                                )
                                "Teju" -> TejuAvatarGuide(
                                    initialSpeechText = card.audioDesc,
                                    onAvatarClick = { ttsManager.speak(card.audioDesc) }
                                )
                                "Rose" -> RoseAvatarGuide(
                                    initialSpeechText = card.audioDesc,
                                    onAvatarClick = { ttsManager.speak(card.audioDesc) }
                                )
                                else -> LionAvatarGuide(
                                    avatarState = lionAvatarState,
                                    onAvatarClick = {
                                        lionAvatarState.speak(card.audioDesc)
                                    }
                                )
                            }
                        }
                    }

                    // Top Right Home Icon Button inside flashcard tile
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(44.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFFEF3C7))
                            .clickable {
                                selectedCategory = null
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "🏠", fontSize = 22.sp)
                    }
                }
            }
        }
    }
}
