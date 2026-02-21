package com.weatherapp.feature.search.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.weatherapp.feature.search.presentation.components.search.CityCard
import com.weatherapp.feature.search.presentation.components.search.SearchBar

data class City(
    val name: String,
    val temp: String,
    val condition: String
)

val dummyCities = listOf(
    City("London", "15°C", "Cloudy"),
    City("New York", "22°C", "Sunny"),
    City("Tokyo", "18°C", "Rainy"),
    City("Paris", "17°C", "Partly Cloudy"),
    City("Sydney", "25°C", "Clear")
)

@Composable
fun SearchScreen() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 24.dp)
        ) {

            Text(
                text = "Search",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Find cities worldwide",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            SearchBar()

            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn {
                items(dummyCities) { city ->
                    CityCard(
                        cityName = city.name,
                        temperature = city.temp,
                        condition = city.condition
                    )
                }
            }
        }
    }
}
