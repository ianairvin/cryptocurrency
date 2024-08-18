package com.irvin.cryptocurrency.presentation.viewmodels

sealed interface InfoUiState {
    data object Initial : InfoUiState
    data object Loading : InfoUiState
    data class Info(val cryptocurrency: String) : InfoUiState
    data object Error : InfoUiState
}