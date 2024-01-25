package com.example.sculptify.data.statistics

import androidx.compose.ui.graphics.Color

data class MyStatisticsTabItem (
    val title: String,
    val unselectedItem: Color = Color(0xff909090),
    val selectedItem: Color = Color(0xffFCFCFC),
    val isActivatedButton: Boolean = false
)