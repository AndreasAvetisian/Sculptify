package com.example.sculptify.layout.general.buttons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmOpenableLineButton(
    bgColor: Color,
    onClick: () -> Unit
) {
    ConfirmButton(
        text = "Save",
        textColor = Color.White,
        bgColor = bgColor,
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(vertical = 10.dp)
            .height(30.dp)
    )
}