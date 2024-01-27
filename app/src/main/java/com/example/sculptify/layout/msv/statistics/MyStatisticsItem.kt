package com.example.sculptify.layout.msv.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
fun MyStatisticsItem(
    title: String,
    value: String
) {
    Card (
        colors = CardDefaults.cardColors(Color(0xff1C1C1E)),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.675.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = value,
                color = Color(0xff909090),
                fontSize = 20.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold
            )
        }
    }
}