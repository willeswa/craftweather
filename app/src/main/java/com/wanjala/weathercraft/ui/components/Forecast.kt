package com.wanjala.weathercraft.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wanjala.weathercraft.CitySearchUiState
import com.wanjala.weathercraft.MainViewModel

import com.wanjala.weathercraft.data.models.DailyForecastUIModel
import com.wanjala.weathercraft.utils.toFormattedTime
import com.wanjala.weathercraft.utils.toHumanReadableDate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DailyForecastSection(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val uiState by viewModel.forecastUiState.collectAsState()
    val forecastData by viewModel.forecastData.collectAsState()
    val selectedCity by viewModel.selectedCity.collectAsState()

    LaunchedEffect(selectedCity) {
        selectedCity?.let { city ->
            viewModel.triggerForecastFetch(city)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White.copy(alpha = 0.1f),
                shape = RoundedCornerShape(24.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Daily Forecast",
            style = MaterialTheme.typography.titleMedium.copy(color = Color.White),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when (uiState) {
            is CitySearchUiState.FetchingWeather -> LoadingState()
            is CitySearchUiState.Error -> {
                val errorMessage = (uiState as CitySearchUiState.Error).message
                ErrorState(message = errorMessage)
            }
            else -> {
                forecastData?.groupBy { it.date.substring(0, 10) }?.let { groupedForecasts ->
                    if (groupedForecasts.isNotEmpty()) {
                        groupedForecasts.forEach { (date, forecasts) ->
                            // Group Title
                            Text(
                                text = date.toHumanReadableDate(),
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                ),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )

                            forecasts.forEachIndexed { index, forecast ->
                                DailyForecastItem(forecast)

                                if (index < forecasts.lastIndex) {
                                    Divider(
                                        color = Color.White.copy(alpha = 0.2f),
                                        thickness = 1.dp,
                                        modifier = Modifier.padding(vertical = 8.dp)
                                    )
                                }
                            }
                        }
                    } else {
                        NoDataState()
                    }
                } ?: NoDataState()
            }
        }
    }
}

@Composable
fun DailyForecastItem(forecast: DailyForecastUIModel) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Weather Icon
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary.copy(alpha = 0.3f),
                                Color.Transparent
                            )
                        ),
                        shape = RoundedCornerShape(32.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = "https://openweathermap.org/img/wn/${forecast.icon}@2x.png",
                    contentDescription = "Weather Icon",
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Day and Temp
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = "${forecast.tempMax}° / ${forecast.tempMin}°",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = forecast.description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Date
            Text(
                text = forecast.date.toFormattedTime(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.onSurface
                ),
                textAlign = TextAlign.End
            )
        }
    }
}
// Extension function to convert date to human-readable format
