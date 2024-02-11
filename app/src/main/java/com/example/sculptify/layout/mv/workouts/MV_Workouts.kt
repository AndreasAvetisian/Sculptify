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
import androidx.navigation.NavHostController
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Advanced_Dark_Red
import com.example.sculptify.ui.theme.Advanced_Red
import com.example.sculptify.ui.theme.Beginner_Dark_Green
import com.example.sculptify.ui.theme.Beginner_Green
import com.example.sculptify.ui.theme.Intermediate_Dark_Orange
import com.example.sculptify.ui.theme.Intermediate_Orange
import com.example.sculptify.viewModels.WorkoutsViewModel

@Composable
fun MV_Workouts(navController: NavHostController) {
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
        CustomText(text = "Workouts")

        val beginnerWorkouts = workoutsData.entries.filter { it.key == "Beginner" }
        val intermediateWorkouts = workoutsData.entries.filter { it.key == "Intermediate" }
        val advancedWorkouts = workoutsData.entries.filter { it.key == "Advanced" }

        MV_RenderWorkouts(
            workouts = beginnerWorkouts,
            difficultyColor = Beginner_Green,
            titleColor = Beginner_Dark_Green,
            padding = 10.dp,
            navController = navController
        )
        MV_RenderWorkouts(
            workouts = intermediateWorkouts,
            difficultyColor = Intermediate_Orange,
            titleColor = Intermediate_Dark_Orange,
            padding = 25.dp,
            navController = navController
        )
        MV_RenderWorkouts(
            workouts = advancedWorkouts,
            difficultyColor = Advanced_Red,
            titleColor = Advanced_Dark_Red,
            padding = 25.dp,
            navController = navController
        )
    }
}