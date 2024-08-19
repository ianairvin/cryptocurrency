package com.irvin.cryptocurrency.data

import com.irvin.cryptocurrency.data.retrofit.Api
import com.irvin.cryptocurrency.domain.entities.Cryptocurrency
import com.irvin.cryptocurrency.domain.entities.Currency
import com.irvin.cryptocurrency.domain.entities.InfoCryptocurrency
import com.irvin.cryptocurrency.domain.repository.CryptocurrenciesRepository
import javax.inject.Inject

class CryptocurrenciesRepositoryImpl @Inject constructor(
    private val api: Api
) : CryptocurrenciesRepository {
    override suspend fun getCryptocurrencies(
        currency: Currency
    ): Result<List<Cryptocurrency>> {
        return try {
            val apiList = api.loadCryptocurrencies(currency.title.lowercase())
            val list = apiList.map { Converter.toCryptocurrency(it) }.shuffled()
            Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getInfoCryotocurrency(id: String): Result<InfoCryptocurrency> {
        return try {
            Result.success(Converter.toInfoCryptocurrency(api.loadInfoCryptocurrency(id)))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}