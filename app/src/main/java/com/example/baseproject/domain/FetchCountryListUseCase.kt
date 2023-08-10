package com.example.baseproject.domain

import com.example.baseproject.model.repository.CountriesRepository
import javax.inject.Inject

/**
 * Use case class to fetch countries, fits between ListViewModel from the UI layer and countries repository from the data layer.
 * * The current implementation is simple (accessing methods of repository).
 * * Can be used to map data from different repositories, communicate with other use cases in the future.
 */
class FetchCountryListUseCase @Inject constructor(private val repository: CountriesRepository) {
    suspend operator fun invoke() = repository.fetchAllCountries()
}
