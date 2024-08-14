package com.irvin.cryptocurrency.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val LightColorScheme = lightColors(
    background = BackgroundColor
)

@Composable
fun CryptocurrencyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colors = colorScheme,
        typography = Typography,
        content = content
    )
}