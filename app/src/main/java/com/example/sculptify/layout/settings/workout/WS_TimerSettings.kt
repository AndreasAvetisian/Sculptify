package com.example.sculptify.layout.settings.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.WS_Green
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun WS_TimerSettings() {
    val userVM: UserViewModel = viewModel()

    val userData by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value
    )

    val rbeValue = userData["rbe"]?.toString()?.toInt() ?: 0
    val cbsValue = userData["cbs"]?.toString()?.toInt() ?: 0

    var currentRBEValue by remember{ mutableIntStateOf(rbeValue) }
    var currentCBSValue by remember{ mutableIntStateOf(cbsValue) }

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
            title = "Rest Before Exercise",
            titleFontSize = 16.sp,
            value = "$currentRBEValue s",
            onValueIncreaseClick = {
                if (currentRBEValue < 60) {
                    currentRBEValue++
                }
            },
            onValueDecreaseClick = {
                if (currentRBEValue > 5) {
                    currentRBEValue--
                }
            },
            paddingBottom = 5.dp,
            paddingTop = 10.dp,
            buttonWidth = 180.dp,
            circleSize = 60.dp,
            thumbColor = WS_Green
        )
        CounterInput(
            title = "Countdown Before Start",
            titleFontSize = 16.sp,
            value = "$currentCBSValue s",
            onValueIncreaseClick = {
                if (currentCBSValue < 15) {
                    currentCBSValue++
                }
            },
            onValueDecreaseClick = {
                if (currentCBSValue > 10) {
                    currentCBSValue--
                }
            },
            paddingBottom = 5.dp,
            paddingTop = 10.dp,
            buttonWidth = 180.dp,
            circleSize = 60.dp,
            thumbColor = WS_Green
        )
        ConfirmOpenableLineButton(
            bgColor = if (currentRBEValue != rbeValue || currentCBSValue != cbsValue) {
                WS_Green
            } else {
                WS_Green.copy(0.2f)
            },
            onClick = {
                if (currentRBEValue != rbeValue || currentCBSValue != cbsValue) {
                    userVM.modifyTimerSettings(currentRBEValue, currentCBSValue)
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