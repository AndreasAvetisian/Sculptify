package com.example.sculptify.data.mv

import androidx.compose.ui.graphics.Color

data class MVSwipeMenuTabItem (
    val title: String,
    val description: String,
    val swipeMenuContent: List<MVSwipeMenuContentItem>,
    val unselectedItem: Color = Color(0xff909090),
    val selectedItem: Color = Color(0xffFCFCFC)
)