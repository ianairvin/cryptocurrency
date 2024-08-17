package com.irvin.cryptocurrency.presentation.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DescriptionVM : ViewModel() {
    private val _uiState = MutableStateFlow<DescriptionUiState>(DescriptionUiState.Initial)
    val uiState: StateFlow<DescriptionUiState> = _uiState

    private val _pickedCryptocurrency = MutableStateFlow("")
    val pickedCryptocurrency: StateFlow<String> = _pickedCryptocurrency

    fun changePickedCryptocurrency(cryptocurrency: String) {
        _pickedCryptocurrency.value = cryptocurrency
    }


}