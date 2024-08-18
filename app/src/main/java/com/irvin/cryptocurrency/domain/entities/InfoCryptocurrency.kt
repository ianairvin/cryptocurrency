package com.irvin.cryptocurrency.domain.entities

import com.irvin.cryptocurrency.data.retrofit.dto.DescriptionDTO
import com.irvin.cryptocurrency.data.retrofit.dto.ImageDTO

data class InfoCryptocurrency(
    val id: String,
    val image: String,
    val categories: List<String>,
    val description: String
)
