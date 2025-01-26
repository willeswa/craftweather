package com.wanjala.weathercraft.mappers


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.sharp.Air
import androidx.compose.material.icons.sharp.CloudQueue
import com.google.gson.Gson
import com.wanjala.weathercraft.data.models.CurrentWeatherUIModel
import com.wanjala.weathercraft.data.models.DailyForecastUIModel
import com.wanjala.weathercraft.data.models.ExtraWeatherInfo
import com.wanjala.weathercraft.data.models.ForecastUIModel
import com.wanjala.weathercraft.data.sources.local.db.CurrentWeatherEntity
import com.wanjala.weathercraft.data.sources.local.db.DailyForecastEntity
import com.wanjala.weathercraft.data.sources.remote.models.CurrentWeatherResponse
import com.wanjala.weathercraft.data.sources.remote.models.ForecastResponse
import java.util.Locale

// Extension function to map CurrentWeatherResponse to CurrentWeatherUIModel
fun CurrentWeatherResponse.toUIModel(): CurrentWeatherUIModel {
    return CurrentWeatherUIModel(
        temperature = main.temp.toInt(),
        maxTemperature = main.tempMax.toInt(),
        minTemperature = main.tempMin.toInt(),
        description = weather.firstOrNull()?.description?.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }
            ?: "Unknown",
        extraInfo = listOf(
            ExtraWeatherInfo(
                title = "Wind Speed",
                subtitle = "${wind.speed} km/h",
            ),
            ExtraWeatherInfo(
                title = "Humidity",
                subtitle = "${main.humidity}%",
            ),
            ExtraWeatherInfo(
                title = "Clouds",
                subtitle = "${clouds.cloudiness}%",
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

fun CurrentWeatherEntity.toUIModel(): CurrentWeatherUIModel {
    val extraInfoItems = Gson().fromJson(extraInfoJson, Array<ExtraWeatherInfo>::class.java).toList()
    return CurrentWeatherUIModel(
        temperature = temperature,
        maxTemperature = maxTemperature,
        minTemperature = minTemperature,
        description = description,
        extraInfo = extraInfoItems,
        timestamp = timestamp
    )
}


fun CurrentWeatherResponse.toEntity(): CurrentWeatherEntity {
    return CurrentWeatherEntity(
        city = this.cityName,
        temperature = this.main.temp.toInt(),
        maxTemperature = this.main.tempMax.toInt(),
        minTemperature = this.main.tempMin.toInt(),
        description = this.weather.firstOrNull()?.description ?: "Unknown",
        extraInfoJson = Gson().toJson(
            listOf(
                ExtraWeatherInfo("Wind Speed", "${this.wind.speed} km/h"),
                ExtraWeatherInfo("Humidity", "${this.main.humidity}%"),
                ExtraWeatherInfo("Clouds", "${this.clouds.cloudiness}%")
            )
        ),
        timestamp = System.currentTimeMillis()
    )
}


fun ForecastResponse.toEntities(): List<DailyForecastEntity> {
    return list.map { forecast ->
        DailyForecastEntity(
            date = forecast.dtTxt, // Datetime from the API
            tempMin = forecast.main.tempMin,
            tempMax = forecast.main.tempMax,
            description = forecast.weather.firstOrNull()?.description ?: "N/A",
            icon = forecast.weather.firstOrNull()?.icon ?: "01d", // Default sunny icon
            timestamp = System.currentTimeMillis()
        )
    }
}


fun DailyForecastEntity.toUIModel(): DailyForecastUIModel {
    return DailyForecastUIModel(
        date = this.date,
        tempMin = this.tempMin,
        tempMax = this.tempMax,
        description = this.description,
        icon = this.icon
    )
}
