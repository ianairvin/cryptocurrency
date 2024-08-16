package com.irvin.cryptocurrency.domain.repository

import com.irvin.cryptocurrency.domain.entities.Cryptocurrency

interface CryptocurrenciesRepository {
    suspend fun getCryptocurrencies() : Result<List<Cryptocurrency>>
}