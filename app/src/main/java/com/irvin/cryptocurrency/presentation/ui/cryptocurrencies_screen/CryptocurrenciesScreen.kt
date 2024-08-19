package com.irvin.cryptocurrency.presentation.ui.cryptocurrencies_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesVM

@Composable
fun CryptocurrenciesScreen(
    modifier: Modifier,
    cryptocurrenciesViewModel: CryptocurrenciesVM,
    navController: NavHostController
) {
    Column(modifier = modifier) {
        CryptocurrenciesToolBar(
            cryptocurrenciesViewModel::changePickedCurrency,
            cryptocurrenciesViewModel.pickedCurrency
        )
        CryptocurrenciesContent(
            cryptocurrenciesViewModel.uiState,
            cryptocurrenciesViewModel::changeStateToLoading,
            cryptocurrenciesViewModel.pickedCurrency,
            cryptocurrenciesViewModel::updateListFromPullToRefresh,
            navController
        )
    }
}