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

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()

    private var lastId: Int? = null

    fun loadByCoordinates(lat: Double, lon: Double) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                _state.value = repository.getDetailWeather(lat, lon)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadById(id: Int) {
        lastId = id
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            try {
                println("LOADING DETAIL FOR ID: $id")
                _state.value = repository.getDetailWeatherById(id)
            } catch (e: Exception) {
                e.printStackTrace()
                _error.value = e.message ?: "Connection failed"
                println("ERROR LOADING DETAIL: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun retry() {
        lastId?.let { loadById(it) }
    }

    fun updateNickname(id: Int, nickname: String) {
        viewModelScope.launch {
            repository.updateNickname(id, nickname)
        }
    }
}