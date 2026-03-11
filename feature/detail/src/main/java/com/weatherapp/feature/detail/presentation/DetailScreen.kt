package com.weatherapp.feature.detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.weatherapp.core.ui.theme.*
import com.weatherapp.core.ui.components.WeatherScreenWrapper
import com.weatherapp.feature.detail.presentation.components.DetailWeatherCard


@Composable
fun DetailScreen(
    id: Int,
    onBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(id) {
        viewModel.loadById(id)
    }

    val state by viewModel.state.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val scrollState = rememberScrollState()

    WeatherTheme {
        WeatherScreenWrapper { paddingValues ->
            if (isLoading || state == null) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(56.dp),
                            color = PrimaryBlue,
                            trackColor = BackgroundPrimary
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Fetching weather data...",
                            style = BodyTextStyle,
                            color = TextSecondary
                        )
                    }
                }
            } else {
                val weather = state!!
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .verticalScroll(scrollState)
                ) {
                    DetailWeatherCard(
                        weather = weather,
                        onBack = onBack,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
