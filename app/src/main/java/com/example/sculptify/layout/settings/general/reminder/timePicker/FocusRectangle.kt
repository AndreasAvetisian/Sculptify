package com.example.sculptify.layout.settings.general.reminder.timePicker

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import com.example.sculptify.ui.theme.Blue

@Composable
fun FocusRectangle(
    modifier: Modifier = Modifier
) {
    Canvas(
        modifier = modifier
    ) {
        drawContext.canvas.nativeCanvas.apply {
            drawRoundRect(
                size = Size(size.width, size.height),
                style = Stroke(width = 20f, join = StrokeJoin.Round),
                color = Blue,
                cornerRadius = CornerRadius(25f, 25f)
            )
        }
    }
}