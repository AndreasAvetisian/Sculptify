package com.example.sculptify.layout.msv.bmi.slider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText

@Composable
fun BMI_SliderItem(
    width: Float,
    color: Color,
    index: Float
) {
    Column (
        modifier = Modifier
            .fillMaxWidth(width)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Card (
            colors = CardDefaults.cardColors(color),
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
        ) {
            Spacer(modifier = Modifier.fillMaxSize())
        }
        CustomText(
            text = "%.${if (index == 18.5f) "1" else "0"}f".format(index),
            fontSize = 10.sp
        )
    }
}