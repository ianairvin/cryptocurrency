package com.irvin.cryptocurrency.data.retrofit.dto

data class InfoCryptocurrencyDTO(
    val id: String,
    val image: ImageDTO,
    val categories: List<String>,
    val description: DescriptionDTO
)
