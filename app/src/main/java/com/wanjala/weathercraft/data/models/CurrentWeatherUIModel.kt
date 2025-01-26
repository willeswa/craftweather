package com.wanjala.weathercraft.data.models

data class CurrentWeatherUIModel(
    val temperature: Int, // Current temperature
    val maxTemperature: Int, // Maximum temperature
    val minTemperature: Int, // Minimum temperature
    val description: String, // Weather description, e.g., "Mostly Cloudy"
    val extraInfo: List<ExtraWeatherInfo> // Additional weather details like wind speed, humidity
)

