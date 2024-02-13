package com.example.sculptify.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sculptify.layout.general.customText.CustomText

@Composable
fun MyHistoryView() {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        CustomText(text = "My History")
    }
}