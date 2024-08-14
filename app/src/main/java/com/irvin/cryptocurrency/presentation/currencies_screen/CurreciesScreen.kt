package com.irvin.cryptocurrency.presentation.currencies_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CurrenciesScreen(
    modifier: Modifier,
    currenciesViewModel: CurrenciesVM
){
    Column(modifier = modifier) {
        TopBar()

    }
}

@Composable
fun TopBar(){

}