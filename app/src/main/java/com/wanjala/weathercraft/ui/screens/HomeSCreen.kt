package com.wanjala.weathercraft.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.wanjala.weathercraft.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController) {
    val forecast = listOf("Day 1", "Day 2", "Day 3", "Day 4", "Day 5") // Mock data

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Current Weather")
        LazyColumn {

            items(forecast) { day ->
                Button(onClick = { }) {
                    Text(day)
                }
            }
        }
    }
}