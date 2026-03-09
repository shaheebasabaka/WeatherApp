package com.weatherapp.feature.locations.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.core.ui.spacing.Spacing

@Composable
fun EmptyLocationsState() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(Spacing.L)
        ) {

            Icon(
                imageVector = Icons.Default.Cloud,
                contentDescription = "No saved locations",
                modifier = Modifier.size(80.dp),
                tint = Color(0xFFBDBDBD)
            )

            Spacer(modifier = Modifier.height(Spacing.L))

            Text(
                text = "No saved locations yet",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF212121),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(Spacing.S))

            Text(
                text = "Search for cities and add\nthem to your list",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF9E9E9E),
                textAlign = TextAlign.Center
            )
        }
    }
}