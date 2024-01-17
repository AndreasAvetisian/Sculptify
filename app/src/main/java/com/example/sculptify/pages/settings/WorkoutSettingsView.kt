package com.example.sculptify.pages.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.sculptify.layout.general.buttons.OpenableLineButton
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.layout.settings.workout.WS_DeleteAllDataButton
import com.example.sculptify.main.MAIN_ROUTE

@Composable
fun WorkoutSettingsView(navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            TopBarView(
                title = "Workout Settings",
                onClick = {
                    navController.popBackStack()
                    navController.navigate(MAIN_ROUTE)
                }
            )
            OpenableLineButton(
                text = "Timer Settings",
                isProfileView = false,
                isTimerSettingsView = true,
                isReadMeView = false
            )
        }
        WS_DeleteAllDataButton()
    }
}