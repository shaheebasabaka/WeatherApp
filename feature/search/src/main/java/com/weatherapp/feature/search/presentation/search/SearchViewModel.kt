package com.weatherapp.feature.search.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherapp.feature.search.domain.model.CityWeather
import com.weatherapp.feature.search.domain.usecase.AddCityUseCase
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
    private val addCityUseCase: AddCityUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    fun searchCity(city: String) {
        viewModelScope.launch {
            try {
                val result = searchCityUseCase(city)

                println("API RESULT = $result")

                _uiState.value = _uiState.value.copy(
                    results = listOf(result),
                    error = null
                )

            } catch (e: Exception) {

                e.printStackTrace()
                println("ERROR = ${e.message}")

                _uiState.value = _uiState.value.copy(
                    results = emptyList(),
                    error = e.message ?: "Something went wrong"
                )
            }
        }
    }

    fun addCity(city: CityWeather) {
        viewModelScope.launch {
            addCityUseCase(city)
        }
    }
}
