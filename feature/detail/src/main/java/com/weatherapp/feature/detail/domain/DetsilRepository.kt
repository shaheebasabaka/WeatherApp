package com.weatherapp.feature.detail.domain

interface DetailRepository {

    suspend fun getDetailWeather(
        lat: Double,
        lon: Double
    ): DetailWeather

    suspend fun getDetailWeatherById(id: Int): DetailWeather

    suspend fun updateNickname(id: Int, nickname: String)
}