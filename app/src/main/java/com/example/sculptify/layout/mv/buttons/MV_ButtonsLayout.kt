package com.example.sculptify.layout.mv.buttons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.R
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun MV_ButtonsLayout(navController: NavHostController) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val dayStreakValue = userVM.userdata.value["dayStreak"].toString()
    val weeklyGoalValue = userVM.userdata.value["weeklyGoal"].toString()

    Row (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        MV_Button(
            navController = navController,
            data = dayStreakValue,
            iconId = R.drawable.day_streak_main_icon,
            iconColor = Color(0xffff4e28),
            title = "Day Streak",
            stat = "Personal best: 0",
            width = 0.5f,
            paddingStart = 0.dp,
            paddingEnd = 10.dp
        )
        MV_Button(
            navController = navController,
            data = "0/$weeklyGoalValue",
            iconId = R.drawable.active_days_main_icon,
            iconColor = Color(0xff0060FE),
            title = "This week",
            stat = "in Total: 0",
            width = 1f,
            paddingStart = 10.dp,
            paddingEnd = 0.dp
        )
    }
}