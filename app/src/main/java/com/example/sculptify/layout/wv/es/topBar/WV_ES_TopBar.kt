package com.example.sculptify.layout.wv.es.topBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sculptify.layout.wv.layout.WV_CircleButton

@Composable
fun WV_ES_TopBar(
    exerciseAmount: Int,
    exercisesCompleted: Int,
    isCountdownActive: Boolean,
    isExerciseListOpen: Boolean,
    onExerciseListOpen: () -> Unit,
    isCancelMenuOpen: Boolean,
    onCancelMenuClick: () -> Unit,
    exerciseIndex: Int,
    stopwatch: String
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
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    WV_CircleButton(
                        imageVector = Icons.Rounded.Close,
                        isButtonClicked = isCancelMenuOpen,
                        onClick = {
                            onCancelMenuClick()
                        }
                    )
                    WV_CircleButton(
                        imageVector = Icons.Rounded.List,
                        isButtonClicked = isExerciseListOpen,
                        onClick = {
                            onExerciseListOpen()
                        }
                    )
                }
            }
            WV_ES_TB_StopwatchAndInfo(
                exerciseIndex = exerciseIndex,
                exerciseAmount = exerciseAmount,
                stopwatch = stopwatch
            )
        }
    }
}