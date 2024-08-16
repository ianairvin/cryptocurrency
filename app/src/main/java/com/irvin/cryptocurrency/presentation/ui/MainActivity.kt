package com.irvin.cryptocurrency.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.irvin.cryptocurrency.presentation.ui.cryptocurrencies_screen.CryptocurrenciesScreen
import com.irvin.cryptocurrency.presentation.ui.theme.BackgroundColor
import com.irvin.cryptocurrency.presentation.ui.theme.CryptocurrencyTheme
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesVM
import com.irvin.cryptocurrency.presentation.viewmodels.DescriptionVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val cryptocurrenciesViewModel: CryptocurrenciesVM by viewModels()
    private val descriptionViewModel: DescriptionVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyTheme {
                val navController = rememberNavController()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BackgroundColor)
                ) {
                    NavHost(navController = navController, startDestination = "cryptocurrencies") {
                        composable("cryptocurrencies") {
                            CryptocurrenciesScreen(Modifier, cryptocurrenciesViewModel)
                        }
                        composable("description_currency") {
                            //DescriptionScreen()
                        }
                    }
                }
            }
        }
    }
}
