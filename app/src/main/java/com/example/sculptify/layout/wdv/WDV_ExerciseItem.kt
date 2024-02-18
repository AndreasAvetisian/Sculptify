package com.example.sculptify.layout.wdv

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import java.util.Locale

@Composable
fun WDV_ExerciseItem(
    exercise: Map<String, String>,
    onClick: (Map<String, String>) -> Unit
) {
    val title = exercise["title"] ?: ""
    val duration = exercise["duration"] ?: ""
    val repetitions = exercise["repetitions"] ?: ""

    Card (
        colors = CardDefaults.cardColors(Dark_Gray),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
            .clickable {
                onClick(exercise)
            }
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.675.dp)
        ) {
            Card (
                modifier = Modifier
                    .size(60.dp)
            ) {}
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CustomText(
                    text = title.uppercase(Locale.ROOT),
                    fontSize = 16.sp
                )
                if (duration.isNotEmpty()) {
                    CustomText(
                        text = if (duration.toInt() != 60) "00:$duration" else "01:00",
                        color = Light_Gray,
                        fontSize = 14.sp
                    )
                }
                if (repetitions.isNotEmpty()) {
                    CustomText(
                        text = "x $repetitions",
                        color = Light_Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}