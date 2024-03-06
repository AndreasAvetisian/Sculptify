package com.example.sculptify.layout.wv.es.topBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.Workout_Timer_Gray
import kotlinx.coroutines.delay

@Composable
fun WV_ES_TB_StopwatchAndInfo(
    exerciseIndex: Int,
    exerciseAmount: Int,
    stopwatch: String,
    exerciseTitle: String,
) {
    var changeText by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(3000)
        changeText = true
    }

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End
    ) {
        CustomText(
            text = if (changeText) "Exercise ${exerciseIndex + 1}/$exerciseAmount" else exerciseTitle,
            color = Black
        )
        CustomText(
            text = if (changeText) stopwatch else "Next",
            color = Workout_Timer_Gray
        )
    }
}