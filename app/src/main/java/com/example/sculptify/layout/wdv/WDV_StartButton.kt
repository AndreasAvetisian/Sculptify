package com.example.sculptify.layout.wdv

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.ui.theme.White

@Composable
fun WDV_StartButton(
    onClick: () -> Unit,
    bgColor: Color
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.675.dp, vertical = 10.dp)
    ) {
        ConfirmButton(
            text = "Start",
            bgColor = bgColor,
            textColor = White,
            modifier = Modifier
                .fillMaxWidth(),
            onClick = {
                onClick()
            }
        )
    }
}