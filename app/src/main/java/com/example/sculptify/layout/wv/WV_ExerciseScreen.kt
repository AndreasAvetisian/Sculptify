package com.example.sculptify.layout.wv

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.pages.formatTime
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.White
import com.example.sculptify.ui.theme.Workout_Gray
import com.example.sculptify.ui.theme.Workout_Timer_Gray
import java.util.Locale

@Composable
fun WV_ExerciseScreen(
    exerciseIndex: Int,
    exerciseAmount: Int,
    isCountdownActive: Boolean,
    isCancelMenuOpen: Boolean,
    onCancelMenuClick: () -> Unit,
    elapsedSeconds: Long,
    exerciseTitle: String,
    exerciseValue: String,
    onCompleteClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.675.dp, 15.675.dp, 15.675.dp, 0.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 0.dp, 10.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                repeat(exerciseAmount) {
                    Card (
                        colors = CardDefaults.cardColors(Workout_Gray),
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier
                            .weight(1f)
                            .height(5.dp)
                    ) {
                        // Card content
                    }
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (isCountdownActive) Arrangement.End else Arrangement.SpaceBetween
            ) {
                if (!isCountdownActive) {
                    Card (
                        colors = CardDefaults.cardColors(
                            Workout_Gray
                        ),
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                if (!isCancelMenuOpen) {
                                    onCancelMenuClick()
                                }
                            },
                    ) {
                        Column (
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Rounded.Close,
                                contentDescription = "",
                                tint = if (isCountdownActive) White else Black
                            )
                        }
                    }
                }
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    CustomText(
                        text = "Exercise ${exerciseIndex + 1}/$exerciseAmount",
                        color = Black
                    )
                    CustomText(
                        text = formatTime(elapsedSeconds),
                        color = Workout_Timer_Gray
                    )
                }
            }
        }
        if (!isCountdownActive && !isCancelMenuOpen) {
            Card (
                modifier = Modifier
                    .size(300.dp)
            ) {
                // Image here
            }
            Card (
                colors = CardDefaults.cardColors(Black),
                shape = RoundedCornerShape(
                    topStart = 40.dp,
                    topEnd = 40.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.675.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CustomText(
                            text = exerciseTitle.uppercase(Locale.ROOT),
                            fontSize = if (exerciseTitle.length < 24) 30.sp else 20.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(end = 5.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.question_circle),
                            contentDescription = "",
                            tint = Workout_Timer_Gray,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    CustomText(
                        text = exerciseValue,
                        fontSize = 70.sp
                    )
                    ConfirmButton(
                        bgColor = Blue,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        onClick = { onCompleteClick() },
                        withText = false
                    )
                }
            }
        }
    }
}