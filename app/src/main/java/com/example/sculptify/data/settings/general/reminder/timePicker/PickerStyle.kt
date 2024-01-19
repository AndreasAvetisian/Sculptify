package com.example.sculptify.data.settings.general.reminder.timePicker

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class PickerStyle(
    val lineColor: Color = Color(0xff0060FE),
    val lineLength: Float = 25f,
    val textSize: TextUnit = 36.sp,
    val textColor: Color = Color.White
)