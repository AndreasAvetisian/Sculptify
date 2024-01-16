package com.example.sculptify.layout.general.counterButton

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.sculptify.R
import kotlin.math.absoluteValue

private const val ICON_BUTTON_ALPHA_INITIAL = 0.3f
private const val CONTAINER_BACKGROUND_ALPHA_INITIAL = 0.6f
private const val CONTAINER_BACKGROUND_ALPHA_MAX = 0.7f
private const val DRAG_HORIZONTAL_ICON_HIGHLIGHT_LIMIT_DP = 36
private const val CONTAINER_OFFSET_FACTOR = 0.1f

@Composable
fun ButtonContainer(
    thumbOffsetX: Float,
    thumbOffsetY: Float,
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    modifier: Modifier = Modifier,
    clearButtonVisible: Boolean = false,
) {
    // at which point the icon should be fully visible
    val horizontalHighlightLimitPx = DRAG_HORIZONTAL_ICON_HIGHLIGHT_LIMIT_DP.dp.dpToPx()

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .offset {
                IntOffset(
                    (thumbOffsetX * CONTAINER_OFFSET_FACTOR).toInt(),
                    (thumbOffsetY * CONTAINER_OFFSET_FACTOR).toInt(),
                )
            }
            .fillMaxSize()
            .clip(RoundedCornerShape(64.dp))
            .background(
                Color.Black.copy(
                    alpha = if (thumbOffsetX.absoluteValue > 0.0f) {
                        // horizontal
                        (CONTAINER_BACKGROUND_ALPHA_INITIAL + ((thumbOffsetX.absoluteValue / horizontalHighlightLimitPx) / 20f))
                            .coerceAtMost(CONTAINER_BACKGROUND_ALPHA_MAX)
                    }  else {
                        CONTAINER_BACKGROUND_ALPHA_INITIAL
                    }
                )
            )
            .padding(horizontal = 8.dp)
    ) {
        // decrease button
        IconControlButton(
            iconId = R.drawable.minus,
            rotateAngle = 0f,
            contentDescription = "Decrease count",
            onClick = onValueDecreaseClick,
            enabled = !clearButtonVisible,
            tintColor = Color.White.copy(
                alpha = if (clearButtonVisible) {
                    0.0f
                } else if (thumbOffsetX < 0) {
                    (thumbOffsetX.absoluteValue / horizontalHighlightLimitPx).coerceIn(
                        ICON_BUTTON_ALPHA_INITIAL,
                        1f
                    )
                } else {
                    ICON_BUTTON_ALPHA_INITIAL
                }
            )
        )

        // increase button
        IconControlButton(
            iconId = R.drawable.plus,
            rotateAngle = 0f,
            contentDescription = "Increase count",
            onClick = onValueIncreaseClick,
            enabled = !clearButtonVisible,
            tintColor = Color.White.copy(
                alpha = if (clearButtonVisible) {
                    0.0f
                } else if (thumbOffsetX > 0) {
                    (thumbOffsetX.absoluteValue / horizontalHighlightLimitPx).coerceIn(
                        ICON_BUTTON_ALPHA_INITIAL,
                        1f
                    )
                } else {
                    ICON_BUTTON_ALPHA_INITIAL
                }
            )
        )
    }
}