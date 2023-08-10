package com.example.baseproject.model.repository

import com.example.baseproject.model.local.CountriesLocalDataSource
import com.example.baseproject.model.remote.CountriesRemoteDataSource
import com.example.baseproject.mapper.toCountry
import com.example.baseproject.mapper.toCountryEntityList
import com.example.baseproject.mapper.toCountryList
import com.example.baseproject.view.data.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Repository Impl to get data either from local data source and remote data source.
 * Repository maintains single source of truth abstraction.
 */
class CountriesRepositoryImpl @Inject constructor(
    private val localDataSource: CountriesLocalDataSource,
    private val remoteDataSource: CountriesRemoteDataSource
) : CountriesRepository {

    /* Read from db. If countries do not exist in db, fetch from API and write it to db. */
    override suspend fun fetchAllCountries(): Flow<Result<List<Country>>> = flow<Result<List<Country>>> {

        val countryListFlow = localDataSource.getAllCountries()
            .map { localData -> localData.map { it.toCountry() } }

        countryListFlow.collect { state ->
            if(state.isEmpty()) {
                try {
                    val countryResponse = remoteDataSource.fetchCountries()
                    if (countryResponse.isSuccess) {
                        val countryEntityList = countryResponse.getOrThrow().toCountryEntityList()
                        localDataSource.insertAllCountries(countryEntityList)
                        emit(Result.success(countryEntityList.toCountryList()))
                    }
                } catch (exception: Exception) {
                    emit(Result.failure(exception))
                }
            } else {
                emit(Result.success(state))
            }
        }
    }.flowOn(Dispatchers.IO)

}