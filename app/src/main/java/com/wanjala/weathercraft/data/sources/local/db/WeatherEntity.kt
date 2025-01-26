package com.wanjala.weathercraft.data.sources.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "current_weather")
data class CurrentWeatherEntity(
    @PrimaryKey val city: String,
    val temperature: Int,
    val maxTemperature: Int,
    val minTemperature: Int,
    val description: String,
    val extraInfoJson: String, // JSON string for extra weather info
    val timestamp: Long // Cache timestamp
)


@Entity(tableName = "daily_forecast")
data class DailyForecastEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String, // Forecast date in a readable format (e.g., "2025-01-28 15:00:00")
    val tempMin: Double,
    val tempMax: Double,
    val description: String,
    val icon: String, // Weather icon code from the API
    val timestamp: Long // Cache timestamp
)