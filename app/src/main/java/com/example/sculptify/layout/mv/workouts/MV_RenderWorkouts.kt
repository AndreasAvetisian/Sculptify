package com.example.sculptify.layout.mv.workouts

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun MV_RenderWorkouts(
    workouts: List<Map.Entry<String, Any>>,
    bgColor: Color,
    padding: Dp
) {
    workouts.forEach { (workoutName, workoutDetails) ->
        if (workoutDetails is Map<*, *>) {
            MV_Workout(
                title = workoutName,
                workoutsList = workoutDetails as Map<String, Any>,
                bgColor = bgColor,
                padding = padding
            )
        }
    }
}