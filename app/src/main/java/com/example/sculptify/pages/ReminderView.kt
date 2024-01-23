package com.example.sculptify.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.dialogs.ReminderDialog
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.layout.settings.general.reminder.EmptyListIcon
import com.example.sculptify.layout.settings.general.reminder.ReminderBottomBar
import com.example.sculptify.layout.settings.general.reminder.ReminderItem
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

    var isEditClicked by remember { mutableStateOf(false)}

    var isPageRefreshed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarView(
            title = "Reminder",
            onClick = {
                navController.navigate(GENERAL_SETTINGS_ROUTE)
                isEditClicked = false
            }
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.675.dp, 0.dp, 15.675.dp, 0.dp)
        ) {
            LazyColumn (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.875f),
            ) {
                if (reminders.isNotEmpty()) {
                    items(reminders.size) { index ->
                        val time = "${reminders[index]["hourValue"]}:${reminders[index]["minuteValue"].toString().padStart(2, '0')} ${reminders[index]["amOrPm"]}"
                        val days = reminders[index]["daysOfWeek"] as List<String>

                        val isSwitchActive = reminders[index]["active"] as Boolean

                        var currentSwitchState by remember{ mutableStateOf(isSwitchActive) }

                        ReminderItem(
                            time = time,
                            days = days,
                            isSwitchActive = currentSwitchState,
                            onSwitchChanged = {
                                currentSwitchState = it
                                reminderVM.changeReminderState(index, currentSwitchState)
                                isPageRefreshed = true
                            },
                            isEditClicked = isEditClicked,
                            onDeletedClicked = {
                                reminderVM.deleteReminder(index)
                                isEditClicked = false
                                isPageRefreshed = true
                            }
                        )
                    }
                } else {
                    item {
                        EmptyListIcon()
                    }
                }
            }
        }
        ReminderBottomBar(
            onAddClick = {
                reminderVM.onAddReminderClick()
                isPageRefreshed = true
            },
            onEditClick = {
                isEditClicked = !isEditClicked
                isPageRefreshed = true
            }
        )
    }
    if (reminderVM.isDialogShown) {
        ReminderDialog(
            onCancel = {
                reminderVM.onDismissDialog()
                isPageRefreshed = true
            },
            onAdd = {
                reminderVM.onDismissDialog()
                isPageRefreshed = true
            },
            reminderVM = reminderVM
        )
    }
    if (isPageRefreshed) {
        LaunchedEffect(true) {
            userVM.getUserData()
        }
        isPageRefreshed = false
    }
}