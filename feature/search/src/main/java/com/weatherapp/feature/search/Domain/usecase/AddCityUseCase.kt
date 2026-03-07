package com.weatherapp.feature.search.domain.usecase

import com.weatherapp.feature.search.domain.model.CityWeather
import com.weatherapp.feature.search.domain.repository.SearchRepository
import javax.inject.Inject

class AddCityUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(city: CityWeather) {
        repository.addCity(city)
    }
}