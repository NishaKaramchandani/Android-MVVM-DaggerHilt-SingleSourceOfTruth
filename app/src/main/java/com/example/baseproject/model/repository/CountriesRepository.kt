package com.example.baseproject.model.repository

import com.example.baseproject.view.data.Country
import kotlinx.coroutines.flow.Flow

interface CountriesRepository {
    suspend fun fetchAllCountries(): Flow<Result<List<Country>>>
    fun destroy()
}