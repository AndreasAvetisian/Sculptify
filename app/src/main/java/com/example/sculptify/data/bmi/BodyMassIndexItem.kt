package com.example.sculptify.data.bmi

import androidx.compose.ui.graphics.Color

data class BodyMassIndexItem (
    val index: Float,
    val indicatorColor: Color,
    val description: String,
    val width: Float,
    val range: ClosedFloatingPointRange<Float>
)