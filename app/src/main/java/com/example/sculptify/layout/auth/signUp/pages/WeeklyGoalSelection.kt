package com.example.sculptify.layout.auth.signUp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.pages.auth.regWeeklyGoal
import com.example.sculptify.layout.general.counterButton.CounterButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.ADV_Blue
import com.example.sculptify.ui.theme.ADV_Orange
import com.example.sculptify.ui.theme.ADV_Red
import com.example.sculptify.ui.theme.Dark_Gray

@Composable
fun WeeklyGoalSelection() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(
            text = "How often would you like to work out?",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
        CounterButton(
            value = regWeeklyGoal.toString(),
            onValueIncreaseClick = {
                if (regWeeklyGoal < 7) {
                    regWeeklyGoal++
                }
            },
            onValueDecreaseClick = {
                if (regWeeklyGoal > 1) {
                    regWeeklyGoal--
                }
            },
            width = 300.dp,
            height = 80.dp,
            circleSize = 80.dp,
            fontSize = 50.sp,
            thumbColor = when (regWeeklyGoal) {
                1 -> ADV_Blue
                2 -> ADV_Blue
                3 -> ADV_Orange
                4 -> ADV_Orange
                else -> ADV_Red
            },
            containerColor = Dark_Gray,
            dragHorizontalLimit = 120,
            iconSize = 50.dp
        )
        CustomText(
            text = if (regWeeklyGoal == 1) "time / week" else "times / week",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}