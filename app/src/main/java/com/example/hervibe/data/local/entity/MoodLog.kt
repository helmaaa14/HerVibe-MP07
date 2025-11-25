package com.example.hervibe.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mood_logs")
data class MoodLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val date: Long, // Timestamp dalam milliseconds

    val moodType: String, // Happy, Sad, Irritable, Anxious, Energetic, Calm

    val intensity: Int, // Skala 1-5

    val notes: String = "" // Catatan jurnal singkat (opsional)
)

// Enum untuk Mood Type
enum class MoodType(val displayName: String, val emoji: String) {
    HAPPY("Happy", "😊"),
    SAD("Sad", "😢"),
    IRRITABLE("Irritable", "😤"),
    ANXIOUS("Anxious", "😰"),
    ENERGETIC("Energetic", "⚡"),
    CALM("Calm", "😌");

    companion object {
        fun fromString(value: String): MoodType {
            return values().find { it.name == value } ?: CALM
        }
    }
}