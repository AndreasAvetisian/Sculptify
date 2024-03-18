package com.example.sculptify.pages

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.enumClasses.StatSelectionButton
import com.example.sculptify.layout.msv.statistics.MyStatisticsItem
import com.example.sculptify.layout.msv.statistics.StatSelection
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun MyStatisticsView() {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val userData by userVM.userdata.collectAsState()

    var selectedButton by remember { mutableStateOf(StatSelectionButton.Today) }

//    val (workoutsCompleted, durationValue, caloriesBurned) = remember(userData, selectedButton) {
//        computeStatistics(userData, selectedButton)
//    }

    val pbsValue by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value["pbs"].toString()
    )

    val historyOfWorkouts = userData["historyOfWorkouts"] as? List<Map<String, Any>> ?: emptyList()

    Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAA", historyOfWorkouts.toString())

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StatSelection(selectedButton = selectedButton) { button ->
            selectedButton = button
        }
        MyStatisticsItem(
            title = "Workouts Completed",
            value = historyOfWorkouts.size.toString()
        )
        MyStatisticsItem(
            title = "Duration",
            value = "$ min"
        )
        MyStatisticsItem(
            title = "Calories Burned",
            value = "$ kcal"
        )
        AnimatedVisibility(
            visible = selectedButton == StatSelectionButton.AllTime,
            enter = fadeIn(
                initialAlpha = 0.4f
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 250)
            )
        ) {
            MyStatisticsItem(
                title = "Personal Best Streak",
                value = "$pbsValue ${if (pbsValue != "1") "days" else "day"}"
            )
        }
    }
}

//private fun computeStatistics(
//    userData: Map<String, Any>,
//    selectedButton: StatSelectionButton
//): Triple<String, String, String> {
//    val userWorkoutStat = userData["userWorkoutStat"] as? List<Map<String, Any>> ?: return Triple("0", "0", "0")
//
//    val todayStats = userWorkoutStat.lastOrNull { it["date"] != null }
//
//    val exercisesCompleted = when (selectedButton) {
//        StatSelectionButton.Today -> todayStats?.get("exercisesCompleted")?.toString() ?: "0"
//        StatSelectionButton.AllTime -> userWorkoutStat.sumOf {
//            it["exercisesCompleted"].toString().toInt()
//        }.toString()
//    }
//
//    val durationValue = when (selectedButton) {
//        StatSelectionButton.Today -> todayStats?.get("duration")?.toString() ?: "0"
//        StatSelectionButton.AllTime -> userWorkoutStat.sumOf {
//            it["duration"].toString().toInt()
//        }.toString()
//    }
//
//    val caloriesBurned = when (selectedButton) {
//        StatSelectionButton.Today -> todayStats?.get("caloriesBurned")?.toString() ?: "0"
//        StatSelectionButton.AllTime -> userWorkoutStat.sumOf {
//            it["caloriesBurned"].toString().toInt()
//        }.toString()
//    }
//
//    return Triple(exercisesCompleted, durationValue, caloriesBurned)
//}
