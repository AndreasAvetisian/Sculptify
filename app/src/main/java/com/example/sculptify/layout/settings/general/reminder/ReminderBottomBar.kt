package com.example.sculptify.layout.settings.general.reminder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Light_Gray
import com.example.sculptify.ui.theme.White

@Composable
fun ReminderBottomBar(
    onAddClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.675.dp, 0.dp, 15.675.dp, 15.675.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(
            thickness = 2.dp,
            color = Light_Gray,
            modifier = Modifier
                .padding(bottom = 15.675.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.675.dp)
        ) {
            ConfirmButton(
                text = "Add reminder",
                bgColor = Blue,
                textColor = White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                onClick = {
                    onAddClick()
                }
            )
        }
    }
}