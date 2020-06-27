package com.jfma75.movieskotlindemo.theme

import androidx.compose.Composable
import androidx.ui.foundation.isSystemInDarkTheme
import androidx.ui.graphics.Color
import androidx.ui.material.MaterialTheme
import androidx.ui.material.darkColorPalette
import androidx.ui.material.lightColorPalette

val lightThemeColors = lightColorPalette(
    primary = Color(0xFFDD0D3C),
    primaryVariant = Color(0xFFC20029),
    onPrimary = Color.White,
    secondary = Color.White,
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = Color(0xFFD00036),
    onError = Color.White
)

/**
 * Note: Dark Theme support is not yet available, it will come in 2020. This is just an example of
 * using dark colors.
 */
val darkThemeColors = darkColorPalette(
    primary = Color(0xFFEA6D7E),
    primaryVariant = Color(0xFFDD0D3E),
    onPrimary = Color.White,
    secondary = Color(0xFF121212),
    onSecondary = Color.White,
    background = Color(0xFF121212),
    onBackground = Color.White,
    surface = Color(0xFF121212),
    onSurface = Color.White,
    error = Color(0xFFDD0D3E),
    onError = Color.Black
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    MaterialTheme(
        colors = if (darkTheme) darkThemeColors else lightThemeColors,
        typography = themeTypography,
        shapes = Shapes,
        content = content
    )
}