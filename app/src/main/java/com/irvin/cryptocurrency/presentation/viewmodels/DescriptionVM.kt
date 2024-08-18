package com.irvin.cryptocurrency.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DescriptionVM : ViewModel() {
    private val _uiState = MutableStateFlow<DescriptionUiState>(DescriptionUiState.Initial)
    val uiState: StateFlow<DescriptionUiState> = _uiState

    private val _pickedCryptocurrencyName = MutableStateFlow("")
    val pickedCryptocurrencyName: StateFlow<String> = _pickedCryptocurrencyName

    private val pickedCryptocurrencyId = MutableStateFlow("")

    fun changePickedCryptocurrency(cryptocurrencyName: String, cryptocurrencyId: String) {
        _pickedCryptocurrencyName.value = cryptocurrencyName
        pickedCryptocurrencyId.value = cryptocurrencyId
    }

    fun changeStateToLoading(){
        _uiState.value = DescriptionUiState.Loading
    }

    private fun getDescriptionCryptocurrency(){
        _uiState.value = DescriptionUiState.Description("")
    }

    private fun startObservingUiState(){
        viewModelScope.launch {
            _uiState.collect{ state ->
                if(state is DescriptionUiState.Loading){
                    getDescriptionCryptocurrency()
                }
            }
        }
    }

    private fun startObservingPickedCruptocurrencyId(){
        viewModelScope.launch {
            pickedCryptocurrencyId.collect {
                _uiState.value = DescriptionUiState.Loading
            }
        }
    }

    init {
        startObservingUiState()
        startObservingPickedCruptocurrencyId()
    }
}