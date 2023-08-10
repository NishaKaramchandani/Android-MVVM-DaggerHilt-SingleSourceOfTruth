package com.example.baseproject.model.remote

import com.example.baseproject.model.remote.data.CountryResponse
import okhttp3.ResponseBody
import java.io.IOException
import javax.inject.Inject

class CountriesRemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun fetchCountries(): Result<List<CountryResponse>> {
        val countriesResponse = apiService.getAllCountriesList()
        if (countriesResponse.isSuccessful) {
            return (countriesResponse.body()?.let { Result.success(it) }
                ?: Result.failure(IOException("No data received.")))
        }
        val error: ResponseBody? = countriesResponse.errorBody()
        return Result.failure(IOException(error.toString()))
    }

}