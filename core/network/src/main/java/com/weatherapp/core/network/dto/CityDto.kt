package com.weatherapp.core.network.dto

import com.google.gson.annotations.SerializedName

data class CityDto(
    val id: Int,
    val name: String,
    val main: MainDto,
    val weather: List<WeatherDto>,
    val wind: WindDto,
    val sys: SysDto,
    val coord: CoordDto
)

data class CoordDto(
    val lat: Double,
    val lon: Double
)

data class MainDto(
    val temp: Double,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double,
    val pressure: Int,
    val humidity: Int
)

data class WeatherDto(
    val main: String,
    val description: String,
    val icon: String
)

data class WindDto(
    val speed: Double
)

data class SysDto(
    val sunrise: Long,
    val sunset: Long
)