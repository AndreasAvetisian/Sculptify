package com.example.sculptify.pages

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.sculptify.layout.wv.WV_Screen

@Composable
fun WorkoutView(navController: NavHostController) {
//    var isStopwatchRunning by remember { mutableStateOf(true) }
//    var isCancelMenuOpen by remember { mutableStateOf(false) }
//
//    val onCancelMenuClicked = {
//        isStopwatchRunning = false
//        isCancelMenuOpen = true
//    }
//
//    val onResumeClicked = {
//        isCancelMenuOpen = false
//        isStopwatchRunning = true
//    }

    WV_Screen(
        navController = navController,
//        isStopwatchRunning = isStopwatchRunning,
//        onCancelMenuClicked = onCancelMenuClicked,
//        isCancelMenuOpen = isCancelMenuOpen,
//        onResumeClicked = onResumeClicked
    )
}