package com.irvin.cryptocurrency.presentation.currencies_screen

sealed interface CurrenciesUiState {
    data object Initial : CurrenciesUiState
    data object Loading: CurrenciesUiState
    data class CurrenciesList(
        val list: List<T>
    ): CurrenciesUiState
    data object Error: CurrenciesUiState
}