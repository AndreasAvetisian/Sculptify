package com.example.sculptify.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun RegConfirmButton(
    text: String,
    bgColor: Color,
    modifier: Modifier
) {
    Card (
        colors = CardDefaults.cardColors(bgColor),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = modifier
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}