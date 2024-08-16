package com.irvin.cryptocurrency.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irvin.cryptocurrency.domain.entities.Currency
import com.irvin.cryptocurrency.domain.usecases.GetCryptocurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptocurrenciesVM @Inject constructor(
    private val getCryptocurrenciesUseCase: GetCryptocurrenciesUseCase
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<CryptocurrenciesUiState>(CryptocurrenciesUiState.Initial)
    val uiState: StateFlow<CryptocurrenciesUiState> = _uiState

    private val _pickedCurrency = MutableStateFlow(Currency.USD)
    val pickedCurrency: StateFlow<Currency> = _pickedCurrency

    private fun getCryptocurrencies() = viewModelScope.launch(Dispatchers.IO) {
        val result = getCryptocurrenciesUseCase(pickedCurrency.value)
        result.onSuccess { list ->
            _uiState.value = CryptocurrenciesUiState.Cryptocurrencies(list)
        }.onFailure {
            _uiState.value = CryptocurrenciesUiState.Error
        }
    }

    fun changeStateToLoading() {
        _uiState.value = CryptocurrenciesUiState.Loading
    }

    fun changePickedCurrency() {
        _pickedCurrency.value =
            if (_pickedCurrency.value == Currency.USD) Currency.RUB else Currency.USD
        changeStateToLoading()
    }

    private fun startObservingState() {
        viewModelScope.launch {
            _uiState.collect { state ->
                if (state is CryptocurrenciesUiState.Loading) {
                    getCryptocurrencies()
                }
            }
        }
    }

    init {
        startObservingState()
        _uiState.value = CryptocurrenciesUiState.Loading
    }
}