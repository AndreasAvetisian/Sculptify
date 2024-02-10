package com.example.sculptify.layout.auth.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.settings.general.reminder.timePicker.DraggablePicker
import com.example.sculptify.layout.settings.general.reminder.timePicker.FocusRectangle

@Composable
fun YOB_Slider(
    yearsList: List<Int>,
    onYearsChanged: (Int) -> Unit,
) {
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(text = "Year of Birth: ")
        Box (
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            DraggablePicker(
                list = yearsList,
                onValueChanged = onYearsChanged,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                showAmount = 6,
                fontSize = 22.sp,
                textPadding = 50f
            )
            FocusRectangle(
                modifier = Modifier
                    .size(60.dp)
            )
        }
    }
}