package com.example.baseproject.model.local

import android.content.Context
import com.example.baseproject.di.DatabaseFactory
import com.example.baseproject.model.local.data.CountryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountriesLocalDataSource(context: Context) {

    private var countriesDao: CountriesDao? = DatabaseFactory.createDatabaseClient(context).countriesDao()

    fun insertCountry(countryEntity: CountryEntity) {
        countriesDao?.insertCountryEntity(countryEntity)
    }

    fun insertAllCountries(countries: List<CountryEntity>) {
        countriesDao?.insertAll(countries)
    }

    fun getAllCountries() = countriesDao?.getAllCountries()

    fun destroy() {
        DatabaseFactory.destroy()
        countriesDao = null
    }
}