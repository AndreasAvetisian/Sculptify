package com.example.sculptify.data.statistics

import androidx.compose.ui.graphics.Color

data class StatSelectionItem (
    val title: String,
    val unselectedItem: Color = Color(0xff909090),
    val selectedItem: Color = Color.White,
    val unselectedIndicator: Color = Color.Transparent,
    val selectedIndicator: Color = Color(0xff0060FE)
)