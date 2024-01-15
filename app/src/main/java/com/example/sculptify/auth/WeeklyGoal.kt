package com.example.sculptify.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.ui.theme.balooFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeeklyGoal() {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(15.675.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.92f)
                    .padding(15.675.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "How often would you like to work out?",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Icon(
                    painter = painterResource(
                        id = when (regWeeklyGoal) {
                            1f -> R.drawable.weekly_goal_1
                            2f -> R.drawable.weekly_goal_2
                            3f -> R.drawable.weekly_goal_3
                            4f -> R.drawable.weekly_goal_4
                            else -> R.drawable.weekly_goal_1
                        }
                    ),
                    contentDescription = "",
                    tint = when (regWeeklyGoal) {
                        1f -> Color(0xff0000FF)
                        2f -> Color(0xffFFFF00)
                        3f -> Color(0xffFFA500)
                        4f -> Color(0xffFF0000)
                        else -> Color.Blue
                    },
                    modifier = Modifier
                        .size(100.dp)
                )
                Text(
                    text = if (regWeeklyGoal == 1f) "time" else "times" + " / week",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Slider(
                    value = regWeeklyGoal,
                    onValueChange = { newValue ->
                        regWeeklyGoal = newValue
                    },
                    modifier = Modifier
                        .padding(top = 20.dp),
                    valueRange = 1f..4f,
                    steps = 2,
                    track = {
                        Card (
                            colors = CardDefaults.cardColors(Color(0xff0060FE).copy(alpha = 0.2f)),
                            shape = MaterialTheme.shapes.extraLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp)
                        ) {}
                    },
                    thumb = {
                        Card (
                            colors = CardDefaults.cardColors(
                                when (regWeeklyGoal) {
                                    1f -> Color(0xff0000FF)
                                    2f -> Color(0xffFFFF00)
                                    3f -> Color(0xffFFA500)
                                    4f -> Color(0xffFF0000 )
                                    else -> Color.Blue
                                }
                            ),
                            shape = MaterialTheme.shapes.extraLarge,
                            modifier = Modifier
                                .size(40.dp)
                        ) {}
                    },
                )
                Row (
                    modifier = Modifier
                        .padding(start = 10.dp, end = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Less",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "More",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}