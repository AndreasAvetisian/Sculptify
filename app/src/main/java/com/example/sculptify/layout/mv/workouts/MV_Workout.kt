package com.example.sculptify.layout.mv.workouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun MV_Workout(
    padding: Dp
) {
    Card (
        colors = CardDefaults.cardColors(Color(0xff1C1C1E)),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = padding)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xff0060FE))
                    .padding(15.675.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "5 Workouts",
                    fontSize = 14.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xffffffff)
                )
                Text(
                    text = "Beginner",
                    fontSize = 24.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xffffffff)
                )
            }
            MV_WorkoutItem()
            Divider(thickness = 1.dp, color = Color(0xff909090), modifier = Modifier.padding(start = 85.675.dp))
            MV_WorkoutItem()
            Divider(thickness = 1.dp, color = Color(0xff909090), modifier = Modifier.padding(start = 85.675.dp))
            MV_WorkoutItem()
            Divider(thickness = 1.dp, color = Color(0xff909090), modifier = Modifier.padding(start = 85.675.dp))
            MV_WorkoutItem()
            Divider(thickness = 1.dp, color = Color(0xff909090), modifier = Modifier.padding(start = 85.675.dp))
            MV_WorkoutItem()
        }
    }
}