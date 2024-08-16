package com.irvin.cryptocurrency.domain.usecases

import com.irvin.cryptocurrency.domain.entities.Cryptocurrency
import com.irvin.cryptocurrency.domain.entities.Currency
import com.irvin.cryptocurrency.domain.repository.CryptocurrenciesRepository
import javax.inject.Inject

class GetCryptocurrenciesUseCase @Inject constructor(
    private val repository: CryptocurrenciesRepository
) {
    suspend operator fun invoke(currency: Currency): Result<List<Cryptocurrency>> {
        return repository.getCryptocurrencies(currency)
    }
}