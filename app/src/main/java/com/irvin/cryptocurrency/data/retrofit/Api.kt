package com.irvin.cryptocurrency.data.retrofit

import com.irvin.cryptocurrency.data.retrofit.dto.CryptocurrencyDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("coins/markets")
    suspend fun loadCryptocurrencies(
        @Query("vs_currency") currency: String,
        @Query("per_page") numberOfItems: String = "30"
    ): List<CryptocurrencyDTO>
}