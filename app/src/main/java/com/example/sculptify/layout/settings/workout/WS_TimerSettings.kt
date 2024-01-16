package com.example.sculptify.layout.settings.workout

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.buttons.CounterInput
import com.example.sculptify.viewModels.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun WS_TimerSettings(
    animationDuration: Int = 50,
    scaleDown: Float = 0.9f
) {
    val userVM: UserViewModel = viewModel()

    val rbeValue = userVM.userdata.value["rbe"]?.toString()?.toInt() ?: 0
    val cbsValue = userVM.userdata.value["cbs"]?.toString()?.toInt() ?: 0

    var currentRBEValue by remember{ mutableIntStateOf(rbeValue) }
    var currentCBSValue by remember{ mutableIntStateOf(cbsValue) }

    val interactionSource = MutableInteractionSource()

    val coroutineScope = rememberCoroutineScope()

    val scale = remember {
        Animatable(1f)
    }

    var isPageRefreshed by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xff1C1C1E)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .padding(horizontal = 15.675.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround
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
                paddingBottom = 10.dp,
                buttonWidth = 180.dp,
                circleSize = 50.dp
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
                paddingBottom = 10.dp,
                buttonWidth = 180.dp,
                circleSize = 50.dp
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConfirmButton(
                text = "Save",
                textColor = Color.White,
                bgColor = if (currentRBEValue != rbeValue || currentCBSValue != cbsValue) {
                    Color(0xff0000ff)
                } else {
                    Color(0xff0000ff).copy(0.2f)
                },
                modifier = Modifier
                    .scale(scale = scale.value)
                    .fillMaxWidth(0.5f)
                    .height(30.dp)
                    .clickable (
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        coroutineScope.launch {
                            scale.animateTo(
                                scaleDown,
                                animationSpec = tween(animationDuration),
                            )
                            scale.animateTo(
                                1f,
                                animationSpec = tween(animationDuration),
                            )
                            if (currentRBEValue != rbeValue || currentCBSValue != cbsValue) {
                                userVM.modifyTimerSettings(currentRBEValue, currentCBSValue)
                                isPageRefreshed = true
                            }
                        }
                    }
            )
        }
    }
    if (isPageRefreshed) {
        LaunchedEffect(true) {
            userVM.getUserData()
        }
        isPageRefreshed = false
    }
}