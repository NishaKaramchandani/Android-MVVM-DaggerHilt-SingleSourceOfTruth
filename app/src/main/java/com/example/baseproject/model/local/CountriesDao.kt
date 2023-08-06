package com.example.baseproject.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.baseproject.model.local.data.CountryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CountriesDao {

    @Query("SELECT * FROM $COUNTRIES_TABLE")
    fun getAllCountries(): Flow<List<CountryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll( country: List<CountryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountryEntity(countryEntity: CountryEntity)
}