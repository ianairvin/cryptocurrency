package com.irvin.cryptocurrency.presentation.viewmodels

sealed interface CurrenciesUiState {
    data object Initial : CurrenciesUiState
    data object Loading: CurrenciesUiState
    data class Currencies(
        val list: List<Int>
    ): CurrenciesUiState
    data object Error: CurrenciesUiState
}