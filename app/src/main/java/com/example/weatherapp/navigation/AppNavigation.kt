package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.HomeScreen
import com.weatherapp.feature.detail.presentation.DetailScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"

    ) {

        composable("home") {
            HomeScreen()
        }

        // DETAIL SCREEN - still navigates to its own full-screen page
        composable("detail/{id}") { backStackEntry ->

            val id = backStackEntry.arguments
                ?.getString("id")?.toInt() ?: 0

            DetailScreen(
                id = id,
                onBack = { navController.popBackStack() }
            )
        }
    }
}