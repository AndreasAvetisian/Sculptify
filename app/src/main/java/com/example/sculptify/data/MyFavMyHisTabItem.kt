package com.example.sculptify.data

import androidx.compose.ui.graphics.Color

data class MyFavMyHisTabItem (
    val title: String,
    val unselectedItem: Color = Color(0xff909090),
    val selectedItem: Color = Color(0xffFCFCFC)
)