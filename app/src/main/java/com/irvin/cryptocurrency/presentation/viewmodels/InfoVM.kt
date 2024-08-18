package com.irvin.cryptocurrency.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irvin.cryptocurrency.domain.usecases.GetInfoCryptocurrencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoVM @Inject constructor(
    private val getInfoCryptocurrencyUseCase: GetInfoCryptocurrencyUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<InfoUiState>(InfoUiState.Initial)
    val uiState: StateFlow<InfoUiState> = _uiState

    private val _pickedCryptocurrencyName = MutableStateFlow("")
    val pickedCryptocurrencyName: StateFlow<String> = _pickedCryptocurrencyName

    private val pickedCryptocurrencyId = MutableStateFlow("")

    fun changePickedCryptocurrency(cryptocurrencyName: String, cryptocurrencyId: String) {
        _pickedCryptocurrencyName.value = cryptocurrencyName
        pickedCryptocurrencyId.value = cryptocurrencyId
    }

    fun changeStateToLoading() {
        _uiState.value = InfoUiState.Loading
    }

    private fun getInfoCryptocurrency() = viewModelScope.launch(Dispatchers.IO) {
        val result = getInfoCryptocurrencyUseCase(pickedCryptocurrencyId.value)
        result.onSuccess { info ->
            _uiState.value = InfoUiState.Info(info)
        }.onFailure {
            _uiState.value = InfoUiState.Error
        }
    }

    private fun startObservingUiState() {
        viewModelScope.launch {
            _uiState.collect { state ->
                if (state is InfoUiState.Loading) {
                    getInfoCryptocurrency()
                }
            }
        }
    }

    private fun startObservingPickedCruptocurrencyId() {
        viewModelScope.launch {
            pickedCryptocurrencyId.collect {
                _uiState.value = InfoUiState.Loading
            }
        }
    }

    init {
        startObservingUiState()
        startObservingPickedCruptocurrencyId()
    }
}