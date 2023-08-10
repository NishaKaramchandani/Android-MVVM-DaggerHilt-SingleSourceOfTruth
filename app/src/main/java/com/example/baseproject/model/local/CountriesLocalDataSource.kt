package com.example.baseproject.model.local

import com.example.baseproject.model.local.data.CountryEntity
import javax.inject.Inject

class CountriesLocalDataSource @Inject constructor(private val countriesDao: CountriesDao) {

    fun insertCountry(countryEntity: CountryEntity) {
        countriesDao.insertCountryEntity(countryEntity)
    }

    fun insertAllCountries(countries: List<CountryEntity>) {
        countriesDao.insertAll(countries)
    }

    fun getAllCountries() = countriesDao.getAllCountries()

}