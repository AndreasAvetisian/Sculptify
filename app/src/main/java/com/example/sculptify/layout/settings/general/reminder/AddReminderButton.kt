package com.example.sculptify.layout.settings.general.reminder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sculptify.layout.general.buttons.ConfirmButton

@Composable
fun AddReminderButton(
    onClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(
            thickness = 2.dp,
            color = Color(0xff909090),
            modifier = Modifier
                .padding(bottom = 10.dp)
        )
        ConfirmButton(
            text = "Add",
            bgColor = Color(0xff0060FE),
            textColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = {
                onClick()
            }
        )
    }
}