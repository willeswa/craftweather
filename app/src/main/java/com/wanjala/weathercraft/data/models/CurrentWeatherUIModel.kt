package com.wanjala.weathercraft.data.models

data class CurrentWeatherUIModel(
    val temperature: Int,
    val maxTemperature: Int,
    val minTemperature: Int,
    val description: String,
    val extraInfo: List<ExtraWeatherInfo>,
    val timestamp: Long = System.currentTimeMillis()
)

