package com.weatherapp.feature.search.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.weatherapp.core.ui.components.AppBackground
import com.weatherapp.core.ui.spacing.Spacing
import com.weatherapp.core.ui.theme.*
import com.weatherapp.feature.search.presentation.components.search.CityCard
import com.weatherapp.feature.search.presentation.components.search.SearchBar
import androidx.compose.ui.text.style.TextAlign
@Composable
fun SearchScreen(
    onNavigateToLocations: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    AppBackground {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(Spacing.L)
                .clip(RoundedCornerShape(32.dp))
                .background(BackgroundLight)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Spacing.XL)
            ) {
                Text(
                    text = "1. Search Screen",
                    style = MaterialTheme.typography.titleLarge,
                    color = TextPrimary,
                    modifier = Modifier
                        .fillMaxWidth(),
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
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(Spacing.L),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.results) { city ->
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
                }
            }
        }
    }
}