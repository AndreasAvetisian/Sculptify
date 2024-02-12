package com.example.sculptify.layout.wdv

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sculptify.ui.theme.Divider_Gray

@Composable
fun WDV_Divider() {
    Divider(
        modifier = Modifier
            .fillMaxWidth(),
        thickness = 1.dp,
        color = Divider_Gray
    )
}