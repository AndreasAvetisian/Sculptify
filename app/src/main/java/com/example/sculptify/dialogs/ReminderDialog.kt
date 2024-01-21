package com.example.sculptify.dialogs

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.settings.general.reminder.timePicker.TimePicker
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.viewModels.ReminderViewModel

@Composable
fun ReminderDialog(
    onCancel: () -> Unit,
    onAdd: () -> Unit,
    reminderVM: ReminderViewModel
) {
    val daysOfWeek = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    var selectedDaysOfWeek by remember {
        mutableStateOf(emptyList<String>())
    }

    val everyDay = listOf("Every day")



    var hour by remember {
        mutableIntStateOf(7)
    }

    var minute by remember {
        mutableIntStateOf(30)
    }

    var amOrPm by remember {
        mutableStateOf("PM")
    }

    Dialog(
        onDismissRequest = {
            onCancel()
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
                TimePicker(
                    hoursList = (1..12).toList(),
                    onHoursChanged = {
                        hour = it
                        Log.d("**********************", "$hour:$minute $amOrPm")
                    },
                    minutesList = (0..59).toList(),
                    onMinutesChanged = {
                        minute = it
                        Log.d("**********************", "$hour:$minute $amOrPm")
                    },
                    amOrPmList = listOf("AM", "PM"),
                    onAmOrPmChanged = {
                        amOrPm = it
                        Log.d("**********************", "$hour:$minute $amOrPm")
                    }
                )
                Spacer(modifier = Modifier.height(40.dp))
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    daysOfWeek.forEach { item ->
                        var isSelected by remember { mutableStateOf(false) }
                        Card (
                            colors = CardDefaults.cardColors(
                                if (isSelected) {
                                    Color(0xff0060FE)
                                } else {
                                    Color(0xff1C1C1E)
                                }
                            ),
                            shape = MaterialTheme.shapes.extraLarge,
                            border = BorderStroke(
                                width = 2.dp,
                                color = if (isSelected) {
                                    Color.Transparent
                                } else {
                                    Color(0xff0060FE)
                                }
                            ),
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {
                                    if (selectedDaysOfWeek.contains(item)) {
                                        selectedDaysOfWeek = selectedDaysOfWeek.filter { it != item }
                                        isSelected = false
                                    } else {
                                        selectedDaysOfWeek += item
                                        isSelected = true
                                    }
                                    selectedDaysOfWeek = selectedDaysOfWeek.sortedBy { daysOfWeek.indexOf(it) }

                                }
                        ) {
                            Column (
                                modifier = Modifier
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = item.first().toString(),
                                    fontSize = 25.sp,
                                    fontFamily = balooFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                )
                            }
                        }
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
                        text = "Add",
                        bgColor =
                        if (selectedDaysOfWeek.isNotEmpty()) {
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
                                onAdd()
                                reminderVM.addReminder(
                                    hourValue = hour,
                                    minuteValue = minute,
                                    amOrPm = amOrPm,
                                    isActive = true,
                                    daysOfWeek = if (selectedDaysOfWeek.size == 7) everyDay else selectedDaysOfWeek
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}