package com.weatherapp.feature.locations.domain.usecase


import androidx.paging.PagingData
import com.weatherapp.feature.locations.domain.model.SavedLocation
import com.weatherapp.feature.locations.domain.repository.LocationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedLocationsUseCase @Inject constructor(
    private val repository: LocationsRepository
) {
    operator fun invoke(): Flow<PagingData<SavedLocation>> {
        return repository.getSavedLocations()
    }
}