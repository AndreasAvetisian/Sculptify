package com.example.sculptify.layout.MP

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
fun MP_ModifyGenderButton(
    text: String,
    onClick: () -> Unit,
    selected: Boolean
) {
    val buttonColor = if (selected) Color(0xff0000ff) else Color(0xff0060FE).copy(alpha = 0.2f)

    Card (
        colors = CardDefaults.cardColors(buttonColor),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .width(90.dp)
            .height(30.dp)
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
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}