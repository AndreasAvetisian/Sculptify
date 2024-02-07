package com.example.sculptify.layout.mv.workouts

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun MV_RenderWorkouts(
    workouts: List<Map.Entry<String, Any>>,
    difficultyColor: Color,
    titleColor: Color,
    padding: Dp
) {
    workouts.forEach { (workoutName, workoutDetails) ->
        if (workoutDetails is Map<*, *>) {
            MV_Workout(
                title = workoutName,
                titleColor = titleColor,
                workoutsList = workoutDetails as Map<String, Any>,
                padding = padding,
                difficultyColor = difficultyColor,
            )
        }
    }
}