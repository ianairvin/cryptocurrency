package com.irvin.cryptocurrency.data

import com.irvin.cryptocurrency.domain.entities.Cryptocurrency
import com.irvin.cryptocurrency.domain.repository.CryptocurrenciesRepository
import kotlinx.coroutines.delay

class CryptocurrenciesRepositoryImpl : CryptocurrenciesRepository {
    override suspend fun getCryptocurrencies(): Result<List<Cryptocurrency>> {
        delay(1000L)
        return try {
            Result.success(emptyList())
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}