package com.weatherapp.feature.detail.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.weatherapp.core.ui.components.ErrorStateCard
import com.weatherapp.core.ui.theme.PrimaryBlue
import com.weatherapp.core.ui.theme.SecondaryPurple


@Composable
fun DetailScreenCard(
    modifier: Modifier = Modifier,
    selectedCityId: Int?,
    viewModel: DetailViewModel = hiltViewModel()
) {
    LaunchedEffect(selectedCityId) {
        if (selectedCityId != null) {
            viewModel.loadById(selectedCityId)
        }
    }

    val state by viewModel.state.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(32.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "3. Location Detail",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            when {
                error != null -> {
                    ErrorStateCard(
                        modifier = Modifier.fillMaxWidth(),
                        onRetry = { viewModel.retry() }
                    )
                }

                selectedCityId == null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(56.dp),
                                color = PrimaryBlue,
                                trackColor = Color(0xFFE8EAF6)
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "Fetching weather data...",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF9E9E9E)
                            )
                        }
                    }
                }

                isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(56.dp),
                                color = PrimaryBlue,
                                trackColor = Color(0xFFE8EAF6)
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = "Fetching weather data...",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color(0xFF9E9E9E)
                            )
                        }
                    }
                }

                state != null -> {
                    val weather = state!!
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(24.dp))
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(PrimaryBlue, SecondaryPurple)
                                )
                            )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = weather.cityName,
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Surface(
                                color = Color.White.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.padding(vertical = 8.dp)
                            ) {
                                Text(
                                    text = "🏠 ${weather.nickname ?: "Home"}",
                                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                                    color = Color.White
                                )
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = "${weather.temperature.toInt()}°",
                                fontSize = 72.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )

                            Text(
                                text = weather.condition,
                                style = MaterialTheme.typography.headlineSmall,
                                color = Color.White
                            )

                            Text(
                                text = "Feels like ${weather.feelsLike.toInt()}°",
                                color = Color.White.copy(alpha = 0.7f)
                            )

                            Spacer(modifier = Modifier.height(16.dp))


                            Row(Modifier.fillMaxWidth()) {
                                DetailItem("MIN / MAX", "${weather.minTemp.toInt()}° / ${weather.maxTemp.toInt()}°", Modifier.weight(1f))
                                DetailItem("HUMIDITY", "${weather.humidity}%", Modifier.weight(1f))
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            Row(Modifier.fillMaxWidth()) {
                                DetailItem("PRESSURE", "${weather.pressure} hPa", Modifier.weight(1f))
                                DetailItem("WIND", "${weather.windSpeed.toInt()} km/h", Modifier.weight(1f))
                            }

                            Spacer(modifier = Modifier.height(16.dp))

                            Surface(
                                color = Color(0xFFFFF9C4),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(20.dp),
                                    horizontalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    SunItem("SUNRISE", weather.sunrise)
                                    SunItem("SUNSET", weather.sunset)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


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

    if (isLoading || state == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(listOf(PrimaryBlue, SecondaryPurple))
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(
                    modifier = Modifier.size(56.dp),
                    color = Color.White,
                    trackColor = Color.White.copy(alpha = 0.3f)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Fetching weather data...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White
                )
            }
        }
        return
    }

    val weather = state!!

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(colors = listOf(PrimaryBlue, SecondaryPurple))
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 24.dp)
                .padding(top = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(onClick = onBack, modifier = Modifier.align(Alignment.Start)) {
                Text("← Back", color = Color.White)
            }

            Text(
                text = weather.cityName,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Surface(
                color = Color.White.copy(alpha = 0.2f),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Text(
                    text = "🏠 ${weather.nickname ?: "Home"}",
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "${weather.temperature.toInt()}°",
                fontSize = 100.sp,
                fontWeight = FontWeight.Black,
                color = Color.White
            )

            Text(text = weather.condition, style = MaterialTheme.typography.headlineSmall, color = Color.White)
            Text(text = "Feels like ${weather.feelsLike.toInt()}°", color = Color.White.copy(alpha = 0.7f))
        }

        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = Color.White,
            shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp).navigationBarsPadding()) {
                Row(Modifier.fillMaxWidth()) {
                    DetailItem("MIN / MAX", "${weather.minTemp.toInt()}° / ${weather.maxTemp.toInt()}°", Modifier.weight(1f))
                    DetailItem("HUMIDITY", "${weather.humidity}%", Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(Modifier.fillMaxWidth()) {
                    DetailItem("PRESSURE", "${weather.pressure} hPa", Modifier.weight(1f))
                    DetailItem("WIND", "${weather.windSpeed.toInt()} km/h", Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(24.dp))
                Surface(
                    color = Color(0xFFFFF9C4),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(modifier = Modifier.padding(20.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                        SunItem("SUNRISE", weather.sunrise)
                        SunItem("SUNSET", weather.sunset)
                    }
                }
            }
        }
    }
}

@Composable
fun DetailItem(label: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F9FA)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            Text(value, fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = Color.Black)
        }
    }
}

@Composable
fun SunItem(label: String, time: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF8D6E63))
        Text(time, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5D4037))
    }
}
