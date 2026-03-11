package com.weatherapp.feature.locations.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.weatherapp.core.ui.spacing.Spacing
import com.weatherapp.core.ui.theme.*

@Composable
fun WeatherCard(
    city: String,
    condition: String,
    temperature: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = onClick
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {

            // LEFT COLOR STRIP
            Box(
                modifier = Modifier
                    .width(Spacing.S)
                    .fillMaxHeight()
                    .background(PrimaryBlue)
            )

            // CONTENT AREA
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Spacing.M)
            ) {


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = city,
                        style = TitleTextStyle
                    )

                    Text(
                        text = temperature,
                        style = TemperatureTextStyle.copy(fontSize = 24.sp),
                        color = PrimaryBlue
                    )
                }

                Spacer(modifier = Modifier.height(Spacing.S))

                // 🏠 HOME LABEL
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Home",
                        tint = TextSecondary,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(modifier = Modifier.width(Spacing.XS))

                    Text(
                        text = "Home",
                        style = BodyTextStyle,
                        color = TextSecondary
                    )
                }

                Spacer(modifier = Modifier.height(Spacing.S))

                //  CONDITION
                Text(
                    text = condition,
                    style = BodyTextStyle,
                    color = TextSecondary
                )
            }
        }
    }
}