package com.example.sculptify.pages

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import com.example.sculptify.screens.Screen
import com.example.sculptify.viewModels.ReminderViewModel
import com.example.sculptify.viewModels.UserViewModel


@Composable
fun ReminderView(
    navController: NavHostController,
    reminderVM: ReminderViewModel
) {
    val userVM: UserViewModel = viewModel()

    val userData by userVM.userdata.collectAsState()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val reminderList = userData["reminders"] as? List<Map<String, Any>> ?: emptyList()

    var isEditClicked by remember { mutableStateOf(false) }

    var clickedReminderId by remember { mutableStateOf<String?>(null) }

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

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarView(
            title = "Reminder",
            navController = navController,
            onBackClick = {
                isEditClicked = false
            }
        )
        Spacer(modifier = Modifier.fillMaxHeight(heightAnimation.value))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.675.dp, 0.dp, 15.675.dp, 0.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.875f),
            ) {
                if (reminderList.isNotEmpty()) {
                    itemsIndexed(reminderList) { _, reminder ->
                        val time = "${reminder["hourValue"]}:${
                            reminder["minuteValue"].toString().padStart(2, '0')
                        } ${reminder["amOrPm"]}"

                        val days = reminder["daysOfWeek"] as List<String>

                        val isSwitchActive = reminder["active"] as Boolean

                        var currentSwitchState by remember { mutableStateOf(isSwitchActive) }

                        ReminderItem(
                            time = time,
                            days = days,
                            isSwitchActive = currentSwitchState,
                            onSwitchChanged = {
                                currentSwitchState = it
                                clickedReminderId = reminder["id"] as String
                                reminderVM.changeReminderState(clickedReminderId!!, currentSwitchState)
                            },
                            onEditClicked = {
                                clickedReminderId = reminder["id"] as String
                                reminderVM.onEditReminderClick(clickedReminderId!!)
                            },
                            onDeletedClicked = {
                                clickedReminderId = reminder["id"] as String
                                reminderVM.deleteReminder(clickedReminderId!!)
                                isEditClicked = false
                            }
                        )
                    }
                } else {
                    item {
                        EmptyListIcon(
                            route = Screen.Reminder.route
                        )
                    }
                }
            }
        }
        ReminderBottomBar(
            onAddClick = {
                reminderVM.onAddReminderClick()
            }
        )
    }
    if (reminderVM.isCreateDialogShown || reminderVM.isEditDialogShown || reminderVM.doesReminderAlreadyExist) {
        val initialSelectedDays = if (reminderVM.isEditDialogShown) {
            val selectedReminderId = clickedReminderId ?: ""
            reminderList.find { it["id"] == selectedReminderId }?.get("daysOfWeek") as? List<String> ?: emptyList()
        } else {
            emptyList()
        }

        ReminderDialog(
            onCancel = {
                reminderVM.onDismissDialog()
            },
            onConfirm = {
                reminderVM.onDismissDialog()
            },
            reminderVM = reminderVM,
            initialSelectedDays = initialSelectedDays,
            clickedReminderId = clickedReminderId
        )
    }
}