package com.wanjala.weathercraft.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material 3 typography styles to start with
val Typography = Typography(
    // For your BIG temperature text (like "16°")
    displayLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        fontSize = 120.sp,    // Adjust as needed
        lineHeight = 72.sp,
        letterSpacing = 0.sp
    ),
    // For mid-level titles (e.g. “Mostly Cloudy”, section headers)
    titleMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    // Keep your existing bodyLarge override
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    // For smaller body text (e.g. minor descriptions, in-card text)
    bodyMedium = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    // For tiny labels (e.g. sub-labels, captions)
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

// Add extension properties to your M3 Typography for convenience
val Typography.h1: TextStyle
    get() = displayLarge

val Typography.subtitle1: TextStyle
    get() = titleMedium

val Typography.body1: TextStyle
    get() = bodyLarge

val Typography.caption: TextStyle
    get() = labelSmall

