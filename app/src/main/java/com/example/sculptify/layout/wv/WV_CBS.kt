package com.example.sculptify.layout.wv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.White
import java.util.Locale

@Composable
fun WV_CBS( // Countdown Before Start
    count: String,
    list: List<MutableMap<String, String>>,
    onStartClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Dark_Gray.copy(0.8f)),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomText(
                text = "READY TO G0",
                fontSize = 50.sp
            )
            CustomText(
                text = count,
                fontSize = 150.sp
            )
            CustomText(
                text = "Exercise 1/${list.size}",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            CustomText(
                text = list[0]["title"].toString().uppercase(Locale.ROOT),
                fontSize = 24.sp
            )
        }
        ConfirmButton(
            text = "Start!",
            bgColor = White,
            textColor = Black,
            fontSize = 26.sp,
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp),
            onClick = {
                onStartClick()
            }
        )
    }
}