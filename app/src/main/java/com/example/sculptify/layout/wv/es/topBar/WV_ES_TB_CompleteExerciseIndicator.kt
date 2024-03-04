package com.example.sculptify.layout.wv.es.topBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.Workout_Gray

@Composable
fun WV_ES_TB_CompleteExerciseIndicator(
    exerciseAmount: Int,
    exercisesCompleted: Int
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp, 10.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        repeat(exerciseAmount) { index ->
            val cardColor = if (index < exercisesCompleted) Black else Workout_Gray
            Card (
                colors = CardDefaults.cardColors(cardColor),
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier
                    .weight(1f)
                    .height(5.dp)
            ) {}
        }
    }
}