package com.example.sculptify.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sculptify.dialogs.ReminderDialog
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.layout.settings.general.reminder.AddReminderButton
import com.example.sculptify.layout.settings.general.reminder.ReminderItem
import com.example.sculptify.main.GENERAL_SETTINGS_ROUTE
import com.example.sculptify.viewModels.TimeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderView(
    navController: NavHostController,
    timeVM: TimeViewModel
) {
    val listOfDays = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TopBarView(
                title = "Reminder",
                onClick = {
                    navController.navigate(GENERAL_SETTINGS_ROUTE)
                }
            )
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.675.dp, 0.dp, 15.675.dp, 15.675.dp)
            ) {
                LazyColumn (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.9f)
                ) {
                    items(2) {
                        ReminderItem(
                            time = "8:50 PM",
                            days = listOfDays
                        )
                    }
                }
                AddReminderButton(
                    onClick = {
                        timeVM.onTimeClick()
                    }
                )
            }
        }
    }
    if (timeVM.isDialogShown) {
        ReminderDialog(
            onDismiss = {
                timeVM.onDismissDialog()
            },
            onConfirm = {
                //
            }
        )
    }
}