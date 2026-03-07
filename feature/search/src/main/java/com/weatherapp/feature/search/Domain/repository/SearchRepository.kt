package com.weatherapp.feature.search.domain.repository

import com.weatherapp.feature.search.domain.model.CityWeather

interface SearchRepository {

    suspend fun getCityWeather(city: String): CityWeather

    suspend fun addCity(city: CityWeather)

}
