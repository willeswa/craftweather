package com.wanjala.weathercraft.data.sources.remote

import com.wanjala.weathercraft.data.sources.remote.models.CurrentWeatherResponse
import com.wanjala.weathercraft.data.sources.remote.models.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric" // Defaults to metric
    ): CurrentWeatherResponse

    @GET("forecast")
    suspend fun getWeatherForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String = "metric" // Defaults to metric
    ): ForecastResponse
}
