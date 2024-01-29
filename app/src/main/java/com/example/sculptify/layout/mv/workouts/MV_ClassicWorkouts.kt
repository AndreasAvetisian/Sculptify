package com.example.sculptify.layout.mv.workouts

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.sculptify.layout.general.customText.CustomText

@Composable
fun MV_ClassicWorkouts() {
    Column {
        CustomText(text = "Classic Workouts")
        MV_Workout(padding = 10.dp)
        MV_Workout(padding = 25.dp)
        MV_Workout(padding = 25.dp)
    }
}