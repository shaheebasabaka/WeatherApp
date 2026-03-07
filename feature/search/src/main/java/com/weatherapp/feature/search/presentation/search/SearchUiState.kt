package com.weatherapp.feature.search.presentation.search

import com.weatherapp.feature.search.domain.model.CityWeather

data class SearchUiState(
    val results: List<CityWeather> = emptyList(),
    val error: String? = null
)
