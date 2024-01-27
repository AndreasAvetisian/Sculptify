package com.example.sculptify.layout.msv.bmi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
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
fun BMI_EditRow() {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "BMI",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = balooFontFamily,
            fontWeight = FontWeight.Bold
        )
        Icon(
            Icons.Rounded.Edit,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier.size(20.dp)
        )

    }
}