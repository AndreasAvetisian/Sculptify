package com.example.sculptify.pages.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.layout.general.buttons.OpenableLineButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.layout.settings.general.GS_ReminderButton
import com.example.sculptify.main.VERSION
import com.example.sculptify.screens.Screen

@Composable
fun GeneralSettingsView(navController: NavHostController) {
    var isOpen by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize(),
    ) {
        TopBarView(
            title = "General Settings",
            navController = navController
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                GS_ReminderButton(
                    onClick = {
                        navController.navigate(Screen.Reminder.route)
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))

                OpenableLineButton(
                    text = "Read Me",
                    route = Screen.GeneralSettings.route,
                    index = 1,
                    onClick = {
                        isOpen = !isOpen
                    },
                    isOpen = isOpen
                )

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CustomText(
                        text = "Version: $VERSION",
                        fontSize = 15.5.sp
                    )
                }
            }
        }
    }
}