package com.example.sculptify.layout.myFavMyHisView.mhv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sculptify.ui.theme.Dark_Gray

@Composable
fun MHV_ListItem(
    workoutFocusArea: String,
    workoutLevel: String,
    time: String,
    date: String,
    duration: String,
    kcal: String
) {
    Card (
        colors = CardDefaults.cardColors(Dark_Gray),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.675.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                MHV_LI_Icon(
                    modifier = Modifier
                        .weight(0.45f),
                    workoutLevel = workoutLevel,
                    workoutFocusArea = workoutFocusArea
                )
                MHV_LI_Card(
                    modifier = Modifier
                        .weight(1f),
                    value = workoutFocusArea,
                    title = "Focus Area"
                )
                MHV_LI_Card(
                    modifier = Modifier
                        .weight(1f),
                    value = workoutLevel,
                    title = "Level"
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                MHV_LI_Card(
                    modifier = Modifier
                        .weight(1f),
                    value = date,
                    title = time
                )
                MHV_LI_Card(
                    modifier = Modifier
                        .weight(1f),
                    value = duration,
                    title = "Duration"
                )
                MHV_LI_Card(
                    modifier = Modifier
                        .weight(1f),
                    value = kcal,
                    title = "Kcal"
                )
            }
        }
    }
}