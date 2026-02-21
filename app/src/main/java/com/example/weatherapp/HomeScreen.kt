package com.example.weatherapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.weatherapp.core.ui.components.AppBackground
import com.weatherapp.core.ui.components.AppHeader
@Composable
fun HomeScreen() {

    AppBackground {

        Column(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {

            AppHeader()

            Spacer(modifier = Modifier.height(32.dp))



        }
    }
}
