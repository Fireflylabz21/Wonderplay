package com.wonderplay.academy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
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
import com.wonderplay.academy.data.model.Story
import com.wonderplay.academy.ui.components.AruneshAvatarGuide
import com.wonderplay.academy.ui.components.LionAvatarGuide
import com.wonderplay.academy.ui.components.RoseAvatarGuide
import com.wonderplay.academy.ui.components.TejuAvatarGuide
import com.wonderplay.academy.ui.components.rememberAdvancedLionAvatarState

@Composable
fun StoryScreen(
    stories: List<Story>,
    ttsManager: TtsManager,
    onStarEarned: () -> Unit,
    onImmersiveChanged: (Boolean) -> Unit,
    currentAvatarName: String = "Leo"
) {
    var activeStory by remember { mutableStateOf<Story?>(null) }
    var activePageIdx by remember { mutableStateOf(0) }
    val lionAvatarState = rememberAdvancedLionAvatarState()

    LaunchedEffect(activeStory) {
        onImmersiveChanged(activeStory != null)
    }

    if (activeStory == null) {
        // Tier 1: Story Catalog Hub (40% Top Avatar, 60% Bottom Card Tiles Grid) matching Learn Screen UI
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
                        onAvatarClick = { ttsManager.speak("Pick a story to read together!") }
                    )
                    "Teju" -> TejuAvatarGuide(
                        onAvatarClick = { ttsManager.speak("Pick a story to read together!") }
                    )
                    "Rose" -> RoseAvatarGuide(
                        onAvatarClick = { ttsManager.speak("Pick a story to read together!") }
                    )
                    else -> LionAvatarGuide(
                        avatarState = lionAvatarState,
                        modifier = Modifier.size(130.dp)
                    ) {
                        lionAvatarState.speak("Pick a story to read together!")
                    }
                }
            }

            // Second Half (60%): Story Cards Grid
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
                    items(stories) { story ->
                        Card(
                            shape = RoundedCornerShape(28.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                            modifier = Modifier
                                .height(140.dp)
                                .fillMaxWidth()
                                .clickable {
                                    activeStory = story
                                    activePageIdx = 0
                                    lionAvatarState.speak("Let's read ${story.title}!")
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
                                    Text(text = story.coverEmoji, fontSize = 24.sp)
                                }
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = story.title,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1E293B),
                                    textAlign = TextAlign.Center,
                                    maxLines = 1
                                )
                                Text(
                                    text = story.theme,
                                    fontSize = 10.sp,
                                    color = Color(0xFFD97706),
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    } else {
        // Tier 2: Immersive Full-Screen Story Tile with Avatar Inside & Swipe Navigation
        val story = activeStory!!
        val page = story.pages[activePageIdx]

        LaunchedEffect(page) {
            if (currentAvatarName == "Arunesh") {
                ttsManager.speak(page.sentence)
            } else {
                lionAvatarState.speak(page.sentence)
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
            // Big Tactile Story Tile Canvas with Swipe Gestures
            Card(
                shape = RoundedCornerShape(40.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 8.dp)
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures { _, dragAmount ->
                            if (dragAmount > 30) {
                                // Swipe Right -> Next Page
                                if (activePageIdx < story.pages.size - 1) {
                                    activePageIdx++
                                    lionAvatarState.speak(story.pages[activePageIdx].sentence)
                                } else {
                                    onStarEarned()
                                    activeStory = null
                                }
                            } else if (dragAmount < -30) {
                                // Swipe Left -> Previous Page
                                if (activePageIdx > 0) {
                                    activePageIdx--
                                    lionAvatarState.speak(story.pages[activePageIdx].sentence)
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
                        verticalArrangement = Arrangement.Top
                    ) {
                        // 1. Avatar on top inside the tile
                        when (currentAvatarName) {
                            "Arunesh" -> AruneshAvatarGuide(
                                initialSpeechText = page.sentence,
                                onAvatarClick = { ttsManager.speak(page.sentence) }
                            )
                            "Teju" -> TejuAvatarGuide(
                                initialSpeechText = page.sentence,
                                onAvatarClick = { ttsManager.speak(page.sentence) }
                            )
                            "Rose" -> RoseAvatarGuide(
                                initialSpeechText = page.sentence,
                                onAvatarClick = { ttsManager.speak(page.sentence) }
                            )
                            else -> LionAvatarGuide(
                                avatarState = lionAvatarState,
                                onAvatarClick = {
                                    lionAvatarState.speak(page.sentence)
                                }
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // 2. Story Content below avatar
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(text = page.illustration, fontSize = 70.sp, modifier = Modifier.padding(bottom = 16.dp))

                            Text(
                                text = page.sentence,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E293B),
                                textAlign = TextAlign.Center
                            )

                            if (activePageIdx == story.pages.size - 1) {
                                Spacer(modifier = Modifier.height(16.dp))
                                Surface(
                                    color = Color(0xFFFEF3C7),
                                    shape = RoundedCornerShape(16.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = "Moral: ${story.moral}",
                                        fontSize = 14.sp,
                                        color = Color(0xFF92400E),
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(12.dp)
                                    )
                                }
                            }
                        }
                    }

                    // Top Right Home Icon Button inside story tile
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(44.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFFEF3C7))
                            .clickable {
                                activeStory = null
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
