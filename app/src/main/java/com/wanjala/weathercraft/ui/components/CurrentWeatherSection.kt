package com.wanjala.weathercraft.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wanjala.weathercraft.CitySearchUiState
import com.wanjala.weathercraft.MainViewModel
import com.wanjala.weathercraft.data.models.CurrentWeather
import com.wanjala.weathercraft.data.models.ExtraWeatherInfo
import com.wanjala.weathercraft.ui.theme.body1
import com.wanjala.weathercraft.ui.theme.caption
import com.wanjala.weathercraft.ui.theme.h1
import com.wanjala.weathercraft.ui.theme.subtitle1

@Composable
fun CurrentWeatherSection(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val uiState by viewModel.weatherUiState.collectAsState()
    val currentWeather by viewModel.currentWeather.collectAsState()
    val selectedCity by viewModel.selectedCity.collectAsState()

    LaunchedEffect(selectedCity) {
        selectedCity?.let { city ->
            viewModel.triggerWeatherFetch(city)
        }
    }

    when (uiState) {
        is CitySearchUiState.FetchingWeather -> LoadingState(modifier)
        is CitySearchUiState.Error -> {
            val errorMessage = (uiState as CitySearchUiState.Error).message
            ErrorState(modifier = modifier, message = errorMessage)
        }
        else -> {
            currentWeather?.let { weather ->
                WeatherContent(
                    modifier = modifier,
                    currentTemp = weather.temperature,
                    maxTemp = weather.maxTemperature,
                    minTemp = weather.minTemperature,
                    description = weather.description,
                    extraInfoItems = weather.extraInfo
                )
            } ?: NoDataState(modifier)
        }
    }
}




@Composable
fun WeatherContent(
    modifier: Modifier = Modifier,
    currentTemp: Int,
    maxTemp: Int,
    minTemp: Int,
    description: String,
    extraInfoItems: List<ExtraWeatherInfo>
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Big temperature in white
        Text(
            text = "$currentTemp°",
            style = MaterialTheme.typography.h1.copy(
                fontWeight = FontWeight.Bold,
                color = Color.White
            ),
            modifier = Modifier.padding(top = 16.dp)
        )

        // Weather description
        Text(
            text = description,
            style = MaterialTheme.typography.subtitle1.copy(color = Color.White.copy(alpha = 0.8f))
        )

        Spacer(modifier = Modifier.height(16.dp))
        // Box for max & min temperatures
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White.copy(alpha = 0.15f),
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Max Temp
                TemperatureRow(temp = maxTemp, icon = Icons.Filled.ArrowUpward, description = "Max")
                // Min Temp
                TemperatureRow(temp = minTemp, icon = Icons.Filled.ArrowDownward, description = "Min")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        // Extra Info Row
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White.copy(alpha = 0.15f),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 8.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                extraInfoItems.forEach { info ->
                    WeatherExtraInfoItem(info)
                }
            }
        }
    }
}

@Composable
fun TemperatureRow(temp: Int, icon: ImageVector, description: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = description,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "$temp°",
            style = MaterialTheme.typography.body1.copy(color = Color.White)
        )
    }
}

