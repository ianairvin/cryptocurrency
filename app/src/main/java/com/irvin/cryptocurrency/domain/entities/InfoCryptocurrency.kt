package com.irvin.cryptocurrency.domain.entities

data class InfoCryptocurrency(
    val id: String,
    val image: String,
    val categories: List<String>,
    val description: String
)
