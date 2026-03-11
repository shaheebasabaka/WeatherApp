package com.weatherapp.feature.locations.domain.usecase

import com.weatherapp.feature.locations.domain.repository.LocationsRepository
import javax.inject.Inject

class RefreshSavedLocationsUseCase @Inject constructor(
    private val repository: LocationsRepository
) {
    suspend operator fun invoke() {
        repository.refreshAllLocations()
    }
}
