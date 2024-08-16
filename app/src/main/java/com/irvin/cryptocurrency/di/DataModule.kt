package com.irvin.cryptocurrency.di

import com.irvin.cryptocurrency.data.CryptocurrenciesRepositoryImpl
import com.irvin.cryptocurrency.domain.repository.CryptocurrenciesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideCryptocurrenciesRepository(): CryptocurrenciesRepository {
        return CryptocurrenciesRepositoryImpl()
    }
}