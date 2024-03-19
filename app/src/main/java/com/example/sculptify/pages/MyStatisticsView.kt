package com.example.sculptify.pages

import android.annotation.SuppressLint
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
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun MyStatisticsView() {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val userData by userVM.userdata.collectAsState()

    var selectedButton by remember { mutableStateOf(StatSelectionButton.Today) }

    val pbsValue by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value["pbs"].toString()
    )

    val currentDate = remember { SimpleDateFormat("MMM d").format(Date()) }

    val historyOfWorkouts = userData["historyOfWorkouts"] as? List<Map<String, Any>> ?: emptyList()

    val filteredWorkouts = remember(selectedButton) {
        when (selectedButton) {
            StatSelectionButton.Today -> {
                historyOfWorkouts.filter { workout ->
                    val workoutDate = workout["date"] as? String
                    workoutDate?.contains(currentDate) ?: false
                }
            }
            StatSelectionButton.AllTime -> {
                historyOfWorkouts
            }
        }
    }

    val totalDurationInSec = filteredWorkouts.sumOf { it["finalDuration"] as Long }
    val totalDurationInMin = totalDurationInSec / 60
    val totalCalories = "%.1f".format(filteredWorkouts.sumOf { it["caloriesBurned"] as Double })
    val workoutsCompleted = filteredWorkouts.size

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StatSelection(selectedButton = selectedButton) { button ->
            selectedButton = button
        }
        MyStatisticsItem(
            title = "Workouts Completed",
            value = if (selectedButton == StatSelectionButton.Today) workoutsCompleted.toString() else historyOfWorkouts.size.toString()
        )
        MyStatisticsItem(
            title = "Duration",
            value = "$totalDurationInMin min"
        )
        MyStatisticsItem(
            title = "Calories Burned",
            value = "$totalCalories kcal"
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
