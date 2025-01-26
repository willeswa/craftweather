package com.wanjala.weathercraft.data.repositories

import android.util.Log
import com.wanjala.weathercraft.data.models.City
import com.wanjala.weathercraft.data.models.CurrentWeatherUIModel
import com.wanjala.weathercraft.data.models.ForecastUIModel
import com.wanjala.weathercraft.data.predefinedCities
import com.wanjala.weathercraft.data.sources.local.db.WeatherDao
import com.wanjala.weathercraft.data.sources.local.session.SessionManagerImpl
import com.wanjala.weathercraft.data.sources.remote.WeatherApiService
import com.wanjala.weathercraft.mappers.toEntity
import com.wanjala.weathercraft.mappers.toUIModel
import com.wanjala.weathercraft.utils.handleApiError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
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

                weatherDao.insertCurrentWeather(response.toEntity()) // Save new data to DB
            } catch (e: Exception) {
                Log.e("Repository", "Error fetching current weather: ${e.message}")
                handleApiError(e) // Log or handle server errors
            }
            // Return data from Room
            val cachedWeather = weatherDao.getCurrentWeather()
            Log.d("======>", cachedWeather?.toString() ?: "no data")
            cachedWeather?.toUIModel() ?: throw Exception("No data available")
        }
    }


    suspend fun getWeatherForecast(lat: Double, lon: Double): ForecastUIModel {
        return withContext(Dispatchers.IO) {
            try {
                val response = weatherApiService.getWeatherForecast(latitude = lat, longitude = lon)
                response.toUIModel() // Map ForecastResponse to ForecastUIModel
            } catch (e: Exception) {
                throw handleApiError(e)
            }
        }
    }

    suspend fun saveSelectedCity(city: City) {
        sessionManager.saveSelectedCity(city)
    }

    fun getSelectedCity(): Flow<City?> {
        return sessionManager.getSelectedCity()
    }
}
