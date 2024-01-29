package com.example.sculptify.layout.settings.general

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sculptify.R
import com.example.sculptify.layout.general.customText.CustomText

@Composable
fun GS_ReminderButton(
    onClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xff1C1C1E))
            .height(56.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.675.dp, 10.dp, 17.675.dp, 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomText(text = "Reminder")
            Icon(
                modifier = Modifier
                    .scale(scaleX = -1f, scaleY = 1f)
                    .padding(0.dp, 3.dp, 0.dp, 0.dp),
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "arrow",
                tint = Color.White
            )
        }
    }
}