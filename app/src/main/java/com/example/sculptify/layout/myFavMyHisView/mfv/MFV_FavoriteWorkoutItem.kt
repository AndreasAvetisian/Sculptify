package com.example.sculptify.layout.myFavMyHisView.mfv

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Advanced_Red
import com.example.sculptify.ui.theme.Beginner_Green
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Intermediate_Orange
import com.example.sculptify.ui.theme.MF_TomatoRed
import com.example.sculptify.ui.theme.White

@Composable
fun MFV_FavoriteWorkoutItem(
    onClick: () -> Unit,
    workoutID: String,
    focusArea: String,
    level: String,
    onTheList: Boolean,
    onHeartClick: () -> Unit
) {
    val bgColor = when(level) {
        "Beginner" -> Beginner_Green
        "Intermediate" -> Intermediate_Orange
        else -> Advanced_Red
    }

    val iconID = when (focusArea) {
        "Chest" -> R.drawable.chest
        "Abs" -> R.drawable.abs
        "Shoulder & Back" -> R.drawable.shoulderandback
        "Arm" -> R.drawable.arm
        else -> R.drawable.leg
    }

    Card (
        colors = CardDefaults.cardColors(Dark_Gray),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .clickable {
                onClick()
            }
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.675.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.675.dp)
            ) {
                Card (
                    colors = CardDefaults.cardColors(bgColor),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .size(60.dp)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(iconID),
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize(0.7f)
                        )
                    }
                }
                CustomText(
                    text = if (workoutID == "shoulderAndBackIntermediate") {
                        "$focusArea\n$level"
                    } else "$focusArea $level",
                    fontSize = 18.sp
                )
            }
            Icon(
                Icons.Outlined.Favorite,
                modifier = Modifier
                    .width(25.dp)
                    .height(25.dp)
                    .clickable {
                        onHeartClick()
                    },
                contentDescription = "arrow",
                tint = if (onTheList) MF_TomatoRed else White
            )
        }
    }
}