package com.example.hervibe.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cycle_logs")
data class CycleLog(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val startDate: Long, // Timestamp hari pertama haid

    val endDate: Long?, // Timestamp hari terakhir haid (nullable, bisa belum selesai)

    val flowLevel: String // Ringan, Sedang, Berat
)

// Enum untuk Flow Level
enum class FlowLevel(val displayName: String) {
    LIGHT("Ringan"),
    MEDIUM("Sedang"),
    HEAVY("Berat");

    companion object {
        fun fromString(value: String): FlowLevel {
            return values().find { it.name == value } ?: MEDIUM
        }
    }
}