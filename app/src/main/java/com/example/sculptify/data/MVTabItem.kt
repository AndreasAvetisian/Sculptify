package com.example.sculptify.data

import androidx.compose.ui.graphics.Color

data class MVTabItem (
    val title: String,
    val description: String,
    val data: List<DataItem>,
    val unselectedItem: Color = Color(0xff909090),
    val selectedItem: Color = Color(0xffFCFCFC)
)

data class DataItem(
    val iconId: Int,
    val dataTitle: String,
    val dataDate: String
)