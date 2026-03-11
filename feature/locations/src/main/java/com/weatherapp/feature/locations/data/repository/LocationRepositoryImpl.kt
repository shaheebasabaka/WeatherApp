package com.weatherapp.feature.locations.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.weatherapp.core.network.api.WeatherApi
import com.weatherapp.feature.locations.data.local.SavedLocationDao
import com.weatherapp.feature.locations.data.local.SavedLocationEntity
import com.weatherapp.feature.locations.domain.model.SavedLocation
import com.weatherapp.feature.locations.domain.repository.LocationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationsRepositoryImpl @Inject constructor(
    private val dao: SavedLocationDao,
    private val api: WeatherApi
) : LocationsRepository {

    override suspend fun saveLocation(location: SavedLocation) {
        val entity = SavedLocationEntity(
            cityId = location.id,
            cityName = location.cityName,
            temperature = location.temperature,
            condition = location.condition,
            icon = location.icon,
            lat = location.lat,
            lon = location.lon
        )
        dao.insert(entity)
    }

    override fun getSavedLocations(): Flow<PagingData<SavedLocation>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { dao.pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                SavedLocation(
                    id = entity.cityId,
                    cityName = entity.cityName,
                    temperature = entity.temperature,
                    condition = entity.condition,
                    icon = entity.icon,
                    lat = entity.lat,
                    lon = entity.lon
                )
            }
        }
    }

    override suspend fun deleteLocation(id: Int) {
        val entity = dao.getAll().find { it.cityId == id } ?: return
        dao.delete(entity)
    }

    override fun getSavedCityIds(): Flow<List<Int>> {
        return dao.getSavedCityIds()
    }

    override suspend fun refreshAllLocations() {
        val savedEntities = dao.getAll()
        savedEntities.forEach { entity ->
            try {
                val updatedWeather = api.getWeatherById(entity.cityId)
                val updatedEntity = entity.copy(
                    temperature = updatedWeather.main.temp,
                    condition = updatedWeather.weather.firstOrNull()?.main ?: entity.condition,
                    icon = updatedWeather.weather.firstOrNull()?.icon ?: entity.icon
                )
                dao.insert(updatedEntity)
            } catch (e: Exception) {
                // Ignore for individual city refresh failure
                e.printStackTrace()
            }
        }
    }
}