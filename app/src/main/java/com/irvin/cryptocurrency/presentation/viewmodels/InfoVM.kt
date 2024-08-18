package com.irvin.cryptocurrency.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InfoVM : ViewModel() {
    private val _uiState = MutableStateFlow<InfoUiState>(InfoUiState.Initial)
    val uiState: StateFlow<InfoUiState> = _uiState

    private val _pickedCryptocurrencyName = MutableStateFlow("")
    val pickedCryptocurrencyName: StateFlow<String> = _pickedCryptocurrencyName

    private val pickedCryptocurrencyId = MutableStateFlow("")

    fun changePickedCryptocurrency(cryptocurrencyName: String, cryptocurrencyId: String) {
        _pickedCryptocurrencyName.value = cryptocurrencyName
        pickedCryptocurrencyId.value = cryptocurrencyId
    }

    fun changeStateToLoading(){
        _uiState.value = InfoUiState.Loading
    }

    private fun getInfoCryptocurrency(){
        _uiState.value = InfoUiState.Info("")
    }

    private fun startObservingUiState(){
        viewModelScope.launch {
            _uiState.collect{ state ->
                if(state is InfoUiState.Loading){
                    getInfoCryptocurrency()
                }
            }
        }
    }

    private fun startObservingPickedCruptocurrencyId(){
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