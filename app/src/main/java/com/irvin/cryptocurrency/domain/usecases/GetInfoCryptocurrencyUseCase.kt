package com.irvin.cryptocurrency.domain.usecases

import com.irvin.cryptocurrency.domain.entities.InfoCryptocurrency
import com.irvin.cryptocurrency.domain.repository.CryptocurrenciesRepository
import javax.inject.Inject

class GetInfoCryptocurrencyUseCase @Inject constructor(
    private val repository: CryptocurrenciesRepository
) {
    suspend operator fun invoke(id: String): Result<InfoCryptocurrency> {
        return repository.getInfoCryotocurrency(id)
    }
}