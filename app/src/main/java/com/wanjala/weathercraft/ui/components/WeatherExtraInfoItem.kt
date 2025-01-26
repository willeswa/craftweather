package com.wanjala.weathercraft.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wanjala.weathercraft.data.models.ExtraWeatherInfo

@Composable
fun WeatherExtraInfoItem(info: ExtraWeatherInfo) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(8.dp)
            .size(80.dp)
    ) {
        // Icon
        Icon(
            imageVector = info.icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Title (e.g., wind speed or rain chance)
        Text(
            text = info.title,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
                fontSize = 14.sp
            ),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(2.dp))

        // Subtitle (e.g., description of the info)
        Text(
            text = info.subtitle,
            style = MaterialTheme.typography.bodySmall.copy(
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 12.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}