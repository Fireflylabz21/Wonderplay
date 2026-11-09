package com.wonderplay.academy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import com.wonderplay.academy.ui.components.AruneshAvatarGuide
import com.wonderplay.academy.ui.components.LionAvatarGuide
import com.wonderplay.academy.ui.components.RoseAvatarGuide
import com.wonderplay.academy.ui.components.TejuAvatarGuide
import com.wonderplay.academy.ui.components.rememberAdvancedLionAvatarState
import kotlinx.coroutines.delay

data class KaraokeLine(val text: String, val freq: Double)

data class KaraokeSong(
    val title: String,
    val emoji: String,
    val lines: List<KaraokeLine>,
    val isTamil: Boolean = false
)

data class ChimeBar(val note: String, val label: String, val freq: Double, val color: Color)

@Composable
fun RhymeScreen(
    ttsManager: TtsManager,
    onStarEarned: () -> Unit,
    onImmersiveChanged: (Boolean) -> Unit,
    currentAvatarName: String = "Leo"
) {
    val songs = listOf(
        KaraokeSong(
            "Twinkle Twinkle Little Star", "⭐",
            listOf(
                KaraokeLine("Twinkle, twinkle, little star,", 261.63),
                KaraokeLine("How I wonder what you are!", 293.66),
                KaraokeLine("Up above the world so high,", 329.63),
                KaraokeLine("Like a diamond in the sky.", 349.23),
                KaraokeLine("Twinkle, twinkle, little star,", 392.00),
                KaraokeLine("How I wonder what you are!", 440.00)
            )
        ),
        KaraokeSong(
            "Baa Baa Black Sheep", "🐑",
            listOf(
                KaraokeLine("Baa baa black sheep, have you any wool?", 261.63),
                KaraokeLine("Yes sir, yes sir, three bags full!", 329.63),
                KaraokeLine("One for the master, and one for the dame,", 392.00),
                KaraokeLine("And one for the little boy down the lane.", 523.25)
            )
        ),
        KaraokeSong(
            "The Wheels on the Bus", "🚌",
            listOf(
                KaraokeLine("The wheels on the bus go round and round,", 293.66),
                KaraokeLine("Round and round, round and round.", 329.63),
                KaraokeLine("The wheels on the bus go round and round,", 349.23),
                KaraokeLine("All through the town!", 392.00)
            )
        ),
        KaraokeSong(
            "நிலா நிலா ஓடி வா (Tamil)", "🌙",
            listOf(
                KaraokeLine("நிலா நிலா ஓடி வா, நில்லாமல் ஓடி வா,", 329.63),
                KaraokeLine("மலை மீது ஏறி வா, மல்லிகைப் பூ சூடி வா!", 392.00),
                KaraokeLine("வட்ட வட்ட நிலாவே, வண்ண வண்ண நிலாவே,", 440.00),
                KaraokeLine("பட்டுப் போல ஒளி வீசி, பாப்பாவிடம் ஓடி வா!", 523.25)
            ),
            isTamil = true
        )
    )

    val chimeBars = listOf(
        ChimeBar("C", "Do", 261.63, Color(0xFFF87171)),
        ChimeBar("D", "Re", 293.66, Color(0xFFFB923C)),
        ChimeBar("E", "Mi", 329.63, Color(0xFFFBBF24)),
        ChimeBar("F", "Fa", 349.23, Color(0xFF34D399)),
        ChimeBar("G", "Sol", 392.00, Color(0xFF38BDF8)),
        ChimeBar("A", "La", 440.00, Color(0xFF818CF8)),
        ChimeBar("B", "Ti", 493.88, Color(0xFFC084FC)),
        ChimeBar("C²", "Do", 523.25, Color(0xFFF472B6))
    )

    var activeSong by remember { mutableStateOf<KaraokeSong?>(null) }
    var activeLineIdx by remember { mutableStateOf(-1) }
    var isPlaying by remember { mutableStateOf(false) }
    var showMelodyStudio by remember { mutableStateOf(false) }
    val lionAvatarState = rememberAdvancedLionAvatarState()

    LaunchedEffect(activeSong) {
        onImmersiveChanged(activeSong != null)
    }

    LaunchedEffect(isPlaying, activeSong) {
        if (isPlaying && activeSong != null) {
            val song = activeSong!!
            for (i in song.lines.indices) {
                if (!isPlaying) break
                activeLineIdx = i
                val line = song.lines[i]
                ttsManager.speak(line.text, song.isTamil)
                AudioSynthesizer.playChime(line.freq, 500)
                delay(1800)
            }
            isPlaying = false
            activeLineIdx = -1
            onStarEarned()
        }
    }

    if (activeSong == null) {
        // Catalog Hub (40% Top Avatar, 60% Bottom Card Tiles Grid) matching Learn Screen UI
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Top Half (40%): Avatar Guide & Mode Toggle
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                when (currentAvatarName) {
                    "Arunesh" -> AruneshAvatarGuide(
                        onAvatarClick = { ttsManager.speak("Pick a rhyme or song to sing along!") }
                    )
                    "Teju" -> TejuAvatarGuide(
                        onAvatarClick = { ttsManager.speak("Pick a rhyme or song to sing along!") }
                    )
                    "Rose" -> RoseAvatarGuide(
                        onAvatarClick = { ttsManager.speak("Pick a rhyme or song to sing along!") }
                    )
                    else -> LionAvatarGuide(
                        avatarState = lionAvatarState,
                        modifier = Modifier.size(110.dp)
                    ) {
                        lionAvatarState.speak("Pick a rhyme or song to sing along!")
                    }
                }
                    Spacer(modifier = Modifier.height(4.dp))
                    Button(
                        onClick = { showMelodyStudio = !showMelodyStudio },
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFEF3C7)),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = if (showMelodyStudio) "🎤 Switch to Rhymes" else "🎵 Switch to Melody Studio",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF92400E)
                        )
                    }
                }
            }

            // Second Half (60%): Cards Grid (Songs or Melody Studio)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.6f)
            ) {
                if (showMelodyStudio) {
                    Card(
                        shape = RoundedCornerShape(32.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Tap Chime Bars to Make Music! 🎹",
                                fontSize = 13.sp,
                                color = Color(0xFF64748B),
                                fontWeight = FontWeight.Bold
                            )

                            chimeBars.forEach { bar ->
                                Button(
                                    onClick = {
                                        AudioSynthesizer.playChime(bar.freq, 400)
                                        onStarEarned()
                                    },
                                    shape = RoundedCornerShape(16.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = bar.color),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                        .padding(vertical = 1.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(bar.note, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                        Text(bar.label, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                    }
                                }
                            }
                        }
                    }
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(songs) { song ->
                            Card(
                                shape = RoundedCornerShape(28.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                                modifier = Modifier
                                    .height(140.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        activeSong = song
                                        isPlaying = true
                                        lionAvatarState.speak("Let's sing ${song.title}!")
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
                                        Text(text = song.emoji, fontSize = 24.sp)
                                    }
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = song.title,
                                        fontSize = 12.sp,
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
        }
    } else {
        // Immersive Karaoke Song Viewer
        val song = activeSong!!

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F8F3))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Big Tactile Karaoke Tile Canvas
            Card(
                shape = RoundedCornerShape(40.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        LionAvatarGuide(
                            avatarState = lionAvatarState,
                            modifier = Modifier.size(100.dp)
                        ) {
                            isPlaying = !isPlaying
                        }

                        Text(
                            text = "${song.emoji} ${song.title}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E293B),
                            textAlign = TextAlign.Center
                        )

                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(vertical = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(song.lines.indices.toList()) { idx ->
                                val line = song.lines[idx]
                                val isHighlighted = (idx == activeLineIdx)
                                Surface(
                                    color = if (isHighlighted) Color(0xFFFEF3C7) else Color(0xFFF8FAFC),
                                    shape = RoundedCornerShape(16.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = line.text,
                                        fontSize = if (isHighlighted) 18.sp else 15.sp,
                                        fontWeight = if (isHighlighted) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isHighlighted) Color(0xFFD97706) else Color(0xFF334155),
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.padding(12.dp)
                                    )
                                }
                            }
                        }

                        Button(
                            onClick = { isPlaying = !isPlaying },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF59E0B)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = if (isPlaying) "⏸️ Pause Singing" else "▶️ Sing Along!",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }

                    // Top Right Home Icon Button inside karaoke tile
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .size(44.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFFEF3C7))
                            .clickable {
                                isPlaying = false
                                activeSong = null
                                activeLineIdx = -1
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
