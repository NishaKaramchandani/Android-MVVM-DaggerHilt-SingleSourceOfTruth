package com.example.baseproject.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.baseproject.model.local.data.CountryEntity

const val COUNTRIES_DB = "countries_db"
const val COUNTRIES_TABLE = "countries"

@Database(
    entities = [CountryEntity::class],
    version = 1
)

abstract class CountriesDatabase : RoomDatabase() {
    abstract fun countriesDao(): CountriesDao
}
