package com.irvin.cryptocurrency.presentation.ui.description_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.irvin.cryptocurrency.presentation.ui.app_common_screen.ErrorScreen
import com.irvin.cryptocurrency.presentation.ui.app_common_screen.LoadingScreen
import com.irvin.cryptocurrency.presentation.viewmodels.DescriptionUiState
import com.irvin.cryptocurrency.presentation.viewmodels.DescriptionUiState.Description
import com.irvin.cryptocurrency.presentation.viewmodels.DescriptionUiState.Error
import com.irvin.cryptocurrency.presentation.viewmodels.DescriptionUiState.Initial
import com.irvin.cryptocurrency.presentation.viewmodels.DescriptionUiState.Loading
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DescriptionContent(
    modifier: Modifier,
    uiState: StateFlow<DescriptionUiState>,
    changeStateToLoading: () -> Unit
) {
    Box(modifier.fillMaxSize()) {
        when (uiState.collectAsState().value) {
            is Initial -> Unit
            is Loading -> LoadingScreen()
            is Description -> DescriptionCryptocurrency()
            is Error -> ErrorScreen(changeStateToLoading)
        }
    }
}

