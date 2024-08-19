package com.irvin.cryptocurrency.presentation.ui.cryptocurrencies_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.irvin.cryptocurrency.domain.entities.Currency
import com.irvin.cryptocurrency.presentation.ui.app_common_screen.ErrorScreen
import com.irvin.cryptocurrency.presentation.ui.app_common_screen.LoadingScreen
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState.Cryptocurrencies
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState.Error
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState.Initial
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState.Loading
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CryptocurrenciesContent(
    uiState: StateFlow<CryptocurrenciesUiState>,
    changeStateToLoading: () -> Unit,
    pickedCurrency: StateFlow<Currency>,
    updateListFromPullToRefresh: suspend () -> Boolean,
    navController: NavHostController
) {
    Box(Modifier.fillMaxSize()) {
        when (uiState.collectAsState().value) {
            is Initial -> Unit
            is Loading -> LoadingScreen()
            is Error -> ErrorScreen(changeStateToLoading)
            is Cryptocurrencies -> CryptocurrenciesList(
                uiState.collectAsState().value as Cryptocurrencies,
                pickedCurrency,
                updateListFromPullToRefresh,
                navController
            )
        }
    }
}