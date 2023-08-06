package com.example.baseproject.model.remote

import com.example.baseproject.di.RetrofitClientFactory
import com.example.baseproject.model.remote.data.CountryResponse
import okhttp3.ResponseBody
import retrofit2.Retrofit
import java.io.IOException

class CountriesRemoteDataSource {

    private var retrofit: Retrofit? = RetrofitClientFactory.createRetrofitClient()
    private var apiService: ApiService? = retrofit?.create(ApiService::class.java)

    suspend fun fetchCountries(): Result<List<CountryResponse>> {
        val countriesResponse = apiService?.getAllCountriesList()
        if (countriesResponse?.isSuccessful == true) {
            return (countriesResponse.body()?.let { Result.success(it) }
                ?: Result.failure(IOException("No data received.")))
        }
        val error: ResponseBody? = countriesResponse?.errorBody()
        return Result.failure(IOException(error.toString()))
    }

    fun destroy() {
        retrofit = null
        apiService = null
    }
}