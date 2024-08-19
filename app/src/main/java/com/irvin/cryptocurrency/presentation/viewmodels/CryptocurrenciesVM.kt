package com.irvin.cryptocurrency.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irvin.cryptocurrency.domain.entities.Cryptocurrency
import com.irvin.cryptocurrency.domain.entities.Currency
import com.irvin.cryptocurrency.domain.usecases.GetCryptocurrenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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

    private fun getCryptocurrencies(): Deferred<Result<List<Cryptocurrency>>> =
        viewModelScope.async(Dispatchers.IO) {
            getCryptocurrenciesUseCase(pickedCurrency.value)
        }

    private suspend fun updateList() {
        val result = getCryptocurrencies().await()
        result.onSuccess { list ->
            _uiState.value = CryptocurrenciesUiState.Cryptocurrencies(list)
        }.onFailure {
            _uiState.value = CryptocurrenciesUiState.Error
        }
    }

    suspend fun updateListFromPullToRefresh(): Boolean {
        val result = getCryptocurrencies().await()
        var error = false
        result.onSuccess { list ->
            _uiState.value = CryptocurrenciesUiState.Cryptocurrencies(list)
            error = false
        }.onFailure {
            error = true
        }
        return error
    }

    fun changeStateToLoading() {
        _uiState.value = CryptocurrenciesUiState.Loading
    }

    fun changePickedCurrency() {
        _pickedCurrency.value =
            if (_pickedCurrency.value == Currency.USD) Currency.RUB else Currency.USD
        changeStateToLoading()
    }

    private fun startObservingUiState() {
        viewModelScope.launch {
            _uiState.collect { state ->
                if (state is CryptocurrenciesUiState.Loading) {
                    updateList()
                }
            }
        }
    }

    init {
        startObservingUiState()
        _uiState.value = CryptocurrenciesUiState.Loading
    }
}