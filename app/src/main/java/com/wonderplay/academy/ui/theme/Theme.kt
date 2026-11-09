package com.wonderplay.academy.ui.theme

import androidx.compose.ui.graphics.Color
import com.wonderplay.academy.data.model.ThemeSkin

object WonderPlayThemes {
    val themes = mapOf(
        "safari" to ThemeSkin("safari", "Jungle Safari", "🦁", "Warm savanna honey, gentle greens", 0xFFF9F8F3, 0xFFF9F8F3, 0xFFD97706),
        "dino" to ThemeSkin("dino", "Prehistoric Dinos", "🦖", "Earthy moss green, fossils", 0xFFF4F7F4, 0xFFF4F7F4, 0xFF059669),
        "ocean" to ThemeSkin("ocean", "Under the Sea", "🌊", "Aquamarine mist, coral reefs", 0xFFF2F7F9, 0xFFF2F7F9, 0xFF0284C7),
        "space" to ThemeSkin("space", "Space Voyage", "🚀", "Cosmic lilac, glowing stars", 0xFFF2F1F8, 0xFFF2F1F8, 0xFF59507E),
        "fairytale" to ThemeSkin("fairytale", "Enchanted Fairytale", "🦄", "Blossom pink, gentle unicorns", 0xFFF7F1F4, 0xFFF7F1F4, 0xFF7C4C64),
        "vehicles" to ThemeSkin("vehicles", "Vehicles & Build", "🚜", "Warm clay sand, train tracks", 0xFFF6F2EB, 0xFFF6F2EB, 0xFF6E5B4B)
    )
}
