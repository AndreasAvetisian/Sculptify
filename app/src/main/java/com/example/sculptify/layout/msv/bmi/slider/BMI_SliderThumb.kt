package com.example.sculptify.layout.msv.bmi.slider

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun BMI_SliderThumb(
    index: Float
) {
    Icon(
        painter = painterResource(id = R.drawable.bmi_thumb),
        contentDescription = "",
        tint = Color(0xff58606B),
        modifier = Modifier
            .width(50.dp)
            .padding(bottom = 60.dp)
    )
    Text(
        text = "%.1f".format(index),
        color = Color.White,
        fontSize = 18.sp,
        fontFamily = balooFontFamily,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.width(50.dp),
        textAlign = TextAlign.Center
    )
}