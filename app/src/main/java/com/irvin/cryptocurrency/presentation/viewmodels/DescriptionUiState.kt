package com.irvin.cryptocurrency.presentation.viewmodels

sealed interface DescriptionUiState {
    data object Initial : DescriptionUiState
    data object Loading : DescriptionUiState
    data class Description(val cryptocurrency: String) : DescriptionUiState
    data object Error : DescriptionUiState
}