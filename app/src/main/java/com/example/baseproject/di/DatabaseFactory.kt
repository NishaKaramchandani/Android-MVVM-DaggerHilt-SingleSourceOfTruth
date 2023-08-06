package com.example.baseproject.di

import android.content.Context
import androidx.room.Room
import com.example.baseproject.model.local.CountriesDatabase
import com.example.baseproject.model.local.COUNTRIES_DB
import com.example.baseproject.model.local.CountriesDao

/**
 *  Factory to create database client through manual DI
 */
object DatabaseFactory {

    private var countriesDatabase: CountriesDatabase? = null

    fun createDatabaseClient(context: Context): CountriesDatabase {
        if (countriesDatabase == null) {
            countriesDatabase = Room.databaseBuilder(
                context,
                CountriesDatabase::class.java,
                COUNTRIES_DB
            ).build()
        }

        return requireNotNull(countriesDatabase)
    }

    fun destroy() {
        countriesDatabase = null
    }
}