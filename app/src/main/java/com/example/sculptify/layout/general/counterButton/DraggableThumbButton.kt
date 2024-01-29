package com.example.sculptify.layout.general.counterButton

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.sculptify.enumClasses.DragDirection
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.sign

private const val DRAG_LIMIT_HORIZONTAL_DP = 72
private const val START_DRAG_THRESHOLD_DP = 2
private const val DRAG_LIMIT_HORIZONTAL_THRESHOLD_FACTOR = 0.9f
private const val COUNTER_DELAY_INITIAL_MS = 500L
private const val COUNTER_DELAY_FAST_MS = 100L

@Composable
fun DraggableThumbButton(
    value: String,
    thumbOffsetX: Animatable<Float, AnimationVector1D>,
    thumbOffsetY: Animatable<Float, AnimationVector1D>,
    onValueDecreaseClick: () -> Unit,
    onValueIncreaseClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp,
    fontSize: TextUnit,
    thumbColor: Color
) {
    val dragLimitHorizontalPx = DRAG_LIMIT_HORIZONTAL_DP.dp.dpToPx()
    val startDragThreshold = START_DRAG_THRESHOLD_DP.dp.dpToPx()
    val scope = rememberCoroutineScope()

    val dragDirection = remember {
        mutableStateOf(DragDirection.NONE)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .offset {
                IntOffset(
                    thumbOffsetX.value.toInt(),
                    thumbOffsetY.value.toInt(),
                )
            }
            .shadow(8.dp, shape = CircleShape)
            .size(size)
            .clip(CircleShape)
            .background(thumbColor)
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        awaitFirstDown()

                        // reset drag direction
                        dragDirection.value = DragDirection.NONE

                        var counterJob: Job? = null

                        do {
                            val event = awaitPointerEvent()
                            event.changes.forEach { pointerInputChange ->
                                scope.launch {
                                    if ((dragDirection.value == DragDirection.NONE &&
                                                pointerInputChange.positionChange().x.absoluteValue >= startDragThreshold) ||
                                        dragDirection.value == DragDirection.HORIZONTAL
                                    ) {
                                        // in case of the initial drag
                                        if (dragDirection.value == DragDirection.NONE) {
                                            counterJob = scope.launch {
                                                delay(COUNTER_DELAY_INITIAL_MS)

                                                var elapsed = COUNTER_DELAY_INITIAL_MS
                                                while (isActive && thumbOffsetX.value.absoluteValue >= (dragLimitHorizontalPx * DRAG_LIMIT_HORIZONTAL_THRESHOLD_FACTOR)) {
                                                    if (thumbOffsetX.value.sign > 0) {
                                                        onValueIncreaseClick()
                                                    } else {
                                                        onValueDecreaseClick()
                                                    }

                                                    delay(COUNTER_DELAY_FAST_MS)
                                                    elapsed += COUNTER_DELAY_FAST_MS
                                                }
                                            }
                                        }

                                        // mark horizontal dragging direction to prevent vertical dragging until released
                                        dragDirection.value = DragDirection.HORIZONTAL

                                        // calculate the drag factor so the more the thumb
                                        // is closer to the border, the more effort it takes to drag it
                                        val dragFactor = 2 - (thumbOffsetX.value / dragLimitHorizontalPx).absoluteValue
                                        val delta = pointerInputChange.positionChange().x * dragFactor

                                        val targetValue = thumbOffsetX.value + delta
                                        val targetValueWithinBounds =
                                            targetValue.coerceIn(
                                                -dragLimitHorizontalPx,
                                                dragLimitHorizontalPx
                                            )

                                        thumbOffsetX.snapTo(targetValueWithinBounds)
                                    }
                                }
                            }
                        } while (event.changes.any { it.pressed })

                        counterJob?.cancel()
                    }

                    // detect drag to limit
                    if (thumbOffsetX.value.absoluteValue >= (dragLimitHorizontalPx * DRAG_LIMIT_HORIZONTAL_THRESHOLD_FACTOR)) {
                        if (thumbOffsetX.value.sign > 0) {
                            onValueIncreaseClick()
                        } else {
                            onValueDecreaseClick()
                        }
                    }

                    scope.launch {
                        if (dragDirection.value == DragDirection.HORIZONTAL && thumbOffsetX.value != 0f) {
                            thumbOffsetX.animateTo(
                                targetValue = 0f,
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            )
                        }
                    }
                }
            }
    ) {
        Text(
            text = value,
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            fontSize = fontSize
        )
    }
}