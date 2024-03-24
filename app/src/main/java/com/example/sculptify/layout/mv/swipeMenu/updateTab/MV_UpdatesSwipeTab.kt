package com.example.sculptify.layout.mv.swipeMenu.updateTab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.mv.swipeMenu.MV_SwipeMenuTabItems

@Composable
fun MV_UpdatesSwipeTab() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(text = MV_SwipeMenuTabItems[1].description)
    }
}