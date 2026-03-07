package com.weatherapp.feature.search.domain.model

data class CityWeather(
    val id: Int,
    val name: String,
    val temperature: Double,
    val condition: String,
    val icon: String,
    val lat: Double,
    val lon: Double
)
