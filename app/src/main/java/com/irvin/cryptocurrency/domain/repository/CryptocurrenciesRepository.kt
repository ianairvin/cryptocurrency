package com.irvin.cryptocurrency.domain.repository

import com.irvin.cryptocurrency.domain.entities.Cryptocurrency
import com.irvin.cryptocurrency.domain.entities.Currency
import com.irvin.cryptocurrency.domain.entities.InfoCryptocurrency

interface CryptocurrenciesRepository {
    suspend fun getCryptocurrencies(currency: Currency) : Result<List<Cryptocurrency>>
    suspend fun getInfoCryotocurrency(id: String) : Result<InfoCryptocurrency>
}