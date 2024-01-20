package com.example.sculptify.pages

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.dialogs.ReminderDialog
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.layout.settings.general.reminder.AddReminderButton
import com.example.sculptify.main.GENERAL_SETTINGS_ROUTE
import com.example.sculptify.viewModels.ReminderViewModel
import com.example.sculptify.viewModels.UserViewModel


@Composable
fun ReminderView(
    navController: NavHostController,
    reminderVM: ReminderViewModel
) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val reminders = userVM.userdata.value["reminders"] as? List<Map<String, Any>> ?: emptyList()

    Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", reminders[0]["hourValue"].toString())

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
//                    items(reminders.size) { index ->
//                        val time = "${reminders[index]["hourValue"]}:${reminders[index]["minuteValue"]} ${reminders[index]["amOrPm"]}"
//                        val days = reminders[index]["daysOfWeek"] as List<String>
//                        val isSwitchActive = reminders[index]["active"] as Boolean
//
//                        ReminderItem(
//                            time = time,
//                            days = days,
//                            isSwitchActive = isSwitchActive,
//                            onSwitchChanged = { /* Handle switch change if needed */ }
//                        )
//                    }
                }
                AddReminderButton(
                    onClick = {
                        reminderVM.onAddReminderClick()
                    }
                )
            }
        }
    }
    if (reminderVM.isDialogShown) {
        ReminderDialog(
            onCancel = {
                reminderVM.onDismissDialog()
            },
            onAdd = {
                reminderVM.onDismissDialog()
            },
            reminderVM = reminderVM
        )
    }
}