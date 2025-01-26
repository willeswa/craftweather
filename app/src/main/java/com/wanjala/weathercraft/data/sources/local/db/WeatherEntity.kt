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
