package com.example.sculptify.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun MyBMIView() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Red)
    ) {
        Text(
            text = "BMI",
            color = Color.White,
            fontSize = 20.sp,
            fontFamily = balooFontFamily,
            fontWeight = FontWeight.Bold
        )
    }
}