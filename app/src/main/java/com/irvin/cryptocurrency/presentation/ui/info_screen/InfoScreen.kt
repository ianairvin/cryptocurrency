package com.irvin.cryptocurrency.presentation.ui.info_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.irvin.cryptocurrency.presentation.viewmodels.InfoVM

@Composable
fun InfoScreen(
    modifier: Modifier,
    infoViewModel: InfoVM,
    navController: NavHostController
) {
    Column(modifier = modifier) {
        InfoToolBar(modifier, infoViewModel.pickedCryptocurrencyName, navController)
        InfoContent(
            modifier,
            infoViewModel.uiState,
            infoViewModel::changeStateToLoading
        )
    }
}
