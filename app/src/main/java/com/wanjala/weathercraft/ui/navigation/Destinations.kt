package com.wanjala.weathercraft.ui.navigation

sealed class Screen(val route: String) {
    data object CitySearch : Screen("city_search")
    data object Home : Screen("home")
}