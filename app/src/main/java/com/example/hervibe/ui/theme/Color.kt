package com.example.hervibe.ui.theme

import androidx.compose.ui.graphics.Color

// Color Palette dari Referensi Gambar
val PrimroseGarden = Color(0xFFF297A0)  // Pink utama
val Pinktone = Color(0xFFF9D0CE)        // Pink muda/pastel
val LimeLollipop = Color(0xFFB8BB79)    // Hijau sage
val YuccaWhite = Color(0xFFF3EBD8)      // Cream/Putih kekuningan

// Light Theme Colors - Berdasarkan Palette
val LightPrimary = Color(0xFFE85D75)         // Pink cerah untuk primary
val LightPrimaryVariant = PrimroseGarden     // Pink medium
val LightSecondary = LimeLollipop            // Sage green
val LightBackground = Color(0xFFFFFBFE)      // Putih bersih
val LightSurface = Pinktone                  // Pink muda untuk card
val LightOnPrimary = Color(0xFFFFFFFF)       // Putih untuk text di primary
val LightOnSurface = Color(0xFF2C2C2C)       // Charcoal untuk text

// Dark Theme Colors
val DarkPrimary = Color(0xFFFFB4C1)          // Pink terang untuk dark
val DarkPrimaryVariant = Color(0xFFFF8FA3)
val DarkSecondary = Color(0xFFC5D99E)        // Sage green terang
val DarkBackground = Color(0xFF1C1B1F)       // Hitam keunguan
val DarkSurface = Color(0xFF2B2930)          // Abu gelap
val DarkOnPrimary = Color(0xFF5E1133)        // Maroon gelap
val DarkOnSurface = Color(0xFFECE0E4)        // Pink pucat untuk text

// Neutral Colors
val DarkCharcoal = Color(0xFF2C2C2C)
val MediumGray = Color(0xFF757575)
val LightGray = Color(0xFFE0E0E0)
val VeryLightGray = Color(0xFFF5F5F5)

// Mood Colors (untuk emoji/indicator)
val HappyYellow = Color(0xFFFFD700)
val SadBlue = Color(0xFF6B9BD1)
val IrritableRed = Color(0xFFE85D75)
val AnxiousPurple = Color(0xFFB08BBB)
val EnergeticOrange = Color(0xFFFFAB73)
val CalmGreen = LimeLollipop

// Cycle Indicator Colors
val PeriodRed = Color(0xFFE85D75)
val OvulationBlue = Color(0xFF6B9BD1)
val PredictionPink = PrimroseGarden

// Functional Colors
val ErrorRed = Color(0xFFBA1A1A)
val WarningOrange = Color(0xFFFF9800)
val SuccessGreen = Color(0xFF81C784)

// Gradient Colors (untuk background dekoratif)
val GradientPinkStart = Color(0xFFE85D75)
val GradientPinkEnd = Color(0xFFF297A0)
val GradientCreamStart = YuccaWhite
val GradientCreamEnd = Pinktone