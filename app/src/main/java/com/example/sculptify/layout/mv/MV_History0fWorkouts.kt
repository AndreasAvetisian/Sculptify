package com.example.sculptify.layout.mv

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Dark_Gray

@Composable
fun MV_HistoryOfWorkouts() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.675.dp, bottom = 20.dp)
    ) {
        Card (
            colors = CardDefaults.cardColors(Dark_Gray),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.history_of_workouts),
                    contentDescription = "",
                    tint = Blue,
                    modifier = Modifier
                        .size(20.dp)
                )
                CustomText(
                    text = "History of Workouts",
                    fontSize = 18.sp,
                    color = Blue,
                    modifier = Modifier
                        .padding(start = 5.dp, top = 2.dp)
                )
            }
        }
    }
}