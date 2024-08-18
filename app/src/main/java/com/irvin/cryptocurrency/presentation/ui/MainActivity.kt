package com.irvin.cryptocurrency.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.irvin.cryptocurrency.R
import com.irvin.cryptocurrency.presentation.ui.cryptocurrencies_screen.CryptocurrenciesScreen
import com.irvin.cryptocurrency.presentation.ui.description_screen.DescriptionScreen
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
                    val cryptocurrenciesRoute = stringResource(id = R.string.cryptocurrencies_route)
                    val descriptionRoute = stringResource(id = R.string.description_currency_route)
                    val descriptionNavArgument =
                        stringResource(id = R.string.description_navigate_argument)
                    NavHost(
                        navController = navController,
                        startDestination = cryptocurrenciesRoute
                    ) {
                        composable(
                            cryptocurrenciesRoute,
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() },
                            popEnterTransition = { fadeIn() },
                            popExitTransition = { fadeOut() }
                        ) {
                            CryptocurrenciesScreen(
                                Modifier,
                                cryptocurrenciesViewModel,
                                navController
                            )
                        }
                        composable(
                            route = "${descriptionRoute}/{${descriptionNavArgument}}",
                            arguments = listOf(navArgument(descriptionNavArgument) {
                                type = NavType.StringType
                            }),
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() }
                        ) { backStackEntry ->
                            backStackEntry.arguments?.getString(descriptionNavArgument)?.let {
                                descriptionViewModel.changePickedCryptocurrency(it)
                            }
                            DescriptionScreen(Modifier, descriptionViewModel, navController)
                        }
                    }
                }
            }
        }
    }
}
