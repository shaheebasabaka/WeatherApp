package com.weatherapp.feature.detail.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.core.ui.spacing.Spacing
import com.weatherapp.core.ui.theme.*
import com.weatherapp.feature.detail.domain.DetailWeather

@Composable
fun DetailWeatherCard(
    weather: DetailWeather,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                color = PrimaryBlue.copy(alpha = 0.1f),
                shape = MaterialTheme.shapes.small,
                modifier = Modifier.align(Alignment.Start)
            ) {
                TextButton(onClick = onBack) {
                    Text("← Back", color = PrimaryBlue, style = BodyTextStyle)
                }
            }

            Spacer(modifier = Modifier.height(Spacing.M))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.large)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(PrimaryBlue, SecondaryPurple)
                        )
                    )
                    .padding(Spacing.L),
                     color = Color.Transparent


            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = weather.cityName,
                        style = DisplayTextStyle,
                        color = BackgroundPrimary
                    )

                    Surface(
                        color = BackgroundPrimary.copy(alpha = 0.2f),
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.padding(vertical = Spacing.S)
                    ) {
                        Text(
                            text = "🏠 ${weather.nickname ?: "Home"}",
                            modifier = Modifier.padding(horizontal = Spacing.M, vertical = Spacing.XS),
                            style = BodyTextStyle,
                            color = BackgroundPrimary
                        )
                    }

                    Spacer(modifier = Modifier.height(Spacing.L))

                    Text(
                        text = "${weather.temperature.toInt()}°",
                        style = TemperatureTextStyle.copy(fontSize = 100.sp),
                        color = BackgroundPrimary
                    )

                    Text(text = weather.condition, style = HeadlineTextStyle, color = Color.White)
                    Text(
                        text = "Feels like ${weather.feelsLike.toInt()}°",
                        style = BodyTextStyle,
                        color = BackgroundPrimary.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(Spacing.L))

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
                color = SunSectionBackground,
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(modifier = Modifier.padding(20.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                    SunItem("SUNRISE", weather.sunrise)
                    SunItem("SUNSET", weather.sunset)
                }
            }
            Spacer(modifier = Modifier.height(Spacing.L))
        }
    }
}

@Composable
fun DetailItem(label: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(Spacing.XS),
        colors = CardDefaults.cardColors(containerColor = BackgroundLight),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier.padding(Spacing.M)) {
            Text(label, style = BodyTextStyle, color = TextSecondary)
            Text(value, style = TemperatureTextStyle.copy(fontSize = 32.sp), color = TextPrimary)
        }
    }
}

@Composable
fun SunItem(label: String, time: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(label, style = BodyTextStyle, color = AccentBrownLight)
        Text(time, style = TemperatureTextStyle.copy(fontSize = 32.sp), color = AccentBrownDark)
    }
}
