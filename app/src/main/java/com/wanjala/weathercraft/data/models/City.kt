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
    val placeId: String = "",
    val timezone: String = "UTC",
    val utcOffset: Int = 0
)
