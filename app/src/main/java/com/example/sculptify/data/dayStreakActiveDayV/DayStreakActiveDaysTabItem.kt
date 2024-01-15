package com.example.sculptify.data.dayStreakActiveDayV

import androidx.compose.ui.graphics.Color

data class DayStreakActiveDaysTabItem (
    val title: String,
    val defaultDescription: String,
    val unselectedItem: Color = Color(0xff909090),
    val selectedItem: Color = Color(0xffFCFCFC),
    val notActivatedButtonText: String = "Start now",
    val activatedButtonText: String = "Finish more today",
    val isActivatedButton: Boolean = false
)