package com.wanjala.weathercraft.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wanjala.weathercraft.ui.screens.CitySearchScreen
import com.wanjala.weathercraft.ui.screens.HomeScreen
import com.wanjala.weathercraft.ui.screens.OnboardingScreen

@Composable
fun WeatherApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Onboarding.route
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen(navController)
        }
        composable(Screen.CitySearch.route) {
            CitySearchScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }

    }
}