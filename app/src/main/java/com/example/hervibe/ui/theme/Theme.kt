package com.example.hervibe.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Light Color Scheme (Sesuai Palette Gambar)
private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,                    // Pink utama
    onPrimary = LightOnPrimary,                // Putih
    primaryContainer = LightPrimaryVariant,    // Pink medium
    onPrimaryContainer = DarkCharcoal,

    secondary = LightSecondary,                // Sage green
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFD4E7C5),    // Green muda
    onSecondaryContainer = DarkCharcoal,

    tertiary = YuccaWhite,                     // Cream
    onTertiary = DarkCharcoal,
    tertiaryContainer = Pinktone,              // Pink muda
    onTertiaryContainer = DarkCharcoal,

    error = ErrorRed,
    onError = Color.White,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    background = LightBackground,              // Putih bersih
    onBackground = DarkCharcoal,

    surface = Color.White,                     // Putih untuk card
    onSurface = DarkCharcoal,
    surfaceVariant = Pinktone,                 // Pink muda untuk variant
    onSurfaceVariant = MediumGray,

    outline = LightGray,
    outlineVariant = VeryLightGray,

    inverseSurface = DarkCharcoal,
    inverseOnSurface = LightBackground,
    inversePrimary = DarkPrimary
)

// Dark Color Scheme
private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = Color(0xFF7D1E4C),
    onPrimaryContainer = DarkPrimary,

    secondary = DarkSecondary,
    onSecondary = Color(0xFF1F3701),
    secondaryContainer = Color(0xFF3E5A1A),
    onSecondaryContainer = DarkSecondary,

    tertiary = Color(0xFFFFE4B5),
    onTertiary = DarkCharcoal,
    tertiaryContainer = Color(0xFF895E00),
    onTertiaryContainer = Color(0xFFFFE08C),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),

    background = DarkBackground,
    onBackground = DarkOnSurface,

    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = Color(0xFF4D444C),
    onSurfaceVariant = Color(0xFFCFC4CC),

    outline = Color(0xFF988E96),
    outlineVariant = Color(0xFF4D444C),

    inverseSurface = Color(0xFFECE0E4),
    inverseOnSurface = DarkBackground,
    inversePrimary = LightPrimary
)

@Composable
fun HerVibeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context)
            else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = HerVibeTypography,
        shapes = HerVibeShapes,
        content = content
    )
}