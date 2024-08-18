package com.irvin.cryptocurrency.presentation.viewmodels

import com.irvin.cryptocurrency.domain.entities.InfoCryptocurrency

sealed interface InfoUiState {
    data object Initial : InfoUiState
    data object Loading : InfoUiState
    data class Info(val info: InfoCryptocurrency) : InfoUiState
    data object Error : InfoUiState
}