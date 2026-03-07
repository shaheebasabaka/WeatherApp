package com.weatherapp.feature.detail.data

import com.weatherapp.core.network.api.WeatherApi
import com.weatherapp.feature.detail.domain.DetailRepository
import com.weatherapp.feature.detail.domain.DetailWeather
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : DetailRepository {

    private var dummyNickname: String = "Home"

    override suspend fun getDetailWeather(
        lat: Double,
        lon: Double
    ): DetailWeather {

        val response = weatherApi.getWeatherByCoordinates(
            lat = lat,
            lon = lon
        )

        return DetailWeather(
            id = response.id,
            cityName = response.name,
            nickname = dummyNickname,

            // Main Weather
            temperature = response.main.temp,
            feelsLike = response.main.temp,
            condition = response.weather.firstOrNull()?.main ?: "Clear",

            // Additional Details
            minTemp = response.main.tempMin,
            maxTemp = response.main.tempMax,
            humidity = response.main.humidity,
            pressure = response.main.pressure,
            windSpeed = response.wind.speed,

            sunrise = formatUnixTime(response.sys.sunrise),
            sunset = formatUnixTime(response.sys.sunset)
        )
    }

    override suspend fun getDetailWeatherById(id: Int): DetailWeather {
        val response = weatherApi.getWeatherById(id = id)

        return DetailWeather(
            id = response.id,
            cityName = response.name,
            nickname = dummyNickname,

            // Main Weather
            temperature = response.main.temp,
            feelsLike = response.main.temp,
            condition = response.weather.firstOrNull()?.main ?: "Clear",

            // Additional Details
            minTemp = response.main.tempMin,
            maxTemp = response.main.tempMax,
            humidity = response.main.humidity,
            pressure = response.main.pressure,
            windSpeed = response.wind.speed,

            sunrise = formatUnixTime(response.sys.sunrise),
            sunset = formatUnixTime(response.sys.sunset)
        )
    }

    override suspend fun updateNickname(id: Int, nickname: String) {
        dummyNickname = nickname
    }

    private fun formatUnixTime(unixTime: Long): String {
        val date = Date(unixTime * 1000L)
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        return sdf.format(date)
    }
}