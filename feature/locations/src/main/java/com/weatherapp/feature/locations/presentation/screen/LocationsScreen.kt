package com.weatherapp.feature.locations.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.weatherapp.core.ui.spacing.Spacing
import com.weatherapp.core.ui.theme.*
import com.weatherapp.core.ui.components.WeatherScreenWrapper
import com.weatherapp.feature.locations.presentation.components.*
import com.weatherapp.feature.locations.presentation.viewmodel.LocationsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsScreen(
    modifier: Modifier = Modifier,
    onLocationClick: (Int) -> Unit,
    onNavigateToSearch: () -> Unit,
    viewModel: LocationsViewModel = hiltViewModel()
) {
    val pagingItems = viewModel.locations.collectAsLazyPagingItems()
    val scrollState = rememberScrollState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    WeatherTheme {
        WeatherScreenWrapper(
            title = "2. Saved Locations",
            bottomBar = {
                LocationsBottomBar(
                    currentScreen = "locations",
                    onNavigateToLocations = {},
                    onNavigateToSearch = onNavigateToSearch
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(scrollState)
                    .padding(Spacing.L)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { viewModel.refresh() }) {
                        if (isRefreshing) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = PrimaryBlue,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Refresh",
                                tint = PrimaryBlue
                            )
                        }
                    }

                    Text(
                        text = "My Locations",
                        style = DisplayTextStyle,
                        color = TextPrimary
                    )

                    Spacer(modifier = Modifier.width(48.dp))
                }

                Spacer(modifier = Modifier.height(Spacing.M))

                if (pagingItems.loadState.refresh is androidx.paging.LoadState.Loading) {
                    Box(modifier = Modifier.fillMaxWidth().height(200.dp), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = PrimaryBlue)
                    }
                } else if (pagingItems.itemCount == 0) {
                    EmptyLocationsState()
                } else {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(Spacing.M)
                    ) {
                        for (index in 0 until pagingItems.itemCount) {
                            pagingItems[index]?.let { location ->
                                val dismissState = rememberSwipeToDismissBoxState()

                                SwipeToDismissBox(
                                    state = dismissState,
                                    backgroundContent = {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(RoundedCornerShape(32.dp))
                                                .background(ErrorRed),
                                            contentAlignment = Alignment.CenterEnd
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = "Delete",
                                                tint = BackgroundPrimary,
                                                modifier = Modifier.padding(end = Spacing.L)
                                            )
                                        }
                                    },
                                    content = {
                                        LocationItem(
                                            location = location,
                                            modifier = Modifier.fillMaxWidth(),
                                            onClick = { onLocationClick(location.id) }
                                        )
                                    }
                                )

                                LaunchedEffect(dismissState.currentValue) {
                                    if (dismissState.currentValue == SwipeToDismissBoxValue.EndToStart) {
                                        viewModel.deleteLocation(location.id)
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(Spacing.L))
            }
        }
    }
}

@Composable
fun LocationsBottomBar(
    currentScreen: String,
    onNavigateToLocations: () -> Unit,
    onNavigateToSearch: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        color = BackgroundLight,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LocationsNavItem(
                label = "Locations",
                icon = Icons.Outlined.Home,
                isSelected = currentScreen == "locations",
                onClick = onNavigateToLocations
            )

            LocationsNavItem(
                label = "Search",
                icon = Icons.Outlined.Search,
                isSelected = currentScreen == "search",
                onClick = onNavigateToSearch
            )
        }
    }
}

@Composable
fun LocationsNavItem(
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