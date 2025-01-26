package com.wanjala.weathercraft.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    val latitude: Double = 0.0,
    val longitude: Double = 0.0
)

@Serializable
data class City(
    val coordinates: Coordinates = Coordinates(),
    val name: String = "",
    val country: String = "Unknown",
    val placeId: String = "", // Useful for integration with external APIs
    val timezone: String = "UTC", // Default to UTC
    val utcOffset: Int = 0 // Timezone offset in minutes
)
