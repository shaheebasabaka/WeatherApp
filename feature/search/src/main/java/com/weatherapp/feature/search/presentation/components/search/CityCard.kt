package com.weatherapp.feature.search.presentation.components.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.weatherapp.core.ui.spacing.Spacing
import com.weatherapp.core.ui.theme.*

@Composable
fun CityCard(
    cityName: String,
    temperature: String,
    condition: String,
    isAdded: Boolean = false,
    onAddClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = BackgroundPrimary
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.L),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.weight(1f)) {

                Text(
                    text = cityName,
                    style = TitleTextStyle,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(Spacing.S))

                Text(
                    text = temperature,
                    style = TemperatureTextStyle,
                    color = PrimaryBlue
                )

                Spacer(modifier = Modifier.height(Spacing.XS))

                Text(
                    text = condition,
                    style = BodyTextStyle,
                    color = TextSecondary
                )
            }

            if (isAdded) {
                // Show Tick Mark if already added
                Surface(
                    color = BackgroundPrimary,
                    shape = RoundedCornerShape(50),
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Added",
                        tint = SuccessGreen,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            } else {
                Button(
                    onClick = onAddClick,
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PrimaryBlue,
                        contentColor = BackgroundPrimary
                    )
                ) {
                    Text("+ Add")
                }
            }
        }
    }
}