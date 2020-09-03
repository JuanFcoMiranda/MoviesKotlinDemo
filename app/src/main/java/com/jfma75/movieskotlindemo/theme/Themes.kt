package com.jfma75.movieskotlindemo.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val lightThemeColors = lightColors(
    primary = FunctionalRed2,
    primaryVariant = FunctionalRed3,
    onPrimary = Color.White,
    secondary = Color.White,
    onSecondary = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    error = FunctionalRed,
    onError = Color.White
)

/**
 * Note: Dark Theme support is not yet available, it will come in 2020. This is just an example of
 * using dark colors.
 */
val darkThemeColors = darkColors(
    primary = FunctionalRedDark,
    primaryVariant = FuncionalRedDark2,
    onPrimary = Color.White,
    secondary = Neutral8,
    onSecondary = Color.White,
    background = Neutral8,
    onBackground = Color.White,
    surface = Neutral8,
    onSurface = Color.White,
    error = FuncionalRedDark2,
    onError = Color.Black
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (darkTheme) darkThemeColors else lightThemeColors,
        typography = themeTypography,
        shapes = Shapes,
        content = content
    )
}