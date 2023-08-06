package com.example.baseproject.domain


import android.content.Context
import com.example.baseproject.di.RepositoryFactory
import com.example.baseproject.model.repository.CountriesRepository

/**
 * Use case class to fetch countries, fits between ListViewModel from the UI layer and countries repository from the data layer.
 * * The current implementation is simple (accessing methods of repository).
 * * Can be used to map data from different repositories, communicate with other use cases in the future.
 */
class FetchCountryListUseCase(context: Context) {

    private var repository: CountriesRepository? = RepositoryFactory.createRepositoryClient(context)

    suspend operator fun invoke() = repository?.fetchAllCountries()

    fun destroy() {
        repository?.destroy()
        repository = null
    }
}
