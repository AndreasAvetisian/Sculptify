package com.example.sculptify.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.layout.myFavMyHisView.mhv.MHV_ListItem
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun MyHistoryView() {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val userData by userVM.userdata.collectAsState()

    val historyOfWorkouts = userData["historyOfWorkouts"] as? List<Map<String, Any>> ?: emptyList()

    fun formatTime(seconds: Long): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60

        return String.format("%02d:%02d", minutes, remainingSeconds)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (historyOfWorkouts.isNotEmpty()) {
                itemsIndexed(historyOfWorkouts.reversed()) { _, workout ->
                    val workoutMap = workout.toMap()
                    val workoutDate = workoutMap["date"].toString()
                    val workoutFocusArea = workoutMap["focusArea"].toString()
                    val workoutLevel = workoutMap["level"].toString()
                    val workoutFinalDuration = workoutMap["finalDuration"].toString().toLong()
                    val workoutCaloriesBurned = workoutMap["caloriesBurned"].toString().toFloat()

                    val time = workoutDate.split(";")[0]
                    val date = workoutDate.split(";")[1]

                    MHV_ListItem(
                        workoutFocusArea = workoutFocusArea,
                        workoutLevel = workoutLevel,
                        time = time,
                        date = date,
                        duration = formatTime(workoutFinalDuration),
                        kcal = workoutCaloriesBurned.toString()
                    )
                }
            }
        }
    }
}