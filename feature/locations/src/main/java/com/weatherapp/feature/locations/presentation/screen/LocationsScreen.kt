package com.weatherapp.feature.locations.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.weatherapp.core.ui.spacing.Spacing
import com.weatherapp.core.ui.theme.BackgroundLight
import com.weatherapp.feature.locations.presentation.components.*
import com.weatherapp.feature.locations.presentation.viewmodel.LocationsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationsScreen(
    modifier: Modifier = Modifier,
    onLocationClick: (Int) -> Unit,
    viewModel: LocationsViewModel = hiltViewModel()
) {

    val pagingItems = viewModel.locations.collectAsLazyPagingItems()
    val refreshState = rememberPullToRefreshState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(Spacing.L)
            .clip(RoundedCornerShape(32.dp))
            .background(BackgroundLight)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            Text(
                text = "2. Saved Locations",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(Spacing.XS))

            Text(
                text = "My Locations",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(Spacing.L))

            PullToRefreshBox(
                state = refreshState,
                isRefreshing = pagingItems.loadState.refresh is androidx.paging.LoadState.Loading,
                onRefresh = { pagingItems.refresh() },
                modifier = Modifier.fillMaxSize()
            ) {

                if (pagingItems.itemCount == 0 &&
                    pagingItems.loadState.refresh !is androidx.paging.LoadState.Loading
                ) {
                    EmptyLocationsState()
                } else {

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(Spacing.M)
                    ) {

                        items(pagingItems.itemCount) { index ->

                            pagingItems[index]?.let { location ->

                                val dismissState = rememberSwipeToDismissBoxState()

                                SwipeToDismissBox(
                                    state = dismissState,

                                    backgroundContent = {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clip(RoundedCornerShape(32.dp))
                                                .background(MaterialTheme.colorScheme.error),
                                            contentAlignment = Alignment.CenterEnd
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Delete,
                                                contentDescription = null,
                                                tint = MaterialTheme.colorScheme.onError,
                                                modifier = Modifier.padding(end = Spacing.L)
                                            )
                                        }
                                    },

                                    content = {
                                        LocationItem(
                                            location = location,
                                            modifier = Modifier.fillMaxWidth(),

                                            // ✅ CLICK NAVIGATION
                                            onClick = {
                                                onLocationClick(location.id)
                                            }
                                        )
                                    }
                                )

                                LaunchedEffect(dismissState.currentValue) {
                                    if (dismissState.currentValue ==
                                        SwipeToDismissBoxValue.EndToStart
                                    ) {
                                        viewModel.deleteLocation(location.id)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}