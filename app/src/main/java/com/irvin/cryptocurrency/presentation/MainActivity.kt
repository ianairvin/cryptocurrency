package com.irvin.cryptocurrency.presentation

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
import com.irvin.cryptocurrency.presentation.currencies_screen.CurrenciesScreen
import com.irvin.cryptocurrency.presentation.currencies_screen.CurrenciesVM
import com.irvin.cryptocurrency.presentation.description_screen.DescriptionVM
import com.irvin.cryptocurrency.presentation.ui.theme.BackgroundColor
import com.irvin.cryptocurrency.presentation.ui.theme.CryptocurrencyTheme

class MainActivity : ComponentActivity() {
    private val currenciesViewModel: CurrenciesVM by viewModels()
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
                            CurrenciesScreen(Modifier, currenciesViewModel)
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
