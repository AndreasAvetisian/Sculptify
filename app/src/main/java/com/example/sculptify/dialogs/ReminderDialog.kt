package com.example.sculptify.dialogs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.settings.general.reminder.rd.RD_DaysOfWeek
import com.example.sculptify.layout.settings.general.reminder.timePicker.TimePicker
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.viewModels.ReminderViewModel
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun ReminderDialog(
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    reminderVM: ReminderViewModel,
    initialSelectedDays: List<String>,
    clickedReminderIndex: Int
) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    var selectedDaysOfWeek by remember {
        mutableStateOf(
            if (initialSelectedDays.contains("Every day")) {
                daysOfWeek
            } else {
                initialSelectedDays
            }
        )
    }

    val everyDay = listOf("Every day")

    var hour by remember { mutableIntStateOf(7) }
    var minute by remember { mutableIntStateOf(30) }
    var amOrPm by remember { mutableStateOf("PM") }

    val doesReminderAlreadyExist by remember { derivedStateOf { reminderVM.doesReminderAlreadyExist } }

    Dialog(
        onDismissRequest = {
            onCancel()
            reminderVM.doesReminderAlreadyExist = false
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card (
            colors = CardDefaults.cardColors(Color(0xff1C1C1E)),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(horizontal = 15.675.dp)
                .border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(25.dp)
                )
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.675.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!doesReminderAlreadyExist) {
                    TimePicker(
                        hoursList = (1..12).toList(),
                        onHoursChanged = { hour = it },
                        minutesList = (0..59).toList(),
                        onMinutesChanged = { minute = it },
                        amOrPmList = listOf("AM", "PM"),
                        onAmOrPmChanged = { amOrPm = it }
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        daysOfWeek.forEach { item ->
                            var isSelected by remember {
                                mutableStateOf(initialSelectedDays.contains(item) || initialSelectedDays.contains("Every day"))
                            }
                            RD_DaysOfWeek(
                                bgColor = if (isSelected) Color(0xff0060FE) else Color(0xff1C1C1E),
                                borderColor = if (isSelected) Color.Transparent else Color(0xff0060FE),
                                onClick = {
                                    isSelected = !isSelected
                                    selectedDaysOfWeek = if (isSelected && item == "Every day") {
                                        daysOfWeek
                                    } else if (!isSelected && item == "Every day") {
                                        emptyList()
                                    } else {
                                        selectedDaysOfWeek.toMutableList().apply {
                                            if (isSelected) {
                                                add(item)
                                            } else {
                                                remove(item)
                                            }
                                        }
                                    }
                                },
                                text = item.first().toString()
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ConfirmButton(
                            text = "Cancel",
                            bgColor = Color(0xff0060FE),
                            textColor = Color.White,
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .height(40.dp)
                                .padding(end = 7.5.dp),
                            onClick = {
                                onCancel()
                            }
                        )
                        ConfirmButton(
                            text = if (reminderVM.isCreateDialogShown) "Add" else "Modify",
                            bgColor =
                            if (selectedDaysOfWeek !== initialSelectedDays) {
                                Color(0xff0060FE)
                            } else {
                                Color(0xff0060FE).copy(0.2f)
                            },
                            textColor = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .padding(start = 7.5.dp),
                            onClick = {
                                if (selectedDaysOfWeek.isNotEmpty()) {
                                    if (reminderVM.isCreateDialogShown) {
                                        reminderVM.addReminder(
                                            hourValue = hour,
                                            minuteValue = minute,
                                            amOrPmValue = amOrPm,
                                            daysOfWeek = if (selectedDaysOfWeek.size == 7) everyDay else selectedDaysOfWeek
                                        )
                                        onConfirm()
                                    } else {
                                        if (clickedReminderIndex != -1) {
                                            reminderVM.modifyReminder(
                                                reminderIndex = clickedReminderIndex,
                                                hourValue = hour,
                                                minuteValue = minute,
                                                amOrPmValue = amOrPm,
                                                daysOfWeek = if (selectedDaysOfWeek.size == 7) everyDay else selectedDaysOfWeek.sortedBy {
                                                    daysOfWeek.indexOf(it)
                                                }
                                            )
                                            onConfirm()
                                        }
                                    }
                                }
                            }
                        )
                    }
                } else {
                    Text(
                        text = "Reminder already exists",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}