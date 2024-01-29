package com.example.sculptify.layout.mv.workouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.general.customText.CustomText

@Composable
fun MV_WorkoutItem() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.675.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Column (
                modifier = Modifier
                    .size(70.dp)
                    .background(Color.White)
            ) {
                // Picture
            }
            Column (
                modifier = Modifier
                    .height(70.dp)
                    .padding(start = 15.675.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                CustomText(
                    text = "Abs - Beginner",
                    fontSize = 18.sp
                )
                CustomText(
                    text = "18 mins",
                    fontSize = 16.sp
                )
            }
        }
        Row {
            Icon(
                painter = painterResource(id = R.drawable.arrow_right_circle),
                contentDescription = "",
                tint = Color(0xff262626)
            )
        }
    }
}