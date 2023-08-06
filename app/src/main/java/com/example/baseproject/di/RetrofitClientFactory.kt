package com.example.baseproject.di

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Factory to create retrofit client through manual DI
 */
object RetrofitClientFactory {

    private lateinit var retrofit: Retrofit
    private const val BASE_URL = "https://gist.githubusercontent.com/"

    fun createRetrofitClient(): Retrofit {
        if (RetrofitClientFactory::retrofit.isInitialized.not()) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }

        return retrofit
    }
}

