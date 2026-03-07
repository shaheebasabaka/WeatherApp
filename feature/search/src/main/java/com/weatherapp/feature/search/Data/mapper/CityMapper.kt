package com.weatherapp.feature.search.data.mapper

import com.weatherapp.core.network.dto.CityDto
import com.weatherapp.feature.search.domain.model.CityWeather

fun CityDto.toDomain(): CityWeather {
    return CityWeather(
        id = id,
        name = name,
        temperature = main.temp,
        condition = weather.firstOrNull()?.main ?: "",
        icon = weather.firstOrNull()?.icon ?: "",
        lat = coord.lat,
        lon = coord.lon
    )
}
