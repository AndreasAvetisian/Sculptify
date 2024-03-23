package com.example.sculptify.layout.myFavMyHisView.mhv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sculptify.R
import com.example.sculptify.ui.theme.Advanced_Red
import com.example.sculptify.ui.theme.Beginner_Green
import com.example.sculptify.ui.theme.Intermediate_Orange

@Composable
fun MHV_LI_Icon(
    modifier: Modifier,
    workoutLevel: String,
    workoutFocusArea: String
) {
    val bgColor = when(workoutLevel) {
        "Beginner" -> Beginner_Green
        "Intermediate" -> Intermediate_Orange
        else -> Advanced_Red
    }

    val iconID = when (workoutFocusArea) {
        "Chest" -> R.drawable.chest
        "Abs" -> R.drawable.abs
        "Shoulder & Back" -> R.drawable.shoulderandback
        "Arm" -> R.drawable.arm
        else -> R.drawable.leg
    }

    Card (
        colors = CardDefaults.cardColors(bgColor),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
            .size(50.dp)
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
}