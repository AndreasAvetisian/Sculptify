package com.example.sculptify.layout.mv.workouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Light_Gray
import com.example.sculptify.ui.theme.Medium_Gray
import com.example.sculptify.ui.theme.White

@Composable
fun MV_WorkoutItem(
    title: String,
    time: String,
    difficultyColor: Color,
    iconID: Int,
    onClick: () -> Unit
    ) {
    Card (
        colors = CardDefaults.cardColors(Medium_Gray),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.675.dp)
            .clickable {
                onClick()
            }
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Card (
                    colors = CardDefaults.cardColors(Dark_Gray),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .size(80.dp)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = iconID),
                            contentDescription = "",
                            tint = difficultyColor,
                            modifier = Modifier
                                .size(60.dp)
                        )
                    }
                }
                Column (
                    modifier = Modifier
                        .height(80.dp)
                        .padding(start = 15.675.dp)
                        .fillMaxWidth(0.8f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    CustomText(
                        text = title,
                        fontSize = 18.sp,
                        color = White
                    )
                    CustomText(
                        text = time,
                        fontSize = 16.sp,
                        color = Light_Gray
                    )
                }
            }
            Icon(
                painter = painterResource(id = R.drawable.arrow_right_circle),
                contentDescription = "",
                tint = difficultyColor,
                modifier = Modifier
                    .size(25.dp)
            )
        }
    }
}