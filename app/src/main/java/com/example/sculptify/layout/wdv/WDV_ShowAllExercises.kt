package com.example.sculptify.layout.wdv

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText

@Composable
fun WDV_ShowAllExercises(
    onClick: () -> Unit,
    color: Color
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CustomText(
            text = "View all exercises",
            color = color,
            fontSize = 18.sp
        )
        Icon(
            Icons.Rounded.KeyboardArrowDown,
            contentDescription = "",
            tint = color
        )
    }
}