package com.example.sculptify.layout.settings.general.reminder.rd

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun RD_DaysOfWeek(
    bgColor: Color,
    borderColor: Color,
    onClick: () -> Unit,
    text: String
) {
    Card (
        colors = CardDefaults.cardColors(bgColor),
        shape = MaterialTheme.shapes.extraLarge,
        border = BorderStroke(
            width = 2.dp,
            color = borderColor
        ),
        modifier = Modifier
            .size(40.dp)
            .clickable {
                onClick()
            }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                fontSize = 25.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White,
            )
        }
    }
}