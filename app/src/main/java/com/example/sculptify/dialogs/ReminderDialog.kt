package com.example.sculptify.dialogs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.settings.general.reminder.TimePicker
import com.example.sculptify.ui.theme.balooFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderDialog(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    val timeState = rememberTimePickerState()
    val listOfDays = listOf("Every day", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

    Dialog(
        onDismissRequest = {
            onDismiss()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card (
            colors = CardDefaults.cardColors(Color(0xff1C1C1E)),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            ),
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.675.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.675.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimePicker()
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        contentPadding = PaddingValues(15.675.dp, 0.dp),
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                    ) {
                        listOfDays.forEach {item ->
                            item {
                                Card (
                                    colors = CardDefaults.cardColors(Color(0xFF2020CF)),
                                    shape = MaterialTheme.shapes.large,
                                    modifier = Modifier
                                        .size(80.dp, 40.dp)
                                        .padding(bottom = 10.dp)
                                ) {
                                    Column (
                                        modifier = Modifier
                                            .fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = item,
                                            fontSize = 14.sp,
                                            fontFamily = balooFontFamily,
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
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
                            onDismiss()
                        }
                    )
                    ConfirmButton(
                        text = "Add",
                        bgColor = Color(0xff0060FE),
                        textColor = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .padding(start = 7.5.dp),
                        onClick = {
                            onConfirm()
                        }
                    )
                }
            }
        }
    }
}