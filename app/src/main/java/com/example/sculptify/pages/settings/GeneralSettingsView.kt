package com.example.sculptify.pages.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sculptify.layout.general.buttons.OpenableLineButton
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.layout.settings.general.GS_ReminderButton
import com.example.sculptify.main.MAIN_ROUTE
import com.example.sculptify.main.REMINDER_ROUTE

@Composable
fun GeneralSettingsView(navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            TopBarView(
                title = "General Settings",
                onClick = {
                    navController.navigate(MAIN_ROUTE)
                }
            )

            GS_ReminderButton(
                onClick = {
                    navController.navigate(REMINDER_ROUTE)
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OpenableLineButton(
                text = "Read Me",
                isProfileView = false,
                isTimerSettingsView = false,
                isReadMeView = true
            )
        }
    }
}