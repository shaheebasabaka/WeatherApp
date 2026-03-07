package com.weatherapp.core.ui.theme

import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

val LightColorScheme = lightColorScheme(

    primary = PrimaryBlue,
    secondary = SecondaryPurple,

    background = BackgroundLight,
    surface = Color.White,

    error = ErrorRed,

    onPrimary = Color.White,
    onSecondary = Color.White,

    onBackground = TextPrimary,
    onSurface = TextPrimary,

    onError = Color.White
)

val DarkColorScheme = darkColorScheme(

    primary = PrimaryBlue,
    secondary = SecondaryPurple,

    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),

    error = ErrorRed,

    onPrimary = Color.White,
    onSecondary = Color.White,

    onBackground = Color.White,
    onSurface = Color.White,

    onError = Color.White
)