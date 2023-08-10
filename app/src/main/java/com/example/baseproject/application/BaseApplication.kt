package com.example.baseproject.application

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.baseproject.domain.FetchCountryListUseCase
import com.example.baseproject.model.local.COUNTRIES_DB
import com.example.baseproject.model.local.CountriesDao
import com.example.baseproject.model.local.CountriesDatabase
import com.example.baseproject.model.local.CountriesLocalDataSource
import com.example.baseproject.model.remote.ApiService
import com.example.baseproject.model.remote.CountriesRemoteDataSource
import com.example.baseproject.model.repository.CountriesRepository
import com.example.baseproject.model.repository.CountriesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@HiltAndroidApp
class BaseApplication : Application()

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(ApiService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun createDatabaseClient(@ApplicationContext appContext: Context): CountriesDatabase =
        Room.databaseBuilder(
            appContext,
            CountriesDatabase::class.java,
            COUNTRIES_DB
        ).build()


    @Provides
    @Singleton
    fun createDaoClient(database: CountriesDatabase): CountriesDao = database.countriesDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(countriesDao: CountriesDao): CountriesLocalDataSource =
        CountriesLocalDataSource(countriesDao)

    @Provides
    @Singleton
    fun provideRemoteDataSource(apiService: ApiService): CountriesRemoteDataSource =
        CountriesRemoteDataSource(apiService = apiService)

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideRepository(
        localDataSource: CountriesLocalDataSource,
        remoteDataSource: CountriesRemoteDataSource
    ): CountriesRepository =
        CountriesRepositoryImpl(localDataSource, remoteDataSource)

    @Provides
    @Singleton
    fun provideUseCase(repository: CountriesRepository): FetchCountryListUseCase = FetchCountryListUseCase(repository)

}


