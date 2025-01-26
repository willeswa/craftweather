package com.wanjala.weathercraft.data.sources.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentWeather(weather: CurrentWeatherEntity)

    @Query("SELECT * FROM current_weather LIMIT 1")
    suspend fun getCurrentWeather(): CurrentWeatherEntity?

    @Query("DELETE FROM current_weather")
    suspend fun deleteCurrentWeather()

    // Insert forecast data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecasts(forecasts: List<DailyForecastEntity>)

    // Get all forecasts
    @Query("SELECT * FROM daily_forecast ORDER BY date ASC")
    suspend fun getAllForecasts(): List<DailyForecastEntity>

    // Clear all forecast data
    @Query("DELETE FROM daily_forecast")
    suspend fun clearForecasts()

    @Query("DELETE FROM current_weather")
    suspend fun clearCurrentWeather()
}