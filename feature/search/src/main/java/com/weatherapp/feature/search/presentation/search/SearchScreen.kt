package com.weatherapp.feature.search.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.weatherapp.core.ui.spacing.Spacing
import com.weatherapp.core.ui.theme.*
import com.weatherapp.core.ui.components.ErrorStateCard
import com.weatherapp.feature.search.presentation.components.search.CityCard
import com.weatherapp.feature.search.presentation.components.search.SearchBar
import com.weatherapp.feature.search.presentation.components.search.ShimmerLoadingCard

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onNavigateToLocations: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(BackgroundLight)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.XL)
        ) {
            Text(
                text = "1. Search Screen",
                style = MaterialTheme.typography.titleLarge,
                color = TextPrimary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(Spacing.XL))
            Text(
                text = "Search",
                style = MaterialTheme.typography.headlineLarge,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(Spacing.S))
            Text(
                text = "Find cities worldwide",
                style = MaterialTheme.typography.bodyLarge,
                color = TextSecondary
            )
            Spacer(modifier = Modifier.height(Spacing.L))
            SearchBar(
                onSearch = { city ->
                    viewModel.searchCity(city)
                }
            )

            Spacer(modifier = Modifier.height(Spacing.XL))

            if (state.isLoading) {
                ShimmerLoadingCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Spacing.M)
                )
            } else if (state.results.isEmpty() && state.error == null) {
                Text(
                    text = "Search for a city...",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Spacing.L),
                    textAlign = TextAlign.Center
                )
            } else if (state.error != null) {
                Spacer(modifier = Modifier.height(Spacing.S))
                ErrorStateCard(
                    modifier = Modifier.fillMaxWidth(),
                    onRetry = { viewModel.retry() }
                )
            }

            state.results.forEach { city ->
                Spacer(modifier = Modifier.height(Spacing.L))
                CityCard(
                    cityName = city.name,
                    temperature = "${city.temperature}°",
                    condition = city.condition,
                    onAddClick = {
                        viewModel.addCity(city)
                        onNavigateToLocations()
                    }
                )
            }

            Spacer(modifier = Modifier.height(Spacing.L))
        }
    }
}