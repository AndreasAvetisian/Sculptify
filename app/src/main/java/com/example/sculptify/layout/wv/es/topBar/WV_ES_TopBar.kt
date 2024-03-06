package com.example.sculptify.layout.wv.es.topBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WV_ES_TopBar(
    exerciseAmount: Int,
    exercisesCompleted: Int,
    isCountdownActive: Boolean,
    isCancelMenuOpen: Boolean,
    onCancelMenuClick: () -> Unit,
    exerciseIndex: Int,
    stopwatch: String,
    exerciseTitle: String,
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.675.dp, 15.675.dp, 15.675.dp, 0.dp)
    ) {
        WV_ES_TB_CompleteExerciseIndicator(
            exerciseAmount = exerciseAmount,
            exercisesCompleted = exercisesCompleted
        )
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (isCountdownActive) Arrangement.End else Arrangement.SpaceBetween
        ) {
            if (!isCountdownActive) {
                WV_ES_TB_CancelButton(
                    isCancelMenuOpen = isCancelMenuOpen,
                    onCancelMenuClick = {
                        onCancelMenuClick()
                    }
                )
            }
            WV_ES_TB_StopwatchAndInfo(
                exerciseIndex = exerciseIndex,
                exerciseAmount = exerciseAmount,
                stopwatch = stopwatch,
                exerciseTitle = exerciseTitle
            )
        }
    }
}