package com.irvin.cryptocurrency.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val LightColorScheme = lightColors(
    background = BackgroundColor
)

@Composable
fun CryptocurrencyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    UiController()
    MaterialTheme(
        colors = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun UiController() {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(color = BackgroundColor)
        systemUiController.statusBarDarkContentEnabled = true
        systemUiController.setNavigationBarColor(color = BackgroundColor)
        systemUiController.navigationBarDarkContentEnabled = true
    }
}