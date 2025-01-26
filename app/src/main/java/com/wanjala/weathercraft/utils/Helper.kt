package com.wanjala.weathercraft.utils

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import retrofit2.HttpException
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun handleApiError(e: Exception): Exception {
    return when (e) {
        is IOException -> {
            Exception("Unable to connect to the server. Please check your internet connection.")
        }
        is HttpException -> {
            val errorMessage = when (e.code()) {
                400 -> "Bad request. Please try again."
                401 -> "Unauthorized. Please check your API key."
                404 -> "City or data not found. Please check the coordinates."
                500 -> "Server error. Please try again later."
                else -> "Unexpected error occurred (HTTP ${e.code()})."
            }
            Exception(errorMessage)
        }
        else -> {
            Exception("An unexpected error occurred: ${e.localizedMessage}")
        }
    }
}


fun getGradientForWeather(weatherCondition: String): Brush {
    return when (weatherCondition) {
        "Sunny" -> Brush.verticalGradient(
            colors = listOf(Color(0xFFFFD194), Color(0xFFFFA26B), Color(0xFFF78888)) // Morning Sunrise
        )
        "Rainy" -> Brush.verticalGradient(
            colors = listOf(Color(0xFFBCC6CC), Color(0xFF8AA1B1), Color(0xFF627D98)) // Rainy Day
        )
        "Cloudy" -> Brush.verticalGradient(
            colors = listOf(Color(0xFFEDE7F6), Color(0xFFC8A2C8), Color(0xFF967BB6)) // Dusk Sky
        )
        "Cold" -> Brush.verticalGradient(
            colors = listOf(Color(0xFFE0F7FA), Color(0xFFB2EBF2), Color(0xFF80DEEA)) // Frosty Winter
        )
        else -> Brush.verticalGradient(
            colors = listOf(Color(0xFF4A90E2), Color(0xFF56CCF2), Color(0xFFB993D6)) // Default Gradient
        )
    }
}

fun String.toHumanReadableDate(): String {
    return try {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(this)

        // Format as "Fri, 22" or similar
        SimpleDateFormat("EEE, dd", Locale.getDefault()).format(date ?: Date())
    } catch (e: Exception) {
        "Invalid Date" // Fallback in case of parsing issues
    }
}

fun String.toFormattedTime(): String {
    // Define the input date-time format
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    // Parse the string to a LocalDateTime object
    val dateTime = LocalDateTime.parse(this, inputFormatter)
    // Define the output time format
    val outputFormatter = DateTimeFormatter.ofPattern("h a", Locale.getDefault())
    // Format the LocalDateTime to the desired time string
    return dateTime.format(outputFormatter)
}