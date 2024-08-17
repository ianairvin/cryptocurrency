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
            Modifier.weight(1f),
            cryptocurrenciesViewModel::changePickedCurrency,
            cryptocurrenciesViewModel.pickedCurrency
        )
        CryptocurrenciesContent(
            Modifier.weight(5f),
            cryptocurrenciesViewModel.uiState,
            cryptocurrenciesViewModel::changePickedCurrency,
            cryptocurrenciesViewModel.pickedCurrency,
            navController
        )
    }
}