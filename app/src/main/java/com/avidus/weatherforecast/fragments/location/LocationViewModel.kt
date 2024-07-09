package com.avidus.weatherforecast.fragments.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avidus.weatherforecast.data.RemoteLocation
import com.avidus.weatherforecast.network.repository.WeatherDataRepository
import kotlinx.coroutines.launch

class LocationViewModel(private val weatherDataRepository: WeatherDataRepository) : ViewModel() {

    private val _searchResult = MutableLiveData<SearchResultDataState>()
    val searchResult: LiveData<SearchResultDataState> = _searchResult

    fun searchLocation(query: String) {
        viewModelScope.launch {
            try {
                emitSearchResultUiState(isLoading = true)
                val searchResult = weatherDataRepository.searchLocation(query)
                if (searchResult.isNullOrEmpty()) {
                    emitSearchResultUiState(error = "Location Not found! Try Again!")
                } else {
                    emitSearchResultUiState(locations = searchResult)
                }
            } catch (e: Exception) {
                emitSearchResultUiState(error = "An error occurred: ${e.message}")
            } finally {
                emitSearchResultUiState(isLoading = false)
            }
        }
    }

    private fun emitSearchResultUiState(
        isLoading: Boolean = false,
        locations: List<RemoteLocation>? = null,
        error: String? = null
    ) {
        val searchResultDataState = SearchResultDataState(isLoading, locations, error)
        _searchResult.value = searchResultDataState
    }

    data class SearchResultDataState(
        val isLoading: Boolean,
        val locations: List<RemoteLocation>?,
        val error: String?
    )
}
