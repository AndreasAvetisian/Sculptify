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
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters


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

    //--------------------------------------------------------------------------------

    val historyOfWorkouts = userData["historyOfWorkouts"] as? List<Map<String, Any>> ?: emptyList()

    val monthMap = mapOf(
        "Jan" to "01", "Feb" to "02", "Mar" to "03", "Apr" to "04",
        "May" to "05", "Jun" to "06", "Jul" to "07", "Aug" to "08",
        "Sep" to "09", "Oct" to "10", "Nov" to "11", "Dec" to "12"
    )

    val listOfWorkoutDates = historyOfWorkouts.mapNotNull { workout ->
        val date = (workout["date"] as? String)?.split(";")?.getOrNull(1)
        date?.let { dateString ->
            val (monthAbbreviation, dayOfMonth) = dateString.split(' ')
            val month = monthMap[monthAbbreviation]
            "$month-$dayOfMonth"
        }
    }

    val startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
    val formatter = DateTimeFormatter.ofPattern("MM-dd")
    val daysOfWeek = (0 until 7)
        .map {
            startOfWeek.plusDays(it.toLong())
        }.map {
            it.format(formatter)
        }

    val uniqueWorkoutDates = listOfWorkoutDates.toSet()
    val currentAmountForWeeklyGoal = uniqueWorkoutDates.count { it in daysOfWeek }

    val weeklyGoalDisplay = if (currentAmountForWeeklyGoal >= weeklyGoalValue.toString().toInt()) {
        "Done!"
    } else {
        "$currentAmountForWeeklyGoal/$weeklyGoalValue"
    }

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
            data = weeklyGoalDisplay,
            iconId = R.drawable.active_days_main_icon,
            iconColor = Blue,
            title = "This week",
            stat = "in Total: $currentAmountForWeeklyGoal",
            width = 1f,
            paddingStart = 10.dp,
            paddingEnd = 0.dp
        )
    }
}