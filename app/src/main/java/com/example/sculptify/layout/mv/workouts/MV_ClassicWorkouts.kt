package com.example.sculptify.layout.mv.workouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Dark_Orange
import com.example.sculptify.ui.theme.Light_Orange
import com.example.sculptify.viewModels.WorkoutsViewModel

@Composable
fun MV_ClassicWorkouts() {
    val workoutsVM: WorkoutsViewModel = viewModel()

    LaunchedEffect(true) {
        workoutsVM.getWorkoutsData()
    }

    val workoutsData by rememberUpdatedState(
        newValue = workoutsVM.workoutsList.collectAsState().value
    )

    Column (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        CustomText(text = "Classic Workouts")

        val beginnerWorkouts = workoutsData.entries.filter { it.key == "Beginner" }
        val intermediateWorkouts = workoutsData.entries.filter { it.key == "Intermediate" }
        val advancedWorkouts = workoutsData.entries.filter { it.key == "Advanced" }

        MV_RenderWorkouts(
            workouts = beginnerWorkouts,
            bgColor = Blue,
            padding = 10.dp
        )
        MV_RenderWorkouts(
            workouts = intermediateWorkouts,
            bgColor = Light_Orange,
            padding = 25.dp
        )
        MV_RenderWorkouts(
            workouts = advancedWorkouts,
            bgColor = Dark_Orange,
            padding = 25.dp
        )
    }
}