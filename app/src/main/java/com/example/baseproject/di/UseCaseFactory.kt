package com.example.baseproject.di

import android.content.Context
import com.example.baseproject.domain.FetchCountryListUseCase

/**
 * Factory to create use case client through manual DI
 */
object UseCaseFactory {

    private var fetchCountriesUseCase: FetchCountryListUseCase? = null

    fun createFetchCountriesUseCaseClient(context: Context): FetchCountryListUseCase {
        if (fetchCountriesUseCase == null) {
            fetchCountriesUseCase = FetchCountryListUseCase(context)
        }

        return requireNotNull(fetchCountriesUseCase)
    }

    fun destroy() {
        fetchCountriesUseCase?.destroy()
        fetchCountriesUseCase = null
    }
}