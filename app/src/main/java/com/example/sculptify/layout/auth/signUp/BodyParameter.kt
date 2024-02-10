package com.example.sculptify.layout.auth.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.counterButton.CounterButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Dark_Gray

@Composable
fun BodyParameter(
    text: String,
    value: String,
    onValueIncreaseClick: () -> Unit,
    onValueDecreaseClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomText(text = text)
        CounterButton(
            value = value,
            onValueIncreaseClick = onValueIncreaseClick,
            onValueDecreaseClick = onValueDecreaseClick,
            width = 240.dp,
            height = 40.dp,
            circleSize = 110.dp,
            fontSize = 24.sp,
            thumbColor = Blue,
            containerColor = Dark_Gray
        )
    }
}