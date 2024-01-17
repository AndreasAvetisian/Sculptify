package com.example.sculptify.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.dialogs.TimeDialog
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.main.GENERAL_SETTINGS_ROUTE
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.viewModels.TimeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderView(
    navController: NavHostController,
    timeVM: TimeViewModel
) {
    var checked by remember { mutableStateOf(false) }

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
                    items(4) {
                        Card (
                            colors = CardDefaults.cardColors(Color(0xff1C1C1E)),
                            shape = MaterialTheme.shapes.large,
                            modifier = Modifier
                                .padding(bottom = 15.675.dp)
                        ) {
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(15.675.dp)
                            ) {
                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = "8:30 PM",
                                        fontSize = 40.sp,
                                        fontFamily = balooFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Switch(
                                        checked = checked,
                                        onCheckedChange = {
                                            checked = it
                                        },
                                        colors = SwitchDefaults.colors(
                                            checkedThumbColor = Color.White,
                                            checkedTrackColor = Color(0xff0060FE),
                                            checkedBorderColor = Color(0xff0060FE),
                                            uncheckedThumbColor = Color(0xff909090),
                                            uncheckedTrackColor = Color(0xFFDFDFDF),
                                            uncheckedBorderColor = Color(0xFFDFDFDF)
                                        )
                                    )
                                }
                                Divider(
                                    thickness = 2.dp,
                                    color = Color(0xff909090),
                                    modifier = Modifier
                                        .padding(bottom = 10.dp)
                                )
                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    repeat(7) {
                                        Card (
                                            colors = CardDefaults.cardColors(Color(0xFF2020CF)),
                                            shape = MaterialTheme.shapes.large,
                                            modifier = Modifier
                                                .size(40.dp, 30.dp)
                                        ) {
                                            Column (
                                                modifier = Modifier
                                                    .fillMaxSize(),
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Text(
                                                    text = "Mon",
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
                    }
                }
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Divider(
                        thickness = 2.dp,
                        color = Color(0xff909090),
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                    )
                    ConfirmButton(
                        text = "Add",
                        bgColor = Color(0xff0060FE),
                        textColor = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        onClick = {
                            timeVM.onTimeClick()
                        }
                    )
                }
            }
        }
    }
    if (timeVM.isDialogShown) {
        TimeDialog(
            onDismiss = {
                timeVM.onDismissDialog()
            },
            onConfirm = {
                //
            }
        )
    }
}