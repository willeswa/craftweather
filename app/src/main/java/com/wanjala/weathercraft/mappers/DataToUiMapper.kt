package com.wanjala.weathercraft.mappers


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.sharp.Air
import androidx.compose.material.icons.sharp.CloudQueue
import com.wanjala.weathercraft.data.models.CurrentWeatherUIModel
import com.wanjala.weathercraft.data.models.DailyForecastUIModel
import com.wanjala.weathercraft.data.models.ExtraWeatherInfo
import com.wanjala.weathercraft.data.models.ForecastUIModel
import com.wanjala.weathercraft.data.sources.remote.models.CurrentWeatherResponse
import com.wanjala.weathercraft.data.sources.remote.models.ForecastResponse
import java.util.Locale

// Extension function to map CurrentWeatherResponse to CurrentWeatherUIModel
fun CurrentWeatherResponse.toUIModel(): CurrentWeatherUIModel {
    return CurrentWeatherUIModel(
        temperature = main.temp.toInt(),
        maxTemperature = main.tempMax.toInt(),
        minTemperature = main.tempMin.toInt(),
        description = weather.firstOrNull()?.description?.capitalize(Locale.ROOT) ?: "Unknown",
        extraInfo = listOf(
            ExtraWeatherInfo(
                title = "Wind Speed",
                subtitle = "${wind.speed} km/h",
                icon = Icons.Sharp.Air
            ),
            ExtraWeatherInfo(
                title = "Humidity",
                subtitle = "${main.humidity}%",
                icon = Icons.Filled.WaterDrop
            ),
            ExtraWeatherInfo(
                title = "Clouds",
                subtitle = "${clouds.cloudiness}%",
                icon = Icons.Sharp.CloudQueue
            )
        )
    )
}


    fun ForecastResponse.toUIModel(): ForecastUIModel {
    return ForecastUIModel(
        dailyForecasts = this.list.map { forecast ->
            DailyForecastUIModel(
                date = forecast.dtTxt, // Assuming API returns datetime in `dt_txt`
                tempMin = forecast.main.tempMin,
                tempMax = forecast.main.tempMax,
                description = forecast.weather.firstOrNull()?.description ?: "N/A",
                icon = forecast.weather.firstOrNull()?.icon ?: "01d" // Default sunny icon
            )
        }
    )
}
