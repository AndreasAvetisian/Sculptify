package com.example.sculptify.pages.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.sculptify.layout.general.buttons.OpenableLineButton
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.layout.settings.workout.WS_ResetAllDataButton
import com.example.sculptify.screens.Screen

@Composable
fun WorkoutSettingsView(navController: NavHostController) {
    var isOpen by remember { mutableStateOf(false) }

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
                navController = navController
            )
            OpenableLineButton(
                text = "Timer Settings",
                route = Screen.WorkoutSettings.route,
                index = 1,
                onClick = {
                    isOpen = !isOpen
                },
                isOpen = isOpen
            )
        }
        WS_ResetAllDataButton()
    }
}