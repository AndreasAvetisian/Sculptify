package com.example.sculptify.layout.settings.general.reminder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Light_Gray
import com.example.sculptify.ui.theme.MF_TomatoRed
import com.example.sculptify.ui.theme.Yellow

@Composable
fun EmptyListIcon(
    route: String
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 220.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(Dark_Gray),
            shape = CircleShape,
            modifier = Modifier
                .size(185.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(
                        id = if (route == "reminder") R.drawable.reminder_icon else R.drawable.favorite
                    ),
                    contentDescription = "",
                    tint = if (route == "reminder") Yellow else MF_TomatoRed,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
        if (route == "reminder") {
            CustomText(
                text = "Please set your reminder",
                color = Light_Gray
            )
        } else {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                CustomText(
                    text = "Tap",
                    fontSize = 18.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.favorite),
                    contentDescription = "",
                    tint = MF_TomatoRed
                )
                CustomText(
                    text = " to add your favorite \n workouts here.",
                    fontSize = 18.sp
                )
            }
        }
    }
}