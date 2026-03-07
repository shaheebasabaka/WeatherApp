package com.weatherapp.feature.locations.domain.repository

data class CityWeather(
    val id: Int,
    val name: String,
    val icon: String,
    val condition: String,
    val temperature: Double
)
