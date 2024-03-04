package com.example.sculptify.layout.wv.es

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.sculptify.layout.wv.es.topBar.WV_ES_TopBar
import com.example.sculptify.pages.formatTime

@Composable
fun WV_ExerciseScreen(
    exerciseIndex: Int,
    exerciseAmount: Int,
    exercisesCompleted: Int,
    isCountdownActive: Boolean,
    isCancelMenuOpen: Boolean,
    onCancelMenuClick: () -> Unit,
    elapsedSeconds: Long,
    exerciseTitle: String,
    exerciseValue: String,
    onCompleteClick: () -> Unit,
    onExerciseDescriptionClick: () -> Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        WV_ES_TopBar(
            exerciseAmount = exerciseAmount,
            exercisesCompleted = exercisesCompleted,
            isCountdownActive = isCountdownActive,
            isCancelMenuOpen = isCancelMenuOpen,
            onCancelMenuClick = {
                onCancelMenuClick()
            },
            exerciseIndex = exerciseIndex,
            stopwatch = formatTime(elapsedSeconds)
        )
        if (!isCountdownActive && !isCancelMenuOpen) {
            WV_ES_ExercisePic()
            WV_ES_BottomSheet(
                onExerciseDescriptionClick = {
                    onExerciseDescriptionClick()
                },
                exerciseTitle = exerciseTitle,
                exerciseValue = exerciseValue,
                onCompleteClick = {
                    onCompleteClick()
                }
            )
        }
    }
}