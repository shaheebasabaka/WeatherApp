package com.weatherapp.feature.search.data.repository

import com.weatherapp.feature.search.data.mapper.toDomain
import com.weatherapp.feature.search.data.remote.datasource.SearchRemoteDataSource
import com.weatherapp.feature.search.domain.model.CityWeather
import com.weatherapp.feature.search.domain.repository.SearchRepository
import com.weatherapp.feature.locations.domain.model.SavedLocation
import com.weatherapp.feature.locations.domain.repository.LocationsRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val remoteDataSource: SearchRemoteDataSource,
    private val locationsRepository: LocationsRepository
) : SearchRepository {

    override suspend fun getCityWeather(city: String): CityWeather {
        return remoteDataSource.getWeather(city).toDomain()


    }

    override suspend fun addCity(city: CityWeather) {

        val savedLocation = SavedLocation(
            id = city.id,
            cityName = city.name,
            temperature = city.temperature,
            condition = city.condition,
            icon = city.icon,
            lat = city.lat,
            lon = city.lon
        )

        locationsRepository.saveLocation(savedLocation)
    }
}
