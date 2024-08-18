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
import com.irvin.cryptocurrency.presentation.ui.info_screen.InfoScreen
import com.irvin.cryptocurrency.presentation.ui.theme.BackgroundColor
import com.irvin.cryptocurrency.presentation.ui.theme.CryptocurrencyTheme
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesVM
import com.irvin.cryptocurrency.presentation.viewmodels.InfoVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val cryptocurrenciesViewModel: CryptocurrenciesVM by viewModels()
    private val infoViewModel: InfoVM by viewModels()

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
                    val infoRoute = stringResource(id = R.string.info_cryptocurrency_route)
                    val cryptocurrencyNameArgument =
                        stringResource(id = R.string.info_navigate_argument_name)
                    val cryptocurrencyIdArgument =
                        stringResource(id = R.string.info_navigate_argument_id)
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
                            route = "${infoRoute}" +
                                    "/{${cryptocurrencyNameArgument}}" +
                                    "/{${cryptocurrencyIdArgument}}",
                            arguments = listOf(
                                navArgument(cryptocurrencyNameArgument) {
                                    type = NavType.StringType
                                },
                                navArgument(cryptocurrencyIdArgument) {
                                    type = NavType.StringType
                                }
                            ),
                            enterTransition = { fadeIn() },
                            exitTransition = { fadeOut() }
                        ) { backStackEntry ->
                            val cryptocurrencyName =  backStackEntry.arguments?.getString(cryptocurrencyNameArgument)
                            val cryptocurrencyId =  backStackEntry.arguments?.getString(cryptocurrencyIdArgument)
                            if (cryptocurrencyId != null && cryptocurrencyName != null){
                                infoViewModel.changePickedCryptocurrency(
                                    cryptocurrencyName,
                                    cryptocurrencyId
                                )
                            }
                            InfoScreen(Modifier, infoViewModel, navController)
                        }
                    }
                }
            }
        }
    }
}
