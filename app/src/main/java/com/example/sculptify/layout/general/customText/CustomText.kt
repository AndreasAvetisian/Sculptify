package com.example.sculptify.layout.general.customText

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun CustomText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.White,
    fontSize: TextUnit = 20.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        color = color,
        fontSize = fontSize,
        fontFamily = balooFontFamily,
        fontWeight = fontWeight,
        modifier = modifier,
        textAlign = textAlign
    )
}