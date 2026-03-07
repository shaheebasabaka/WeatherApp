package com.weatherapp.feature.locations.presentation.components


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.weatherapp.core.ui.spacing.Spacing

@Composable
fun EmptyLocationsState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Icon(
                imageVector = Icons.Default.LocationOff,
                contentDescription = "No saved locations",
                modifier = Modifier.size(80.dp)
            )

            Spacer(modifier = Modifier.height(Spacing.M))

            Text(
                text = "No Saved Locations",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(Spacing.S))

            Text(
                text = "Search and add cities to see them here.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}