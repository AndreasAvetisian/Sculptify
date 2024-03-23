package com.example.sculptify.layout.myFavMyHisView.mhv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Advanced_Red
import com.example.sculptify.ui.theme.Beginner_Green
import com.example.sculptify.ui.theme.DS_Gray
import com.example.sculptify.ui.theme.Gray
import com.example.sculptify.ui.theme.Intermediate_Orange

@Composable
fun MHV_LI_Card(
    modifier: Modifier,
    value: String,
    title: String
) {
    val color = when(value) {
        "Beginner" -> Beginner_Green
        "Intermediate" -> Intermediate_Orange
        "Advanced" -> Advanced_Red
        else -> Gray
    }

    Card (
        colors = CardDefaults.cardColors(DS_Gray),
        shape = MaterialTheme.shapes.medium,
        modifier = modifier
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomText(
                text = value,
                fontSize = 16.sp
            )
            CustomText(
                text = title,
                fontSize = 16.sp,
                color = color
            )
        }
    }
}