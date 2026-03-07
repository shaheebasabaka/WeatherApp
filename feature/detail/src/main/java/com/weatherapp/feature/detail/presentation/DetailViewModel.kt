package com.weatherapp.feature.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weatherapp.feature.detail.domain.DetailWeather
import com.weatherapp.feature.detail.domain.DetailRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository
) : ViewModel() {

    private val _state = MutableStateFlow<DetailWeather?>(null)
    val state: StateFlow<DetailWeather?> = _state.asStateFlow()

    fun loadByCoordinates(lat: Double, lon: Double) {
        viewModelScope.launch {
            _state.value = repository.getDetailWeather(lat, lon)
        }
    }

    fun loadById(id: Int) {
        viewModelScope.launch {
            _state.value = repository.getDetailWeatherById(id)
        }
    }

    fun updateNickname(id: Int, nickname: String) {
        viewModelScope.launch {
            repository.updateNickname(id, nickname)
        }
    }
}