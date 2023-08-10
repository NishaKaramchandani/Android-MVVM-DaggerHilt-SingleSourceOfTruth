package com.example.baseproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.baseproject.R
import com.example.baseproject.domain.FetchCountryListUseCase
import com.example.baseproject.view.data.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val fetchCountryListUseCase: FetchCountryListUseCase) : ViewModel() {
    private val _countriesUiState: MutableSharedFlow<CountryListUiState> = MutableSharedFlow(replay = 1)

    val countriesUiState: SharedFlow<CountryListUiState> get() = _countriesUiState.asSharedFlow()

    companion object {
        private const val TAG = "ListViewModel"
    }

    init {
        // Calling data fetching logic in init so we don't fetch again when device is rotated.
        getAllCountries()
    }

    private fun getAllCountries() {
        viewModelScope.launch {
            Log.d(TAG, "getAllCountries: Doing API call!!")
            _countriesUiState.emit(CountryListUiState.Loading)
            // Delay here to test loading state and simulate network latency.
            //delay(2000)

            fetchCountryListUseCase().collectLatest { value: Result<List<Country>> ->
                value.onSuccess { countries ->
                    if (countries.isNotEmpty()) {
                        _countriesUiState.emit(CountryListUiState.Success(countryList = countries))
                    } else {
                        _countriesUiState.emit(CountryListUiState.Error(R.string.error_fetching_countries_empty))
                    }
                }
                value.onFailure { error ->
                    val errorMessageId = when (error) {
                        is UnknownHostException -> R.string.offline_error_fetching
                        else -> R.string.error_fetching_countries
                    }
                    _countriesUiState.emit(CountryListUiState.Error(errorMessageId = errorMessageId))
                }
            }
        }
    }

}

sealed class CountryListUiState {
    data class Success(val countryList: List<Country>) : CountryListUiState()
    data class Error(@StringRes val errorMessageId: Int) : CountryListUiState()
    object Loading : CountryListUiState()
}