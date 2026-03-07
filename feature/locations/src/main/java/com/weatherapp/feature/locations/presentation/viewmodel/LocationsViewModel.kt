package com.weatherapp.feature.locations.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.weatherapp.feature.locations.domain.usecase.DeleteLocationUseCase
import com.weatherapp.feature.locations.domain.usecase.GetSavedLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationsViewModel @Inject constructor(
    getSavedLocationsUseCase: GetSavedLocationsUseCase,
    private val deleteLocationUseCase: DeleteLocationUseCase
) : ViewModel() {

    val locations = getSavedLocationsUseCase()
        .cachedIn(viewModelScope)

    fun deleteLocation(id: Int) {
        viewModelScope.launch {
            deleteLocationUseCase(id)
        }
    }
}
