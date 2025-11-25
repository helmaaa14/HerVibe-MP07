package com.example.hervibe.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// Material Design 3 Shape System dengan smooth corners
val HerVibeShapes = Shapes(
    // Extra Small - untuk chip, badge
    extraSmall = RoundedCornerShape(4.dp),

    // Small - untuk button kecil, input field
    small = RoundedCornerShape(8.dp),

    // Medium - untuk card, dialog
    medium = RoundedCornerShape(12.dp),

    // Large - untuk bottom sheet, large card
    large = RoundedCornerShape(16.dp),

    // Extra Large - untuk modal, full screen card
    extraLarge = RoundedCornerShape(28.dp)
)

// Custom shapes untuk kebutuhan khusus
val TopRoundedShape = RoundedCornerShape(
    topStart = 24.dp,
    topEnd = 24.dp,
    bottomStart = 0.dp,
    bottomEnd = 0.dp
)

val BottomRoundedShape = RoundedCornerShape(
    topStart = 0.dp,
    topEnd = 0.dp,
    bottomStart = 24.dp,
    bottomEnd = 24.dp
)

val CircleShape = RoundedCornerShape(50)