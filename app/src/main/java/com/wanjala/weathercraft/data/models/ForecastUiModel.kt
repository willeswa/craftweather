package com.wanjala.weathercraft.data.models

data class ForecastUIModel(
    val dailyForecasts: List<DailyForecastUIModel>
)

data class DailyForecastUIModel(
    val date: String, // e.g., "2025-01-26"
    val tempMin: Double,
    val tempMax: Double,
    val description: String,
    val icon: String
)