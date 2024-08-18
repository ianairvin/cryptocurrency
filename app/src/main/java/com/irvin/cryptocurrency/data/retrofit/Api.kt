package com.irvin.cryptocurrency.data.retrofit

import com.irvin.cryptocurrency.data.retrofit.dto.CryptocurrencyDTO
import com.irvin.cryptocurrency.data.retrofit.dto.InfoCryptocurrencyDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("coins/markets")
    suspend fun loadCryptocurrencies(
        @Query("vs_currency") currency: String,
        @Query("per_page") numberOfItems: String = "30"
    ): List<CryptocurrencyDTO>

    @GET("coins/{id}")
    suspend fun loadInfoCryptocurrency(
        @Path("id") id: String,
        @Query("localization") localization: String = "false",
        @Query("tickers") tickers: String = "false",
        @Query("market_data") market_data: String = "false",
        @Query("community_data") community_data: String = "false",
        @Query("developer_data") developer_data: String = "false",
        @Query("sparkline") sparkline: String = "false"
    ): InfoCryptocurrencyDTO
}