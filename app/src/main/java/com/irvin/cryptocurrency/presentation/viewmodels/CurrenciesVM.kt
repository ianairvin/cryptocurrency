package com.irvin.cryptocurrency.presentation.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import com.irvin.cryptocurrency.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CurrenciesVM : ViewModel() {
    private val _uiState = MutableStateFlow<CurrenciesUiState>(CurrenciesUiState.Initial)
    val uiState: StateFlow<CurrenciesUiState> = _uiState

    val pickedCurrency = mutableStateOf("usd")

    init {
        _uiState.value = CurrenciesUiState.Loading
    }
}