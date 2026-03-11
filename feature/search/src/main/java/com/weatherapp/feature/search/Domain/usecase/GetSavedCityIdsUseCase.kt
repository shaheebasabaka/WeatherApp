package com.weatherapp.feature.search.domain.usecase

import com.weatherapp.feature.locations.domain.repository.LocationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedCityIdsUseCase @Inject constructor(
    private val repository: LocationsRepository
) {
    operator fun invoke(): Flow<List<Int>> {
        return repository.getSavedCityIds()
    }
}
