package com.wanjala.weathercraft.data.repositories

import android.util.Log
import com.wanjala.weathercraft.data.models.City
import com.wanjala.weathercraft.data.models.CurrentWeatherUIModel
import com.wanjala.weathercraft.data.models.DailyForecastUIModel

import com.wanjala.weathercraft.data.sources.local.db.WeatherDao
import com.wanjala.weathercraft.data.sources.local.session.SessionManagerImpl
import com.wanjala.weathercraft.data.sources.remote.WeatherApiService
import com.wanjala.weathercraft.mappers.toEntities
import com.wanjala.weathercraft.mappers.toEntity
import com.wanjala.weathercraft.mappers.toUIModel
import com.wanjala.weathercraft.utils.handleApiError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import predefinedCities
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val sessionManager: SessionManagerImpl,
    private val weatherApiService: WeatherApiService,
    private val weatherDao: WeatherDao
) {

    suspend fun getCitySuggestions(query: String): List<City> {
        delay(1000)
        return predefinedCities.filter { city ->
            city.name.contains(query, ignoreCase = true) ||
                    city.country.contains(query, ignoreCase = true)
        }
    }

    suspend fun getCurrentWeather(lat: Double, lon: Double): CurrentWeatherUIModel {
        return withContext(Dispatchers.IO) {
            try {
                // Fetch data from server
                val response = weatherApiService.getCurrentWeather(latitude = lat, longitude = lon)
                weatherDao.clearCurrentWeather()
                weatherDao.insertCurrentWeather(response.toEntity()) // Save to DB
            } catch (e: Exception) {
                Log.e("Repository", "Error fetching current weather: ${e.message}")
                // Log but proceed to fetch from DB
            }
            // Return cached data
            val cachedWeather = weatherDao.getCurrentWeather()
            cachedWeather?.toUIModel() ?: throw Exception("No current weather data available")
        }
    }



    suspend fun getWeatherForecast(lat: Double, lon: Double): List<DailyForecastUIModel> {
        return withContext(Dispatchers.IO) {
            try {
                // Fetch data from server
                val response = weatherApiService.getWeatherForecast(latitude = lat, longitude = lon)
                val forecastEntities = response.toEntities()

                // Clear and update forecasts in DB
                weatherDao.clearForecasts()
                weatherDao.insertForecasts(forecastEntities)
            } catch (e: Exception) {
                Log.e("Repository", "Error fetching forecast: ${e.message}")
                // Log but proceed to fetch from DB
            }
            // Return cached data
            val cachedForecasts = weatherDao.getAllForecasts()
            if (cachedForecasts.isEmpty()) {
                throw Exception("No forecast data available")
            }
            cachedForecasts.map { it.toUIModel() }
        }
    }


    suspend fun saveSelectedCity(city: City) {
        sessionManager.saveSelectedCity(city)
    }

    fun getSelectedCity(): Flow<City?> {
        return sessionManager.getSelectedCity()
    }
}
