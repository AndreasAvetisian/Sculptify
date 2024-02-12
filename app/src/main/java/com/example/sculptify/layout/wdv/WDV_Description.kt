package com.example.sculptify.layout.wdv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Light_Gray

@Composable
fun WDV_Description(
    level: String,
    time: String,
    focusArea: String,
    exercises: String
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.675.dp),
        verticalArrangement = Arrangement.spacedBy(15.675.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card (
                colors = CardDefaults.cardColors(Dark_Gray),
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .width(182.dp)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.675.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    CustomText(
                        text = level,
                        fontSize = 18.sp
                    )
                    CustomText(
                        text = "Level",
                        fontSize = 18.sp,
                        color = Light_Gray
                    )
                }
            }
            Card (
                colors = CardDefaults.cardColors(Dark_Gray),
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .width(182.dp)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.675.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    CustomText(
                        text = time,
                        fontSize = 18.sp
                    )
                    CustomText(
                        text = "Time",
                        fontSize = 18.sp,
                        color = Light_Gray
                    )
                }
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card (
                colors = CardDefaults.cardColors(Dark_Gray),
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .width(182.dp)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.675.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    CustomText(
                        text = focusArea,
                        fontSize = 18.sp
                    )
                    CustomText(
                        text = "Focus Area",
                        fontSize = 18.sp,
                        color = Light_Gray
                    )
                }
            }
            Card (
                colors = CardDefaults.cardColors(Dark_Gray),
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .width(182.dp)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.675.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    CustomText(
                        text = exercises,
                        fontSize = 18.sp
                    )
                    CustomText(
                        text = "Exercises",
                        fontSize = 18.sp,
                        color = Light_Gray
                    )
                }
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CustomText(text = "Exercises:")
        }
    }
}