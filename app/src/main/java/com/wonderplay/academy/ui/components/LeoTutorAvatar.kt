package com.wonderplay.academy.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

enum class AvatarState {
    IDLE, TALKING, CELEBRATING
}

@Composable
fun LeoTutorAvatar(
    state: AvatarState,
    speechBubbleText: String? = null,
    onAvatarClicked: () -> Unit = {}
) {
    // Bouncy scale animation based on state
    val infiniteTransition = rememberInfiniteTransition(label = "avatar")
    
    val bounceScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (state == AvatarState.TALKING || state == AvatarState.CELEBRATING) 1.12f else 1.02f,
        animationSpec = infiniteRepeatable(
            animation = tween(400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "bounce"
    )

    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = -2f,
        targetValue = if (state == AvatarState.CELEBRATING) 6f else 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(300, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "rotate"
    )

    Row(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onAvatarClicked() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Avatar Face Box
        Box(
            modifier = Modifier
                .size(64.dp)
                .scale(bounceScale)
                .clip(CircleShape)
                .background(Color(0xFFFEF3C7)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = when (state) {
                    AvatarState.IDLE -> "🦁"
                    AvatarState.TALKING -> "🗣️"
                    AvatarState.CELEBRATING -> "🎉"
                },
                fontSize = 36.sp
            )
        }

        // Speech Bubble
        if (!speechBubbleText.isNullOrEmpty()) {
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(20.dp),
                shadowElevation = 4.dp,
                modifier = Modifier.widthIn(max = 220.dp)
            ) {
                Text(
                    text = speechBubbleText,
                    fontSize = 13.sp,
                    color = Color(0xFF1E293B),
                    modifier = Modifier.padding(12.dp)
                )
            }
        }
    }
}
