package com.weatherapp.core.network.api

import com.weatherapp.core.network.dto.CityDto
import com.weatherapp.core.network.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getWeatherByCity(
        @Query("q") city: String,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): CityDto

    @GET("weather")
    suspend fun getWeatherById(
        @Query("id") id: Int,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): CityDto

    // NEW: Call by Latitude and Longitude (Best for "Current Location")
    @GET("weather")
    suspend fun getWeatherByCoordinates(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") units: String = "metric"
    ): CityDto
}
