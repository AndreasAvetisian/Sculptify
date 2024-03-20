package com.example.sculptify.layout.mv.buttons

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.R
import com.example.sculptify.screens.Screen
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Dark_Orange
import com.example.sculptify.viewModels.UserViewModel


var selectedTabIndexForDSAD by mutableIntStateOf(0)

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MV_ButtonsLayout(navController: NavHostController) {
    val userVM: UserViewModel = viewModel()

    val userData by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value
    )

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val dayStreakValue = userData["dayStreak"] ?: 0
    val weeklyGoalValue = userData["weeklyGoal"] ?: 0
    val pbsValue = userData["pbs"] ?: 0

    Row (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        MV_Button(
            onClick = {
                selectedTabIndexForDSAD = 0
                navController.navigate(Screen.DSAD.route)
            },
            isLoading = userVM.isLoading.value,
            data = dayStreakValue.toString(),
            iconId = R.drawable.day_streak_main_icon,
            iconColor = Dark_Orange,
            title = "Day Streak",
            stat = "Personal best: $pbsValue",
            width = 0.5f,
            paddingStart = 0.dp,
            paddingEnd = 10.dp
        )
        MV_Button(
            onClick = {
                selectedTabIndexForDSAD = 1
                navController.navigate(Screen.DSAD.route)
            },
            isLoading = userVM.isLoading.value,
            data = "0/$weeklyGoalValue",
            iconId = R.drawable.active_days_main_icon,
            iconColor = Blue,
            title = "This week",
            stat = "in Total: 0",
            width = 1f,
            paddingStart = 10.dp,
            paddingEnd = 0.dp
        )
    }
}