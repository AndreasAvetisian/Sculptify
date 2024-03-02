package com.example.sculptify.layout.mbs

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sculptify.layout.dayStreakActiveDaysView.adv.ADV_ModifyWeeklyGoal
import com.example.sculptify.layout.msv.bmi.BMI_EditBodyParameters
import com.example.sculptify.layout.wdv.WDV_ExerciseInfo
import com.example.sculptify.layout.wv.WV_ExerciseInfo
import com.example.sculptify.pages.MeView
import com.example.sculptify.screens.Screen
import com.example.sculptify.ui.theme.Dark_Gray
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MBS(
    navController: NavHostController,
    sheetState: SheetState,
    scope: CoroutineScope,
    onDismiss: () -> Unit,
    data: Map<String, String> = emptyMap(),
    workoutLevel: String = "",
    workoutInstruction: String = "",
    exerciseFocusAreas: String = ""
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState,
        containerColor = Dark_Gray,
        windowInsets = WindowInsets(0)
    ) {
        when (currentRoute) {
            Screen.Main.route -> {
                MeView(
                    scope = scope,
                    sheetState = sheetState,
                    onDismiss = onDismiss,
                    navController = navController
                )
            }
            Screen.DSAD.route -> {
                ADV_ModifyWeeklyGoal(
                    scope = scope,
                    sheetState = sheetState,
                    onDismiss = onDismiss
                )
            }
            Screen.Statistics.route -> {
                BMI_EditBodyParameters(
                    scope = scope,
                    sheetState = sheetState,
                    onDismiss = onDismiss
                )
            }
            Screen.WorkoutDetails.route -> {
                WDV_ExerciseInfo(
                    scope = scope,
                    sheetState = sheetState,
                    onDismiss = onDismiss,
                    exercise = data,
                    workoutLevel = workoutLevel
                )
            }
            Screen.Workout.route -> {
                WV_ExerciseInfo(
                    scope = scope,
                    sheetState = sheetState,
                    onDismiss = onDismiss,
                    workoutInstruction = workoutInstruction,
                    exerciseFocusAreas = exerciseFocusAreas,
                )
            }
        }
    }
}