package com.example.sculptify.layout.wv.es.topBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.Workout_Timer_Gray

@Composable
fun WV_ES_TB_StopwatchAndInfo(
    exerciseIndex: Int,
    exerciseAmount: Int,
    stopwatch: String
) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        CustomText(
            text = "Exercise ${exerciseIndex + 1}/$exerciseAmount",
            color = Black
        )
        CustomText(
            text = stopwatch,
            color = Workout_Timer_Gray
        )
    }
}