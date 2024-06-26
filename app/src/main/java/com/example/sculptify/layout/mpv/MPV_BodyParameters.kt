package com.example.sculptify.layout.mpv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.layout.general.buttons.ConfirmOpenableLineButton
import com.example.sculptify.layout.general.buttons.CounterInput
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun MPV_BodyParameters() {
    val userVM: UserViewModel = viewModel()

    val userData by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value
    )

    val userHeight = userData["height"]?.toString()?.toInt() ?: 0
    val userWeight = userData["weight"]?.toString()?.toFloat() ?: 0f

    var currentHeightValue by remember{ mutableIntStateOf(userHeight) }
    var currentWeightValue by remember{ mutableFloatStateOf(userWeight) }

    var isPageRefreshed by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .background(Dark_Gray)
            .padding(horizontal = 15.675.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CounterInput(
            title = "Height",
            titleFontSize = 20.sp,
            value = "$currentHeightValue cm",
            onValueIncreaseClick = {
                if (currentHeightValue < 250) {
                    currentHeightValue++
                }
            },
            onValueDecreaseClick = {
                if (currentHeightValue > 100) {
                    currentHeightValue--
                }
            },
            paddingBottom = 5.dp,
            paddingTop = 10.dp,
            buttonWidth = 240.dp,
            circleSize = 110.dp,
            thumbColor = Blue
        )
        CounterInput(
            title = "Weight",
            titleFontSize = 20.sp,
            value = "$currentWeightValue kg",
            onValueIncreaseClick = {
                if (currentWeightValue < 250) {
                    currentWeightValue += 0.5f
                }
            },
            onValueDecreaseClick = {
                if (currentWeightValue > 30) {
                    currentWeightValue -= 0.5f
                }
            },
            paddingBottom = 10.dp,
            paddingTop = 10.dp,
            buttonWidth = 240.dp,
            circleSize = 110.dp,
            thumbColor = Blue
        )
        ConfirmOpenableLineButton(
            bgColor = if (currentHeightValue != userHeight || currentWeightValue != userWeight) {
                Blue
            } else {
                Blue.copy(0.2f)
            },
            onClick = {
                if (currentHeightValue != userHeight || currentWeightValue != userWeight) {
                    userVM.modifyHeightAndWeight(currentHeightValue, currentWeightValue)
                    isPageRefreshed = true
                }
            }
        )
        if (isPageRefreshed) {
            LaunchedEffect(true) {
                userVM.getUserData()
            }
            isPageRefreshed = false
        }
    }
}