package com.weatherapp.feature.search.presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.weatherapp.core.ui.spacing.Spacing
import com.weatherapp.core.ui.theme.*
import com.weatherapp.core.ui.components.ErrorStateCard
import com.weatherapp.core.ui.components.WeatherScreenWrapper
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
    WeatherTheme {
        WeatherScreenWrapper(
            title = "1. Search Screen",
            bottomBar = {
                SearchBottomBar(
                    currentScreen = "search",
                    onNavigateToLocations = onNavigateToLocations,
                    onNavigateToSearch = {}
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(Spacing.L)
            ) {
                Text(
                    text = "Search",
                    style = DisplayTextStyle,
                    color = TextPrimary
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
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Search for a city...",
                            style = BodyTextStyle,
                            color = TextSecondary,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )
                    }
                } else if (state.error != null) {
                    Spacer(modifier = Modifier.height(Spacing.S))
                    ErrorStateCard(
                        modifier = Modifier.fillMaxWidth(),
                        onRetry = { viewModel.retry() }
                    )
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    state.results.forEach { city ->
                        Spacer(modifier = Modifier.height(Spacing.L))
                        CityCard(
                            cityName = city.name,
                            temperature = "${city.temperature.toInt()}°",
                            condition = city.condition,
                            isAdded = state.savedCityIds.contains(city.id),
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

@Composable
fun SearchBottomBar(
    currentScreen: String,
    onNavigateToLocations: () -> Unit,
    onNavigateToSearch: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        color = BackgroundPrimary ,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Button for Locations
            SearchNavItem(
                label = "Locations",
                icon = Icons.Outlined.Home,
                isSelected = currentScreen == "locations",
                onClick = onNavigateToLocations
            )

            // Button for Search
            SearchNavItem(
                label = "Search",
                icon = Icons.Outlined.Search,
                isSelected = currentScreen == "search",
                onClick = onNavigateToSearch
            )
        }
    }
}

@Composable
fun SearchNavItem(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) BackgroundPrimary else Color.Transparent
    val contentColor = if (isSelected) PrimaryBlue else TextSecondary

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(horizontal = 20.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(80.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = contentColor,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(Spacing.XS))
            Text(
                text = label,
                color = contentColor,
                style = BodyTextStyle,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}
