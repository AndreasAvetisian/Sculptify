package com.example.sculptify.layout.msv.bmi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.buttons.CounterInput
import com.example.sculptify.ui.theme.Dark_Orange
import com.example.sculptify.viewModels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMI_EditBodyParameters(
    scope: CoroutineScope,
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
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
            .fillMaxWidth()
            .height(200.dp)
            .padding(horizontal = 15.675.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
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
            thumbColor = Dark_Orange
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
            thumbColor = Dark_Orange
        )
        ConfirmButton(
            text = "Save",
            bgColor = if (currentHeightValue != userHeight || currentWeightValue != userWeight) {
                Dark_Orange
            } else {
                Dark_Orange.copy(0.2f)
            },
            textColor = Color.White,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(30.dp),
            onClick = {
                if (currentHeightValue != userHeight || currentWeightValue != userWeight) {
                    userVM.modifyHeightAndWeight(currentHeightValue, currentWeightValue)
                    isPageRefreshed = true

                    scope.launch {
                        sheetState.hide()
                    }
                        .invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onDismiss()
                            }
                        }
                }
            }
        )
    }
    if (isPageRefreshed) {
        LaunchedEffect(true) {
            userVM.getUserData()
        }
        isPageRefreshed = false
    }
}