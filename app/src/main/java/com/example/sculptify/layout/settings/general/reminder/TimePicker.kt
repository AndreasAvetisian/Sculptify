package com.example.sculptify.layout.settings.general.reminder

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun TimePicker() {
    var selectedHour by remember { mutableStateOf(0) }
    var selectedMinute by remember { mutableStateOf(0) }
    var isAM by remember { mutableStateOf(true) }

    var hourList = (0..12).toList()
    var scrollStateHour = rememberLazyListState()

    var minuteList = (0..59).toList()
    var scrollStateMinute = rememberLazyListState()

    val ampmList = listOf("AM", "PM")
    var scrollStateAMPM = rememberLazyListState()

    var aaaaa by remember { mutableStateOf("") }

    Card (
        colors = CardDefaults.cardColors(Color(0xFF000000)),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .width(300.dp)
            .height(110.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(15.675.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(0.33f),
                state = scrollStateHour
            ) {
                items(hourList.size) { index ->
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp),
                    ) {
                        Text(
                            text = hourList[index].toString(),
                            fontSize = 50.sp,
                            fontFamily = balooFontFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedHour = hourList[index]
                                    Log.d("((((((((((((((((((((((((((", "$selectedHour:$selectedMinute" + if (isAM) "AM" else "PM")
                                }
                        )
                    }
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                state = scrollStateMinute
            ) {
                items(minuteList.size) { index ->
                    Text(
                        text = minuteList[index].toString(),
                        fontSize = 50.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                selectedMinute = minuteList[index]
                                Log.d("((((((((((((((((((((((((((", "$selectedHour:$selectedMinute" + if (isAM) "AM" else "PM")
                            },
                        textAlign = TextAlign.Center
                    )
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                Text(
                    text = "AM",
                    fontSize = 30.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .background(Color(0xFF2020CF)),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "PM",
                    fontSize = 30.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

//    TimeInput(
//        state = timeState,
//        colors = TimePickerDefaults.colors(
//            clockDialColor = Color.Red,
//            clockDialSelectedContentColor = Color.Red,
//            clockDialUnselectedContentColor = Color.Red,
//            selectorColor = Color.Red,
//            containerColor = Color.Red,
//            periodSelectorBorderColor =   Color(0xff1C1C1E),
//            periodSelectorSelectedContainerColor = Color(0xff0060FE),
//            periodSelectorUnselectedContainerColor = Color(0xff909090),
//            periodSelectorSelectedContentColor = Color.White,
//            periodSelectorUnselectedContentColor = Color.Black,
//            timeSelectorSelectedContainerColor = Color(0xff0060FE),
//            timeSelectorUnselectedContainerColor = Color(0xff909090),
//            timeSelectorSelectedContentColor = Color.White,
//            timeSelectorUnselectedContentColor = Color.Black,
//        ),
//        modifier = Modifier
//    )
}

@Composable
fun LocalFocusManagerExample() {
    var text by remember { mutableStateOf("text") }
    val focusManager = LocalFocusManager.current

    TextField(
        value = text,
        onValueChange = {
            text = it
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            }
        )
    )
}

