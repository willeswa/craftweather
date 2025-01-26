package com.wanjala.weathercraft.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Segment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.wanjala.weathercraft.MainViewModel
import com.wanjala.weathercraft.data.models.City
import com.wanjala.weathercraft.data.models.CurrentWeather
import com.wanjala.weathercraft.data.models.DailyForecast
import com.wanjala.weathercraft.ui.components.CurrentWeatherSection
import com.wanjala.weathercraft.ui.components.DailyForecastSection
import com.wanjala.weathercraft.ui.components.TopHomeSection
import com.wanjala.weathercraft.utils.getGradientForWeather

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val selectedCity by viewModel.selectedCity.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(getGradientForWeather("rain"))
            .padding(16.dp)
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            // Top section
            Spacer(modifier = Modifier.height(16.dp))
            TopHomeSection(
                city = selectedCity ?: City(), // Pass the selected city
                lastUpdate = "Today, ${System.currentTimeMillis()}",
                navController = navController
            )

            // Current weather (placeholder for now)
            Spacer(modifier = Modifier.height(16.dp))
            CurrentWeatherSection(viewModel = viewModel)

            // Daily forecast (placeholder for now)
            Spacer(modifier = Modifier.height(24.dp))
            DailyForecastSection(viewModel = viewModel)
        }
    }
}


