package com.weatherapp.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.weatherapp.core.ui.theme.VioletDark
import com.weatherapp.core.ui.theme.VioletLight

@Composable
fun AppBackground(
    content: @Composable () -> Unit
) {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            VioletDark,
            VioletLight
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        content()
    }
}
