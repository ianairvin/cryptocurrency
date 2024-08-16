package com.irvin.cryptocurrency.domain.repository

import com.irvin.cryptocurrency.domain.entities.Cryptocurrency
import com.irvin.cryptocurrency.domain.entities.Currency

interface CryptocurrenciesRepository {
    suspend fun getCryptocurrencies(currency: Currency) : Result<List<Cryptocurrency>>
}