package com.example.baseproject.mapper

import com.example.baseproject.model.local.data.CountryEntity
import com.example.baseproject.model.remote.data.CountryResponse
import com.example.baseproject.view.data.Country

/**
 * Contains different mappers to convert different layer country data objects into each other.
 */
fun CountryEntity.toCountry() = Country(
    name = name, capital = capital, code = code, region = region
)

fun CountryResponse.toCountryEntity() = CountryEntity(
    name = name, capital = capital, code = code, region = region
)

fun CountryResponse.toCountry() = Country(
    name = name, capital = capital, code = code, region = region
)

fun List<CountryResponse>.toCountryEntityList(): List<CountryEntity> {
    return this.map { it.toCountryEntity() }
}

fun List<CountryEntity>.toCountryList(): List<Country> {
    return this.map { it.toCountry() }
}