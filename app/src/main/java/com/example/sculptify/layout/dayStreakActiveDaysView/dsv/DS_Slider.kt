package com.example.sculptify.layout.dayStreakActiveDaysView.dsv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.R
import com.example.sculptify.viewModels.UserViewModel
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DS_Slider() {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    var dayStreakValue = userVM.userdata.value["dayStreak"]?.toString()?.toFloat() ?: 0f
    val weeklyGoalValue = userVM.userdata.value["weeklyGoal"]?.toString()?.toFloat() ?: 1f

    Slider(
        value =
        if (weeklyGoalValue == 4f && dayStreakValue == 4f) {
            3.76f
        } else if (weeklyGoalValue == 3f && dayStreakValue == 3f) {
            2.82f
        } else if (weeklyGoalValue == 2f && dayStreakValue == 2f) {
            1.88f
        } else if (weeklyGoalValue == 1f && dayStreakValue == 1f) {
            0.94f
        } else dayStreakValue,
        onValueChange = { dayStreakValue = it },
        enabled = true,
        modifier = Modifier
            .padding(top = 20.dp),
        valueRange = 0f..weeklyGoalValue,
        steps = weeklyGoalValue.roundToInt()-1,
        track = {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card (
                    colors = CardDefaults.cardColors(Color(0xFF3D3D3D)),
                    shape = MaterialTheme.shapes.extraLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(25.dp)
                ) {}
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card (
                    colors = CardDefaults.cardColors(Color(0xFFFFFFFF)),
                    shape = MaterialTheme.shapes.extraLarge,
                    modifier = Modifier
                        .fillMaxWidth(
                            when (weeklyGoalValue) {
                                4f -> when (dayStreakValue) {
                                    0f -> 0f
                                    1f -> 0.31f
                                    2f -> 0.56f
                                    3f -> 0.81f
                                    4f -> 1f
                                    else -> 1f
                                }

                                3f -> when (dayStreakValue) {
                                    0f -> 0f
                                    1f -> 0.393f
                                    2f -> 0.726f
                                    3f -> 1f
                                    else -> 1f
                                }

                                2f -> when (dayStreakValue) {
                                    0f -> 0f
                                    1f -> 0.56f
                                    2f -> 1f
                                    else -> 1f
                                }

                                1f -> when (dayStreakValue) {
                                    0f -> 0f
                                    1f -> 1f
                                    else -> 1f
                                }

                                else -> 0f
                            }
                        )
                        .height(40.dp)
                ) {}
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.flag_ds_icon),
                    contentDescription = "",
                    tint = Color(0xffFF4E28),
                    modifier = Modifier
                        .width(
                            if (weeklyGoalValue == 4f && dayStreakValue == 4f) {
                                0.dp
                            } else if (weeklyGoalValue == 3f && dayStreakValue == 3f) {
                                0.dp
                            } else if (weeklyGoalValue == 2f && dayStreakValue == 2f) {
                                0.dp
                            } else if (weeklyGoalValue == 1f && dayStreakValue == 1f) {
                                0.dp
                            } else 40.dp
                        )
                        .scale(scaleX = -1f, scaleY = 1f)
                )
            }

        },
        thumb = {
            Card (
                colors = CardDefaults.cardColors(Color(0xffFF4E28)),
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier
                    .size(35.dp)
            ) {}
        },
    )
}