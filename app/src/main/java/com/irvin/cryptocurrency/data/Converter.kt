package com.irvin.cryptocurrency.data

import com.irvin.cryptocurrency.data.retrofit.dto.CryptocurrencyDTO
import com.irvin.cryptocurrency.data.retrofit.dto.InfoCryptocurrencyDTO
import com.irvin.cryptocurrency.domain.entities.Cryptocurrency
import com.irvin.cryptocurrency.domain.entities.InfoCryptocurrency

object Converter {
    fun toCryptocurrency(cryptocurrencyDTO: CryptocurrencyDTO): Cryptocurrency {
        return Cryptocurrency(
            id = cryptocurrencyDTO.id,
            name = cryptocurrencyDTO.name,
            shortName = cryptocurrencyDTO.symbol,
            price = cryptocurrencyDTO.currentPrice,
            priceChangePercentage = cryptocurrencyDTO.priceChangePercentage,
            image = cryptocurrencyDTO.image
        )
    }

    fun toInfoCryptocurrency(infoDTO: InfoCryptocurrencyDTO): InfoCryptocurrency {
        return InfoCryptocurrency(
            id = infoDTO.id,
            image = infoDTO.image.large,
            categories = infoDTO.categories,
            description = infoDTO.description.en
        )
    }
}