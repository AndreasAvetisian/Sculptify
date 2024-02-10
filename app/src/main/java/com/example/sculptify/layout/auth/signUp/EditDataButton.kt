package com.example.sculptify.layout.auth.signUp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.pages.auth.isEditClicked
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.White

@Composable
fun EditDataButton(
    text: String,
    onClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .clickable {
                onClick()
                isEditClicked = true
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomText(
            text = text,
            fontSize = 24.sp
        )
        Icon(
            Icons.Rounded.Edit,
            contentDescription = "",
            tint = White
        )
    }
}