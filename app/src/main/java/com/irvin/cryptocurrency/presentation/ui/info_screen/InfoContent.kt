package com.irvin.cryptocurrency.presentation.ui.info_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.irvin.cryptocurrency.presentation.ui.app_common_screen.ErrorScreen
import com.irvin.cryptocurrency.presentation.ui.app_common_screen.LoadingScreen
import com.irvin.cryptocurrency.presentation.viewmodels.InfoUiState
import com.irvin.cryptocurrency.presentation.viewmodels.InfoUiState.Error
import com.irvin.cryptocurrency.presentation.viewmodels.InfoUiState.Info
import com.irvin.cryptocurrency.presentation.viewmodels.InfoUiState.Initial
import com.irvin.cryptocurrency.presentation.viewmodels.InfoUiState.Loading
import kotlinx.coroutines.flow.StateFlow

@Composable
fun InfoContent(
    modifier: Modifier,
    uiState: StateFlow<InfoUiState>,
    changeStateToLoading: () -> Unit
) {
    Box(modifier.fillMaxSize()) {
        when (uiState.collectAsState().value) {
            is Initial -> Unit
            is Loading -> LoadingScreen()
            is Info -> InfoCryptocurrency(uiState.collectAsState().value as Info)
            is Error -> ErrorScreen(changeStateToLoading)
        }
    }
}

