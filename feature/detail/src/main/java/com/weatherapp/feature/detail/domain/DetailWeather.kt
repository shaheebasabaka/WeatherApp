package com.weatherapp.feature.detail.domain


data class DetailWeather(
    val id: Int,
    val cityName: String,
    val nickname: String?,
    val temperature: Double,
    val feelsLike: Double,
    val condition: String,
    val minTemp: Double,
    val maxTemp: Double,
    val humidity: Int,
    val pressure: Int,
    val windSpeed: Double,
    val sunrise: String,
    val sunset: String
)