package com.wonderplay.academy.ui.screens

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
import com.wonderplay.academy.R
import com.wonderplay.academy.audio.AudioSynthesizer

data class AvatarItem(
    val name: String,
    val emoji: String? = null,
    val drawableRes: Int? = null
)

@Composable
fun ParentGateDialog(
    onDismiss: () -> Unit,
    onVerified: () -> Unit
) {
    var answerInput by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF9F8F3)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Bar with Title and Home Icon in Top Right
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("🛡️", fontSize = 24.sp)
                    Text("Adult Verification Gate", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                }

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFFEF3C7))
                        .clickable { onDismiss() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🏠", fontSize = 22.sp)
                }
            }

            // Center Content
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Solve this simple math problem to access parent settings:", fontSize = 14.sp, color = Color(0xFF64748B), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
                
                Surface(
                    color = Color(0xFFFFF1F2),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Calculate: 7 + 8 = ?",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFE11D48),
                        modifier = Modifier.padding(24.dp),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }

                OutlinedTextField(
                    value = answerInput,
                    onValueChange = { answerInput = it },
                    placeholder = { Text("Enter answer...") },
                    shape = RoundedCornerShape(16.dp),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                if (showError) {
                    Text("Incorrect answer, please try 15!", color = Color(0xFFE11D48), fontSize = 14.sp, fontWeight = FontWeight.Bold)
                }
            }

            // Bottom Button
            Button(
                onClick = {
                    if (answerInput.trim() == "15") {
                        AudioSynthesizer.playChime(659.25)
                        onVerified()
                    } else {
                        AudioSynthesizer.playChime(220.00)
                        showError = true
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D48)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Unlock Dashboard", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun ParentDashboardDialog(
    onDismiss: () -> Unit,
    currentPhaseKey: String,
    onPhaseChanged: (String) -> Unit,
    currentLanguage: String,
    onLanguageChanged: (String) -> Unit,
    screenTimerMinutes: Int,
    onTimerChanged: (Int) -> Unit,
    onOpenHabits: () -> Unit
) {
    var selectedTimer by remember { mutableStateOf(screenTimerMinutes) }
    var selectedPhase by remember { mutableStateOf(currentPhaseKey) }
    var selectedLang by remember { mutableStateOf(currentLanguage) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF9F8F3)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Bar with Title and Home Icon in Top Right
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("⚙️", fontSize = 24.sp)
                    Text("Parent Settings & Controls", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E293B))
                }

                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color(0xFFFEF3C7))
                        .clickable { onDismiss() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🏠", fontSize = 22.sp)
                }
            }

            // Scrollable / Column Content
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(vertical = 16.dp)
            ) {
                Surface(
                    color = Color(0xFFF0FDF4),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("🛡️ COPPA Safe Sandbox", fontWeight = FontWeight.Bold, color = Color(0xFF166534), fontSize = 14.sp)
                        Text("Zero ads, zero tracking, secure offline storage.", fontSize = 12.sp, color = Color(0xFF15803D))
                    }
                }

                // Age / Phase Selection
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("👶 Age / Curriculum Phase:", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF1E293B))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("phase1" to "🌱 2-3y", "phase2" to "🎨 4-5y", "phase3" to "🚀 6-7y").forEach { (key, label) ->
                            Button(
                                onClick = {
                                    selectedPhase = key
                                    onPhaseChanged(key)
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedPhase == key) Color(0xFFE11D48) else Color(0xFFF1F5F9)
                                ),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    label,
                                    fontSize = 12.sp,
                                    color = if (selectedPhase == key) Color.White else Color(0xFF475569),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                // Language Selection
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("🗣️ Content Language:", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF1E293B))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf("English", "Hindi", "Tamil").forEach { lang ->
                            Button(
                                onClick = {
                                    selectedLang = lang
                                    onLanguageChanged(lang)
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedLang == lang) Color(0xFF0284C7) else Color(0xFFF1F5F9)
                                ),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    lang,
                                    fontSize = 12.sp,
                                    color = if (selectedLang == lang) Color.White else Color(0xFF475569),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                // Screen Timer Lock Control
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("⏱️ Screen Lock Timer Limit:", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF1E293B))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listOf(15, 30, 45, 60).forEach { mins ->
                            Button(
                                onClick = {
                                    selectedTimer = mins
                                    onTimerChanged(mins)
                                },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (selectedTimer == mins) Color(0xFFD97706) else Color(0xFFF1F5F9)
                                ),
                                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 8.dp),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    "${mins}m",
                                    fontSize = 12.sp,
                                    color = if (selectedTimer == mins) Color.White else Color(0xFF475569),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                // Habits Management
                Button(
                    onClick = onOpenHabits,
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFEF3C7)),
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Text("⭐ Manage Daily Routine Habits", color = Color(0xFF92400E), fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }
            }

            // Bottom Save & Return Button
            Button(
                onClick = onDismiss,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E293B)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text("Save & Return", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun AvatarDialog(
    onDismiss: () -> Unit,
    onAvatarSelected: (AvatarItem) -> Unit
) {
    val avatars = listOf(
        AvatarItem("Arunesh", drawableRes = R.drawable.arunesh_avatar),
        AvatarItem("Teju", drawableRes = R.drawable.teju_avatar),
        AvatarItem("Rose", drawableRes = R.drawable.rose_avatar),
        AvatarItem("Leo", emoji = "🦁"),
        AvatarItem("Panda", emoji = "🐼"),
        AvatarItem("Bunny", emoji = "🐰"),
        AvatarItem("Fox", emoji = "🦊"),
        AvatarItem("Owl", emoji = "🦉"),
        AvatarItem("Unicorn", emoji = "🦄"),
        AvatarItem("Dolphin", emoji = "🐬"),
        AvatarItem("Dino", emoji = "🦖")
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        shape = RoundedCornerShape(28.dp),
        title = { Text("Pick Your Hero & Theme! 🐾", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("Choose your avatar hero or an animal buddy to change your companion and app theme:", fontSize = 12.sp, color = Color(0xFF64748B))
                
                // Grid of avatars
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    avatars.chunked(3).forEach { rowItems ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            rowItems.forEach { avatar ->
                                Button(
                                    onClick = {
                                        onAvatarSelected(avatar)
                                    },
                                    shape = RoundedCornerShape(20.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFEF3C7)),
                                    modifier = Modifier.size(64.dp),
                                    contentPadding = PaddingValues(4.dp)
                                ) {
                                    if (avatar.drawableRes != null) {
                                        Image(
                                            painter = painterResource(id = avatar.drawableRes),
                                            contentDescription = avatar.name,
                                            modifier = Modifier
                                                .size(48.dp)
                                                .clip(CircleShape)
                                        )
                                    } else {
                                        Text(avatar.emoji ?: "🌟", fontSize = 28.sp)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss, shape = RoundedCornerShape(16.dp)) {
                Text("Done")
            }
        }
    )
}
