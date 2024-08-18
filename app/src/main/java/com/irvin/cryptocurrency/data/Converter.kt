package com.irvin.cryptocurrency.data

import com.irvin.cryptocurrency.data.retrofit.dto.CryptocurrencyDTO
import com.irvin.cryptocurrency.domain.entities.Cryptocurrency

object Converter {
    fun toCryptocurrency(cryptocurrencyDTO: CryptocurrencyDTO) : Cryptocurrency{
        return Cryptocurrency(
            id = cryptocurrencyDTO.id,
            name = cryptocurrencyDTO.name,
            shortName = cryptocurrencyDTO.symbol,
            price = cryptocurrencyDTO.currentPrice,
            priceChangePercentage = cryptocurrencyDTO.priceChangePercentage,
            image = cryptocurrencyDTO.image
            )
    }
}