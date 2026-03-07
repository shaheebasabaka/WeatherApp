package com.weatherapp.feature.locations.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.weatherapp.feature.locations.domain.model.SavedLocation

@Composable
fun LocationItem(
    location: SavedLocation,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    WeatherCard(
        city = location.cityName,
        condition = location.condition,
        temperature = "${location.temperature}°",
        modifier = modifier,
        onClick = onClick
    )
}