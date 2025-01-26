package com.wanjala.weathercraft.data.sources.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrentWeatherEntity::class, DailyForecastEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
