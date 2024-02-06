package com.example.sculptify.layout.dayStreakActiveDaysView.adv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.counterButton.CounterButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.ADV_Blue
import com.example.sculptify.ui.theme.ADV_Orange
import com.example.sculptify.ui.theme.ADV_Red
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.White
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.viewModels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ADV_ModifyWeeklyGoal(
    scope: CoroutineScope,
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val weeklyGoalValue = userVM.userdata.value["weeklyGoal"]?.toString()?.toInt() ?: 0

    var currentWeeklyGoalValue by remember{ mutableIntStateOf(weeklyGoalValue) }

    var isPageRefreshed by remember { mutableStateOf(false) }


    val spanStyle = SpanStyle(
        fontSize = 16.sp,
        fontFamily = balooFontFamily,
        fontWeight = FontWeight.Bold,
        color = White,
        textDecoration = TextDecoration.None
    )

    val text = buildAnnotatedString {
        withStyle(style = spanStyle) {
            append("Keep ")
        }
        withStyle(style = spanStyle.copy(
            color = when (currentWeeklyGoalValue) {
                1 -> ADV_Blue
                2 -> ADV_Blue
                3 -> ADV_Orange
                4 -> ADV_Orange
                else -> ADV_Red
            }
        )) {
            append("$currentWeeklyGoalValue")
        }
        withStyle(style = spanStyle) {
            append(" days with workout records in a week!")
        }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Dark_Gray)
            .height(250.dp)
            .padding(15.675.dp, 15.675.dp, 15.675.dp, 39.675.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CustomText(
                text = "Weekly active days",
            )
            Text(
                text = text
            )
        }
        CounterButton(
            value = currentWeeklyGoalValue.toString(),
            onValueIncreaseClick = {
                if (currentWeeklyGoalValue < 7) {
                    currentWeeklyGoalValue++
                }
            },
            onValueDecreaseClick = {
                if (currentWeeklyGoalValue > 1) {
                    currentWeeklyGoalValue--
                }
            },
            width = 200.dp,
            height = 60.dp,
            circleSize = 60.dp,
            fontSize = 32.sp,
            thumbColor = Blue
        )
        Column (
            modifier =  Modifier
                .fillMaxWidth()
        ) {
            ConfirmButton(
                text = "SAVE",
                bgColor = if (currentWeeklyGoalValue != weeklyGoalValue) {
                    Blue
                } else {
                    Blue.copy(0.2f)
                },
                textColor = White,
                onClick = {
                    if (currentWeeklyGoalValue != weeklyGoalValue) {
                        userVM.modifyWeeklyGoal(currentWeeklyGoalValue)

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
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
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