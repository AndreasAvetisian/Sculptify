package com.example.sculptify.layout.mv.swipeMenu

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.sculptify.ui.theme.White

@Composable
fun MV_MyPlanSwipeTab() {
    Text(text = MV_SwipeMenuTabItems[1].title, color = White)
}