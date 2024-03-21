package com.example.sculptify.pages

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.layout.dayStreakActiveDaysView.adv.ADV_CurrentWeekIndicator
import com.example.sculptify.layout.dayStreakActiveDaysView.adv.ADV_ModifyButton
import com.example.sculptify.layout.dayStreakActiveDaysView.adv.customCPI.CustomCPI
import com.example.sculptify.layout.mbs.MBS
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.viewModels.UserViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveDaysView(navController: NavHostController) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val userData by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value
    )

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

    val currentAmountForWeeklyGoal = listOfWorkoutDates.count { it in daysOfWeek }

    val weeklyGoalValue = userData["weeklyGoal"]?.toString() ?: 0

    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val onBottomSheetDismiss: () -> Unit = {
        showBottomSheet = false
    }

    val heightAnimation = remember { Animatable(1f) }
    LaunchedEffect(true) {
        heightAnimation.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 500,
                easing = LinearEasing
            )
        )
    }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(heightAnimation.value))
        CustomCPI(
            modifier = Modifier
                .size(250.dp)
                .clickable {
                    showBottomSheet = true
                },
            currentValue = currentAmountForWeeklyGoal,
            primaryColor = Blue,
            secondaryColor = Dark_Gray,
            circleRadius = 320f,
            maxValue = weeklyGoalValue.toString().toInt()
        )
        ADV_ModifyButton(
            onClick = { showBottomSheet = true }
        )
        ADV_CurrentWeekIndicator(
            daysOfWeek = daysOfWeek,
            listOfWorkoutDates = listOfWorkoutDates
        )
        if (showBottomSheet) {
            MBS(
                sheetState = sheetState,
                scope = scope,
                onDismiss = onBottomSheetDismiss,
                navController = navController
            )
        }
    }
}