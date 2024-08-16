package com.irvin.cryptocurrency.data

import com.irvin.cryptocurrency.data.retrofit.dto.CryptocurrencyDTO
import com.irvin.cryptocurrency.domain.entities.Cryptocurrency

object Converter {
    fun toCryptocurrency(cryptocurrencyDTO: CryptocurrencyDTO) : Cryptocurrency{
        return Cryptocurrency(
            name = cryptocurrencyDTO.name,
            shortName = cryptocurrencyDTO.symbol,
            price = cryptocurrencyDTO.current_price,
            priceChange = cryptocurrencyDTO.price_change_24h,
            priceChangePercentage = cryptocurrencyDTO.price_change_percentage_24h,
            image = cryptocurrencyDTO.image
            )
    }
}