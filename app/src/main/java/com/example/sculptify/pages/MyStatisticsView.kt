package com.example.sculptify.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.layout.sv.statistics.MyStatisticsItem
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun MyStatisticsView() {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val userData by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value
    )

//    val exercisesCompletedValue = userData["exercisesCompleted"].toString()
//    val durationValue = userData["duration"].toString()
//    val caloriesBurnedValue = userData["duration"].toString()
//    val pbsValue = userData["pbs"].toString()

    val exercisesCompletedValue by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value["exercisesCompleted"].toString()
    )
    val durationValue by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value["duration"].toString()
    )
    val caloriesBurnedValue by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value["caloriesBurned"].toString()
    )
    val pbsValue by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value["pbs"].toString()
    )

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        MyStatisticsItem(
            title = "Exercises completed",
            value = exercisesCompletedValue
        )
        MyStatisticsItem(
            title = "Duration",
            value = "$durationValue min"
        )
        MyStatisticsItem(
            title = "Calories burned",
            value = "$caloriesBurnedValue kcal"
        )
        MyStatisticsItem(
            title = "Personal Best Streak",
            value = "$pbsValue ${if (pbsValue != "1") "days" else "day"}"
        )
    }
}