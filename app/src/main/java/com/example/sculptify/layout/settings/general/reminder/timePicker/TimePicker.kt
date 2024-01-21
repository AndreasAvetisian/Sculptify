package com.example.sculptify.layout.settings.general.reminder.timePicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TimePicker(
    hoursList: List<Int>,
    onHoursChanged: (Int) -> Unit,
    minutesList: List<Int>,
    onMinutesChanged: (Int) -> Unit,
    amOrPmList: List<String>,
    onAmOrPmChanged: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .background(Color(0xff1C1C1E))
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DraggablePicker(
                list = hoursList,
                onValueChanged = onHoursChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                showAmount = 6
            )
            DraggablePicker(
                list = minutesList.map { it.toString().padStart(2, '0') },
                onValueChanged = { onMinutesChanged(it.toInt()) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                showAmount = 6
            )
            DraggablePicker(
                list = amOrPmList,
                onValueChanged = onAmOrPmChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                showAmount = 4
            )
        }
        FocusRectangle(
            modifier = Modifier
                .height(230.dp)
                .width(60.dp)
        )
    }
}

