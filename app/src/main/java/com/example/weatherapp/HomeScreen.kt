package com.example.weatherapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.weatherapp.core.ui.components.AppHeader
import com.weatherapp.core.ui.theme.PrimaryBlue
import com.weatherapp.core.ui.theme.SecondaryPurple
import com.weatherapp.feature.detail.presentation.DetailScreenCard
import com.weatherapp.feature.locations.presentation.screen.LocationsScreen
import com.weatherapp.feature.search.presentation.search.SearchScreen

@Composable
fun HomeScreen() {
    // Tracks which city was tapped in the Locations card
    var selectedCityId by remember { mutableStateOf<Int?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(PrimaryBlue, SecondaryPurple)
                )
            )
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {

            item {
                AppHeader()
            }

            // Card 1: Search
            item {
                SearchScreen(
                    modifier = Modifier.fillMaxWidth(),
                    onNavigateToLocations = { /* same page */ }
                )
            }

            // Card 2: Saved Locations — clicking a city updates selectedCityId
            item {
                LocationsScreen(
                    modifier = Modifier.fillMaxWidth(),
                    onLocationClick = { id ->
                        selectedCityId = id
                    }
                )
            }

            // Card 3: Detail — shows loading state until a city is clicked above
            item {
                DetailScreenCard(
                    modifier = Modifier.fillMaxWidth(),
                    selectedCityId = selectedCityId
                )
            }
        }
    }
}
