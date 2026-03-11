package com.weatherapp.feature.search.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherapp.feature.search.domain.model.CityWeather
import com.weatherapp.feature.search.domain.usecase.AddCityUseCase
import com.weatherapp.feature.search.domain.usecase.GetSavedCityIdsUseCase
import com.weatherapp.feature.search.domain.usecase.SearchCityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchCityUseCase: SearchCityUseCase,
    private val addCityUseCase: AddCityUseCase,
    private val getSavedCityIdsUseCase: GetSavedCityIdsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var lastQuery: String = ""

    init {
        observeSavedCityIds()
    }

    private fun observeSavedCityIds() {
        viewModelScope.launch {
            getSavedCityIdsUseCase().collect { ids ->
                _uiState.value = _uiState.value.copy(
                    savedCityIds = ids.toSet()
                )
            }
        }
    }

    fun searchCity(city: String) {
        lastQuery = city
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val result = searchCityUseCase(city)
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    results = listOf(result),
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    results = emptyList(),
                    error = e.message ?: "Something went wrong"
                )
            }
        }
    }

    fun retry() {
        if (lastQuery.isNotBlank()) {
            searchCity(lastQuery)
        }
    }

    fun addCity(city: CityWeather) {
        viewModelScope.launch {
            addCityUseCase(city)
        }
    }
}
