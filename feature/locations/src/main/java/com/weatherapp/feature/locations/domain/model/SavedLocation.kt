package com.weatherapp.feature.locations.domain.model

data class SavedLocation(
    val id: Int,
    val cityName: String,
    val temperature: Double,
    val condition: String,
    val icon: String,
    val lat: Double,
    val lon: Double
)
