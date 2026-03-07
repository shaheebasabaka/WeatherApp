package com.weatherapp.feature.locations.domain.usecase

import com.weatherapp.feature.locations.domain.repository.LocationsRepository
import javax.inject.Inject

class DeleteLocationUseCase @Inject constructor(
    private val repository: LocationsRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteLocation(id)
    }
}