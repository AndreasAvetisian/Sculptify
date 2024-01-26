package com.example.sculptify.pages

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
import com.example.sculptify.layout.sv.statistics.MyStatisticsItem
import com.example.sculptify.layout.sv.statistics.StatSelection
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun MyStatisticsView() {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val userData by userVM.userdata.collectAsState()

    var selectedButton by remember { mutableStateOf(StatSelectionButton.Today) }

    val exercisesCompleted: String = when (selectedButton) {
        StatSelectionButton.Today -> (userData["userWorkoutStat"] as? List<Map<String, Any>>)
            ?.lastOrNull { it["date"] != null }
            ?.get("exercisesCompleted")
            ?.toString() ?: "0"
        StatSelectionButton.AllTime -> {
            val allTimeDuration = (userData["userWorkoutStat"] as? List<Map<String, Any>>)
                ?.mapNotNull { it["exercisesCompleted"] }
                ?.sumOf { it.toString().toInt() }
            allTimeDuration.toString()
        }
    }

    val durationValue: String = when (selectedButton) {
        StatSelectionButton.Today -> (userData["userWorkoutStat"] as? List<Map<String, Any>>)
            ?.lastOrNull { it["date"] != null }
            ?.get("duration")
            ?.toString() ?: "0"
        StatSelectionButton.AllTime -> {
            val allTimeDuration = (userData["userWorkoutStat"] as? List<Map<String, Any>>)
                ?.mapNotNull { it["duration"] }
                ?.sumOf { it.toString().toInt() }
            allTimeDuration.toString()
        }
    }

    val caloriesBurned: String = when (selectedButton) {
        StatSelectionButton.Today -> (userData["userWorkoutStat"] as? List<Map<String, Any>>)
            ?.lastOrNull { it["date"] != null }
            ?.get("caloriesBurned")
            ?.toString() ?: "0"
        StatSelectionButton.AllTime -> {
            val allTimeDuration = (userData["userWorkoutStat"] as? List<Map<String, Any>>)
                ?.mapNotNull { it["caloriesBurned"] }
                ?.sumOf { it.toString().toInt() }
            allTimeDuration.toString()
        }
    }

    val pbsValue by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value["pbs"].toString()
    )

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StatSelection(selectedButton = selectedButton) { button ->
            selectedButton = button
        }
        MyStatisticsItem(
            title = "Exercises Completed",
            value = exercisesCompleted
        )
        MyStatisticsItem(
            title = "Duration",
            value = "$durationValue min"
        )
        MyStatisticsItem(
            title = "Calories Burned",
            value = "$caloriesBurned kcal"
        )
        if (selectedButton == StatSelectionButton.AllTime) {
            MyStatisticsItem(
                title = "Personal Best Streak",
                value = "$pbsValue ${if (pbsValue != "1") "days" else "day"}"
            )
        }
    }
}