package com.weatherapp.feature.search.presentation.components.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
    onAddClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.L),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column {

                Text(
                    text = cityName,
                    style = MaterialTheme.typography.titleMedium,
                    color = TextPrimary
                )

                Spacer(modifier = Modifier.height(Spacing.S))

                Text(
                    text = temperature,
                    style = MaterialTheme.typography.displaySmall,
                    color = PrimaryBlue
                )

                Spacer(modifier = Modifier.height(Spacing.XS))

                Text(
                    text = condition,
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
            }

            Button(
                onClick = onAddClick,
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBlue,
                    contentColor = Color.White
                )
            ) {
                Text("+ Add")
            }
        }
    }
}