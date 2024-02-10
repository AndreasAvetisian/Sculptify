package com.example.sculptify.layout.general.counterButton

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.sculptify.ui.theme.Black

@Composable
fun CounterButton(
    value: String,
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    modifier: Modifier = Modifier,
    width: Dp,
    height: Dp,
    circleSize: Dp,
    fontSize: TextUnit,
    thumbColor: Color,
    containerColor: Color = Black,
    dragHorizontalLimit: Int = 72,
    iconSize: Dp = 40.dp
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(width = width, height = height)
    ) {
        val thumbOffsetX = remember { Animatable(0f) }
        val thumbOffsetY = remember { Animatable(0f) }

        ButtonContainer(
            thumbOffsetX = thumbOffsetX.value,
            thumbOffsetY = thumbOffsetY.value,
            onValueDecreaseClick = onValueDecreaseClick,
            onValueIncreaseClick = onValueIncreaseClick,
            modifier = Modifier,
            bgColor = containerColor,
            iconSize = iconSize
        )

        DraggableThumbButton(
            value = value,
            thumbOffsetX = thumbOffsetX,
            thumbOffsetY = thumbOffsetY,
            onValueDecreaseClick = onValueDecreaseClick,
            onValueIncreaseClick = onValueIncreaseClick,
            modifier = Modifier.align(Alignment.Center),
            size = circleSize,
            fontSize = fontSize,
            thumbColor = thumbColor,
            dragHorizontalLimit = dragHorizontalLimit
        )
    }
}