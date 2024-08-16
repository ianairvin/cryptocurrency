package com.irvin.cryptocurrency.presentation.viewmodels

import androidx.compose.runtime.Immutable
import com.irvin.cryptocurrency.domain.entities.Cryptocurrency

sealed interface CryptocurrenciesUiState {
    data object Initial : CryptocurrenciesUiState
    data object Loading : CryptocurrenciesUiState
    @Immutable
    data class Cryptocurrencies(val list: List<Cryptocurrency>) : CryptocurrenciesUiState
    data object Error : CryptocurrenciesUiState
}