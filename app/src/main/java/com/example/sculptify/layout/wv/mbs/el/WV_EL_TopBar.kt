package com.example.sculptify.layout.wv.mbs.el

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.wv.layout.WV_CircleButton
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Light_Gray

@Composable
fun WV_EL_TopBar(
    count: Int,
    onClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomText(text = "$count Exercises")
        WV_CircleButton(
            imageVector = Icons.Rounded.Close,
            bgColor = Dark_Gray,
            iconColor = Light_Gray,
            onClick = {
                onClick()
            }
        )
    }
}