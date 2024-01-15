package com.example.sculptify.layout.dayStreakActiveDaysView.adv.counterButton

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconControlButton(
    iconId: Int,
    rotateAngle: Float,
    contentDescription: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    tintColor: Color = Color.White,
    clickTintColor: Color = Color.White,
    enabled: Boolean = true
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    IconButton(
        onClick = onClick,
        interactionSource = interactionSource,
        enabled = enabled,
        modifier = modifier
            .size(40.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = contentDescription,
            tint = if (isPressed) clickTintColor else tintColor,
            modifier = Modifier
                .size(40.dp)
                .rotate(rotateAngle)
        )
    }
}
