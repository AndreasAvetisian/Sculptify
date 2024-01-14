package com.example.sculptify.layout.mv.buttons

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.R
import com.example.sculptify.main.DAY_STREAK_ACTIVE_DAYS_ROUTE
import com.example.sculptify.viewModels.UserViewModel


var selectedTabIndexForDSAD by mutableIntStateOf(0)

@Composable
fun MV_ButtonsLayout(navController: NavHostController) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val dayStreakValue = userVM.userdata.value["dayStreak"] ?: 0
    val weeklyGoalValue = userVM.userdata.value["weeklyGoal"] ?: 0

    Row (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        MV_Button(
            onClick = {
                selectedTabIndexForDSAD = 0
                navController.navigate(DAY_STREAK_ACTIVE_DAYS_ROUTE)
            },
            data = dayStreakValue.toString(),
            iconId = R.drawable.day_streak_main_icon,
            iconColor = Color(0xffff4e28),
            title = "Day Streak",
            stat = "Personal best: 0",
            width = 0.5f,
            paddingStart = 0.dp,
            paddingEnd = 10.dp
        )
        MV_Button(
            onClick = {
                selectedTabIndexForDSAD = 1
                navController.navigate(DAY_STREAK_ACTIVE_DAYS_ROUTE)
            },
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