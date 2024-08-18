package com.irvin.cryptocurrency.presentation.ui.description_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.irvin.cryptocurrency.presentation.viewmodels.DescriptionVM

@Composable
fun DescriptionScreen(
    modifier: Modifier,
    descriptionViewModel: DescriptionVM,
    navController: NavHostController
) {
    Column(modifier = modifier) {
        DescriptionToolBar(modifier, descriptionViewModel.pickedCryptocurrency, navController)
        DescriptionContent(
            modifier,
            descriptionViewModel.uiState,
            descriptionViewModel::changeStateToLoading
        )
    }
}
