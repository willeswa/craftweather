package com.wanjala.weathercraft.data.models

import androidx.compose.ui.graphics.vector.ImageVector

data class ExtraWeatherInfo(
    val title: String,       // e.g., "14 km/h", "Humidity 70%"
    val subtitle: String,    // Description, e.g., "Gentle breeze now"
)
