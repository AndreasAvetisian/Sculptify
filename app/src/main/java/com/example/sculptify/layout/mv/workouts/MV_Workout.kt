package com.example.sculptify.layout.mv.workouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Dark_Gray

@Composable
fun MV_Workout(
    title: String,
    titleColor: Color,
    difficultyColor: Color,
    workoutsList: Map<String, Any>,
    padding: Dp
) {
    Card (
        colors = CardDefaults.cardColors(Dark_Gray),
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
                    .background(difficultyColor)
                    .padding(15.675.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                CustomText(
                    text = "5 Workouts",
                    fontSize = 14.sp,
                    color = titleColor
                )
                CustomText(
                    text = title,
                    fontSize = 24.sp,
                    color = titleColor
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.675.dp, 15.675.dp, 15.675.dp, 0.dp)
            ) {
                workoutsList.forEach { (_, workoutDetails) ->
                    val workoutDetailsMap = workoutDetails as? Map<String, Any>
                    val focusArea = workoutDetailsMap?.get("focusArea") as? String
                    val level = workoutDetailsMap?.get("level") as? String
                    val time = workoutDetailsMap?.get("time") as? String

                    MV_WorkoutItem(
                        title = "$focusArea - $level",
                        time = time.toString(),
                        difficultyColor = difficultyColor,
                        iconID =
                            when(focusArea) {
                                "Chest" -> R.drawable.chest
                                "Abs" -> R.drawable.abs
                                "Shoulder & Back" -> R.drawable.shoulderandback
                                "Arm" -> R.drawable.arm
                                else -> R.drawable.leg
                            }
                    )
                }
            }

        }
    }
}