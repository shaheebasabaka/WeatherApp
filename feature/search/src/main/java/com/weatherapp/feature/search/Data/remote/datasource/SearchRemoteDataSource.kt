package com.weatherapp.feature.search.data.remote.datasource

import com.weatherapp.core.network.api.WeatherApi
import com.weatherapp.core.network.dto.CityDto
import javax.inject.Inject

class SearchRemoteDataSource @Inject constructor(
    private val api: WeatherApi
) {

    suspend fun getWeather(city: String): CityDto {
        return api.getWeatherByCity(city)


    }
}