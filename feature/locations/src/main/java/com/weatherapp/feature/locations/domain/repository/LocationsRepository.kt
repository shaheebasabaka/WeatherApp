package com.weatherapp.feature.locations.domain.repository


import androidx.paging.PagingData
import com.weatherapp.feature.locations.domain.model.SavedLocation
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {

    fun getSavedLocations(): Flow<PagingData<SavedLocation>>

    suspend fun saveLocation(location: SavedLocation)

    suspend fun deleteLocation(id: Int)

    fun getSavedCityIds(): Flow<List<Int>>

    suspend fun refreshAllLocations()
}