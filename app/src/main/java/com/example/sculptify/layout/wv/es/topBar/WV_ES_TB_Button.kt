package com.example.sculptify.layout.wv.es.topBar

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.Workout_Gray

@Composable
fun WV_ES_TB_Button(
    imageVector: ImageVector,
    isButtonClicked: Boolean,
    onClick: () -> Unit
) {
    Card (
        colors = CardDefaults.cardColors(
            Workout_Gray
        ),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .size(40.dp)
            .clickable {
                if (!isButtonClicked) {
                    onClick()
                }
            },
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector,
                contentDescription = "",
                tint = Black
            )
        }
    }
}