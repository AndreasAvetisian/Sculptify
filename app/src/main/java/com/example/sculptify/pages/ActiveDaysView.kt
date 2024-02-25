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

    val weeklyGoalValue = userData["weeklyGoal"]?.toString() ?: 0

    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    // Callback function to be passed to MeMBS
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
            currentValue = 4,
            primaryColor = Blue,
            secondaryColor = Dark_Gray,
            circleRadius = 320f,
            maxValue = weeklyGoalValue.toString().toInt()
        )
        ADV_ModifyButton(
            onClick = { showBottomSheet = true }
        )
        ADV_CurrentWeekIndicator()
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