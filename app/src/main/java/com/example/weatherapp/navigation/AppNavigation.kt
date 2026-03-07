package com.example.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp.HomeScreen
import com.weatherapp.feature.search.presentation.search.SearchScreen
import com.weatherapp.feature.locations.presentation.screen.LocationsScreen
import com.weatherapp.feature.detail.presentation.DetailScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "search"
    ) {

        composable("home") {
            HomeScreen()
        }

        composable("search") {
            SearchScreen(
                onNavigateToLocations = {
                    navController.navigate("locations")
                }
            )
        }

        // ✅ PASS NAVIGATION CALLBACK
        composable("locations") {
            LocationsScreen(
                onLocationClick = { id ->
                    navController.navigate("detail/$id")
                }
            )
        }

        // DETAIL SCREEN
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