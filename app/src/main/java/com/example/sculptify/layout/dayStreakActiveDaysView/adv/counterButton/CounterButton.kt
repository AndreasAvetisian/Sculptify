package com.example.sculptify.layout.dayStreakActiveDaysView.adv.counterButton

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CounterButton(
    value: String,
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .width(200.dp)
            .height(60.dp)
    ) {
        val thumbOffsetX = remember { Animatable(0f) }
        val thumbOffsetY = remember { Animatable(0f) }

        ButtonContainer(
            thumbOffsetX = thumbOffsetX.value,
            thumbOffsetY = thumbOffsetY.value,
            onValueDecreaseClick = onValueDecreaseClick,
            onValueIncreaseClick = onValueIncreaseClick,
            modifier = Modifier
        )

        DraggableThumbButton(
            value = value,
            thumbOffsetX = thumbOffsetX,
            thumbOffsetY = thumbOffsetY,
            onValueDecreaseClick = onValueDecreaseClick,
            onValueIncreaseClick = onValueIncreaseClick,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}