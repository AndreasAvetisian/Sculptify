package com.example.sculptify.layout.settings.general.reminder.timePicker

import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.sculptify.data.settings.general.reminder.timePicker.PickerStyle
import com.example.sculptify.ui.theme.Blue
import kotlin.math.abs
import kotlin.math.roundToInt

@Composable
fun <T>DraggablePicker(
    modifier: Modifier = Modifier,
    list: List<T>,
    showAmount: Int = 10,
    style: PickerStyle = PickerStyle(),
    onValueChanged: (T) -> Unit,
    fontSize: TextUnit = 36.sp,
    pickerHeight: Float = 200f,
    textPadding: Float = 25f
) {

    val listCount by remember {
        mutableIntStateOf(list.size)
    }

    val correctionValue by remember {
        if (list.size % 2 == 0) {
            mutableIntStateOf(1)
        } else {
            mutableIntStateOf(0)
        }
    }

    var dragStartedX by remember {
        mutableFloatStateOf(0f)
    }

    var currentDragX by remember {
        mutableFloatStateOf(0f)
    }

    var oldX by remember {
        mutableFloatStateOf(0f)
    }

    Canvas(
        modifier = modifier
            .pointerInput(true) {
                detectDragGestures(
                    onDragStart = { offset ->
                        dragStartedX = offset.x
                    },
                    onDragEnd = {
                        val spacePerItem = size.width/showAmount
                        val rest = currentDragX % spacePerItem

                        val roundUp = abs(rest / spacePerItem).roundToInt() == 1
                        val newX = if (roundUp) {
                            if (rest < 0) {
                                currentDragX + abs(rest) - spacePerItem
                            } else {
                                currentDragX - rest + spacePerItem
                            }
                        } else {
                            if (rest < 0) {
                                currentDragX + abs(rest)
                            } else {
                                currentDragX - rest
                            }
                        }

                        currentDragX = newX.coerceIn(
                            minimumValue = -(listCount / 2f) * spacePerItem,
                            maximumValue = (listCount / 2f - correctionValue) * spacePerItem
                        )
                        val index = (listCount / 2) + (currentDragX / spacePerItem).toInt()
                        onValueChanged(list[index])
                        oldX = currentDragX
                    },
                    onDrag = { change, _ ->
                        val changeX = change.position.x
                        val newX = oldX + (dragStartedX - changeX)
                        val spacePerItem = size.width/showAmount
                        currentDragX = newX.coerceIn(
                            minimumValue = -(listCount / 2f) * spacePerItem,
                            maximumValue = (listCount / 2f - correctionValue) * spacePerItem
                        )
                        val index = (listCount / 2) + (currentDragX / spacePerItem).toInt()
                        onValueChanged(list[index])
                    }
                )
            }
    ) {

        val top = 0f

        drawContext.canvas.nativeCanvas.apply {
            drawRect(
                Rect(1500, top.toInt(), size.width.toInt() - 1500, pickerHeight.toInt()),
                Paint().apply {
                    color = Blue.copy(0.2f).toArgb()
                }
            )
        }
        val spaceForEachItem = size.width/showAmount
        for (i in 0 until listCount) {
            val currentX = i * spaceForEachItem - currentDragX -
                    ((listCount - 1 + correctionValue - showAmount)/2 * spaceForEachItem)

            drawContext.canvas.nativeCanvas.apply {
                val y = textPadding + fontSize.toPx()

                drawText(
                    list[i].toString(),
                    currentX,
                    y,
                    Paint().apply {
                        textSize = fontSize.toPx()
                        textAlign = Paint.Align.CENTER
                        isFakeBoldText = true
                        color = style.textColor.toArgb()
                    }
                )
            }
        }
    }
}