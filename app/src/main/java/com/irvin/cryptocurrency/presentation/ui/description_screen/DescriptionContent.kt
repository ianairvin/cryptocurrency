package com.irvin.cryptocurrency.presentation.ui.description_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
    uiState: StateFlow<DescriptionUiState>,
    changeStateToLoading: () -> Unit
) {
    Column {
        when (uiState.collectAsState().value) {
            is Initial -> Unit
            is Loading -> LoadingScreen()
            is Description -> TODO()
            is Error -> ErrorScreen(changeStateToLoading)
        }
    }
}

