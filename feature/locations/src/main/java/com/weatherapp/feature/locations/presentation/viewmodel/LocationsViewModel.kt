package com.weatherapp.feature.locations.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.weatherapp.feature.locations.domain.usecase.DeleteLocationUseCase
import com.weatherapp.feature.locations.domain.usecase.GetSavedLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.weatherapp.feature.locations.domain.usecase.RefreshSavedLocationsUseCase

@HiltViewModel
class LocationsViewModel @Inject constructor(
    getSavedLocationsUseCase: GetSavedLocationsUseCase,
    private val deleteLocationUseCase: DeleteLocationUseCase,
    private val refreshSavedLocationsUseCase: RefreshSavedLocationsUseCase
) : ViewModel() {

    val locations = getSavedLocationsUseCase()
        .cachedIn(viewModelScope)

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing.asStateFlow()

    fun deleteLocation(id: Int) {
        viewModelScope.launch {
            deleteLocationUseCase(id)
        }
    }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.value = true
            refreshSavedLocationsUseCase()
            _isRefreshing.value = false
        }
    }
}
