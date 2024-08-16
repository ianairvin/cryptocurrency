package com.irvin.cryptocurrency.domain.entities

data class Cryptocurrency(
    val name: String,
    val shortName: String,
    val price: Double,
    val priceChangePercentage: Double,
    val image: String
)
