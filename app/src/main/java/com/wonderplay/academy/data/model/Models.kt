package com.wonderplay.academy.data.model

data class FlashcardItem(
    val title: String,
    val subtitle: String,
    val phonics: String,
    val icon: String,
    val audioDesc: String,
    val isTamil: Boolean = false
)

data class CurriculumCategory(
    val id: String,
    val name: String,
    val items: List<FlashcardItem>
)

data class CurriculumPhase(
    val id: String,
    val title: String,
    val categories: List<CurriculumCategory>
)

data class StoryPage(
    val sentence: String,
    val words: List<String>,
    val illustration: String
)

data class Story(
    val id: String,
    val title: String,
    val theme: String,
    val coverEmoji: String,
    val pages: List<StoryPage>,
    val moral: String
)

data class QuizOption(
    val icon: String,
    val label: String,
    val correct: Boolean
)

data class QuizQuestion(
    val question: String,
    val options: List<QuizOption>
)

data class Habit(
    val id: String,
    val name: String,
    val time: String,
    val icon: String,
    var done: Boolean,
    val tip: String
)

data class RhymeLine(
    val text: String,
    val words: List<String>,
    val notes: List<Double>,
    val pitches: List<Float>
)

data class RhymeSong(
    val id: String,
    val title: String,
    val coverEmoji: String,
    val lang: String,
    val lines: List<RhymeLine>
)

data class MelodyNote(
    val n: String,
    val f: Double,
    val d: Long
)

data class MelodySong(
    val id: String,
    val name: String,
    val icon: String,
    val notes: List<MelodyNote>
)

data class ThemeSkin(
    val id: String,
    val name: String,
    val icon: String,
    val desc: String,
    val bgHex: Long,
    val headerBgHex: Long,
    val accentHex: Long
)
