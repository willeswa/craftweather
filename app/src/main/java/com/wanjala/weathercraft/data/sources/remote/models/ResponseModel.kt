package com.wanjala.weathercraft.data.sources.remote.models

import com.google.gson.annotations.SerializedName

data class  CurrentWeatherResponse(
    @SerializedName("main") val main: Main,
    @SerializedName("weather") val weather: List<Weather>,
    @SerializedName("wind") val wind: Wind,
    @SerializedName("clouds") val clouds: Clouds,
    @SerializedName("name") val cityName: String
)

data class Main(
    @SerializedName("temp") val temp: Double,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("temp_min") val tempMin: Double,
    @SerializedName("temp_max") val tempMax: Double
)

data class Weather(
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class Wind(
    @SerializedName("speed") val speed: Double
)

data class Clouds(
    @SerializedName("all") val cloudiness: Int
)


data class ForecastResponse(
    @SerializedName("list") val list: List<ForecastItem>, // List of forecast entries
    @SerializedName("city") val city: CityInfo // Metadata about the city
)

data class ForecastItem(
    @SerializedName("dt") val timestamp: Long, // Unix timestamp of the forecast
    @SerializedName("main") val main: MainInfo, // Main weather information
    @SerializedName("weather") val weather: List<WeatherInfo>, // Weather details (e.g., rain, clouds)
    @SerializedName("dt_txt") val dtTxt: String // Human-readable datetime (e.g., "2025-01-26 15:00:00")
)

data class MainInfo(
    @SerializedName("temp") val temp: Double, // Current temperature in the forecast
    @SerializedName("temp_min") val tempMin: Double, // Minimum temperature
    @SerializedName("temp_max") val tempMax: Double, // Maximum temperature
    @SerializedName("humidity") val humidity: Int // Humidity percentage
)

data class WeatherInfo(
    @SerializedName("id") val id: Int, // Weather condition ID
    @SerializedName("main") val main: String, // General weather category (e.g., "Rain")
    @SerializedName("description") val description: String, // Detailed description (e.g., "light rain")
    @SerializedName("icon") val icon: String // Weather icon code (e.g., "10d")
)


data class CityInfo(
    @SerializedName("name") val name: String, // Name of the city
    @SerializedName("country") val country: String, // Country code (e.g., "US")
    @SerializedName("coord") val coord: Coordinates // Coordinates of the city
)


data class Coordinates(
    @SerializedName("lat") val latitude: Double, // Latitude of the city
    @SerializedName("lon") val longitude: Double // Longitude of the city
)
