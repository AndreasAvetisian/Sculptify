package com.example.sculptify.layout.dayStreakActiveDaysView.adv

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Light_Gray

@Composable
fun ADV_ModifyButton(
    onClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CustomText(
            text = "Weekly Goal",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Icon(
            Icons.Rounded.Edit,
            contentDescription = "",
            tint = Light_Gray,
            modifier = Modifier
                .size(16.dp)
        )
    }
}