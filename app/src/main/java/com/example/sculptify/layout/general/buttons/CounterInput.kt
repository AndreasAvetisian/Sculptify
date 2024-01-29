package com.example.sculptify.layout.general.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.counterButton.CounterButton
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun CounterInput(
    title: String,
    titleFontSize: TextUnit,
    value: String,
    onValueIncreaseClick: () -> Unit,
    onValueDecreaseClick: () -> Unit,
    paddingBottom: Dp,
    paddingTop: Dp,
    buttonWidth: Dp,
    circleSize: Dp,
    thumbColor: Color
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = paddingBottom, top = paddingTop),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            fontSize = titleFontSize,
            fontFamily = balooFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        CounterButton(
            value = value,
            onValueIncreaseClick = {
                onValueIncreaseClick()
            },
            onValueDecreaseClick = {
                onValueDecreaseClick()
            },
            width = buttonWidth,
            height = 40.dp,
            circleSize = circleSize,
            fontSize = 24.sp,
            thumbColor = thumbColor
        )
    }
}