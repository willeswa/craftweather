package com.wanjala.weathercraft.data.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.ui.graphics.vector.ImageVector

data class Weather(
    val id: Int,           // Weather condition id
    val main: String,      // Group of weather parameters (Rain, Snow, Clouds, etc.)
    val description: String,  // Weather condition within the group
    val icon: String       // Weather icon id
)

data class CurrentWeather(
    val temp: Int,
    val minTemp: Int,
    val maxTemp: Int,
    val windSpeed: Int,
    val clouds: Int,
    val humidity: Int,
    val description: String,
    val lastRecorded: String
)

data class DailyForecast(
    val dayName: String,      // e.g. "Mon", "Tue", ...
    val minTemp: Int,
    val maxTemp: Int,
    val description: String,
    // Use an ImageVector or painter resource for an icon if you like
    val icon: ImageVector = Icons.Default.Cloud
)