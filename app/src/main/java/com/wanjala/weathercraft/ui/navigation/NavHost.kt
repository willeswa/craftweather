package com.wanjala.weathercraft.ui.navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Umbrella
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.sharp.Cloud
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wanjala.weathercraft.CitySearchUiState
import com.wanjala.weathercraft.MainViewModel
import com.wanjala.weathercraft.ui.screens.CitySearchScreen
import com.wanjala.weathercraft.ui.screens.HomeScreen


@Composable
fun WeatherApp(modifier: Modifier = Modifier, viewModel: MainViewModel = hiltViewModel()) {
    val navController = rememberNavController()
    val selectedCity by viewModel.selectedCity.collectAsState()
    val uistate = viewModel.weatherUiState.collectAsState() // Show loading until data is fetched
    val isLoading = selectedCity == null && uistate.value is CitySearchUiState.Loading

    Log.d("WeatherApp", "selectedCity: $selectedCity, isLoading: $isLoading")

            LaunchedEffect(selectedCity) {
        if (!isLoading) {
            // Navigate to the appropriate screen after determining the city state
            if (selectedCity != null) {
                navController.navigate(Screen.Home.route) {
                    popUpTo(0) // Clear back stack to make Home the root
                }
            } else {
                navController.navigate(Screen.CitySearch.route) {
                    popUpTo(0) // Clear back stack to make CitySearch the root
                }
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = if (isLoading) "loading" else Screen.CitySearch.route // Show loading first
    ) {
        composable("loading") {
            LoadingScreen()
        }
        composable(Screen.CitySearch.route) {
            CitySearchScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
            )
        }
    }
}


@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Loading...",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
