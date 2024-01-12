package com.example.sculptify.layout.mv.workouts

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun MV_ClassicWorkouts() {
    Column {
        Text(
            text = "Classic Workouts",
            fontSize = 20.sp,
            fontFamily = balooFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color(0xffffffff),
        )
        MV_Workout(padding = 10.dp)
        MV_Workout(padding = 25.dp)
        MV_Workout(padding = 25.dp)
    }
}