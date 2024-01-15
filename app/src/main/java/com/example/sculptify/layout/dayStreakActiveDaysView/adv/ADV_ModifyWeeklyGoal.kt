package com.example.sculptify.layout.dayStreakActiveDaysView.adv

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.R
import com.example.sculptify.layout.ConfirmButton
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

    var isButtonEnabled by remember{ mutableIntStateOf(0) }

    var isPageRefreshed by remember { mutableStateOf(false) }


    val spanStyle = SpanStyle(
        fontSize = 14.sp,
        fontFamily = balooFontFamily,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        textDecoration = TextDecoration.None
    )

    val text = buildAnnotatedString {
        withStyle(style = spanStyle) {
            append("Keep ")
        }
        withStyle(style = spanStyle.copy(
            color = when (currentWeeklyGoalValue) {
                1 -> Color(0xff0000FF)
                2 -> Color(0xff0000FF)
                3 -> Color(0xffFFA500)
                4 -> Color(0xffFFA500)
                else -> Color(0xffFF0000)
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
            .background(Color(0xff1C1C1E)) // 0xff1C1C1E
            .height(250.dp)
            .padding(15.675.dp, 15.675.dp, 15.675.dp, 39.675.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Weekly active days",
                fontSize = 20.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xffffffff)
            )
            Text(
                text = text
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ADV_CircleButton(
                onClick = {
                    isButtonEnabled--
                    if (currentWeeklyGoalValue > 1) currentWeeklyGoalValue--
                },
                iconId = R.drawable.minus,
                bgColor = if (currentWeeklyGoalValue != 1) {
                    Color(0xff0060FE)
                } else {
                    Color(0xff0060FE).copy(0.2f)
                }
            )
            Text(
                text = currentWeeklyGoalValue.toString() + if (currentWeeklyGoalValue == 1) " day" else " days",
                fontSize = 22.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xffffffff)
            )
            ADV_CircleButton(
                onClick = {
                    isButtonEnabled++
                    if (currentWeeklyGoalValue < 7) currentWeeklyGoalValue++
                },
                iconId = R.drawable.plus,
                bgColor = if (currentWeeklyGoalValue != 7) {
                    Color(0xff0060FE)
                } else {
                    Color(0xff0060FE).copy(0.2f)
                }
            )
        }
        Column (
            modifier =  Modifier
                .fillMaxWidth()
        ) {
            ConfirmButton(
                text = "SAVE",
                bgColor = if (isButtonEnabled != 0) Color(0xff0060FE) else Color(0xff0060FE).copy(0.2f),
                textColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                        if (isButtonEnabled != 0) {
                            userVM.modifyWeeklyGoal(currentWeeklyGoalValue)
                            isButtonEnabled = 0

                            isPageRefreshed = true

                            scope
                                .launch {
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
    }

    if (isPageRefreshed) {
        LaunchedEffect(true) {
            userVM.getUserData()
        }
        isPageRefreshed = false
    }
}