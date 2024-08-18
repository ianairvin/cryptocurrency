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

    private val _pickedCryptocurrency = MutableStateFlow("")
    val pickedCryptocurrency: StateFlow<String> = _pickedCryptocurrency

    fun changePickedCryptocurrency(cryptocurrency: String) {
        _pickedCryptocurrency.value = cryptocurrency
    }

    fun changeStateToLoading(){
        _uiState.value = DescriptionUiState.Loading
    }

    private fun getDescriptionCryptocurrency(){

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

    private fun startObservingPickedCruptocurrency(){
        viewModelScope.launch {
            _pickedCryptocurrency.collect {
                _uiState.value = DescriptionUiState.Loading
            }
        }
    }

    init {
        startObservingUiState()
        startObservingPickedCruptocurrency()
    }
}