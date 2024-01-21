package com.example.sculptify.layout.settings.general.reminder

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
fun RemindersDaysOfWeek(
    text: String
) {
    Card (
        colors = CardDefaults.cardColors(Color(0xFF2020CF)),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .size(
                width = if (text == "Every day") 90.dp else 40.dp,
                height = 30.dp
            )
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = if (text == "Every day") text else text.take(3),
                fontSize = 14.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}