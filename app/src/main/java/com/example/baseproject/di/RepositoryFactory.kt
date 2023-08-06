package com.example.baseproject.di

import android.content.Context
import com.example.baseproject.model.repository.CountriesRepository
import com.example.baseproject.model.repository.CountriesRepositoryImpl

/**
 *  Factory to create repository client through manual DI
 */
object RepositoryFactory {

    private var countriesRepository: CountriesRepository? = null

    fun createRepositoryClient(context: Context): CountriesRepository {
        if (countriesRepository == null) {
            countriesRepository = CountriesRepositoryImpl(context)
        }

        return requireNotNull(countriesRepository)
    }

    fun destroyRepositoryClient() {
        countriesRepository = null
    }
}