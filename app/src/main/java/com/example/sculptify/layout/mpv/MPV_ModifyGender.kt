package com.example.sculptify.layout.mpv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sculptify.layout.general.customText.CustomText

@Composable
fun MPV_ModifyGender(
    onClickM: () -> Unit,
    onClickF: () -> Unit,
    onClickO: () -> Unit,
    selectedM: Boolean,
    selectedF: Boolean,
    selectedO: Boolean,
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CustomText(text = "Gender")
        MPV_ModifyGenderButton(
            text = "Male",
            onClick = {
                onClickM()
            },
            selected = selectedM
        )
        MPV_ModifyGenderButton(
            text = "Female",
            onClick = {
                onClickF()
            },
            selected = selectedF
        )
        MPV_ModifyGenderButton(
            text = "Others",
            onClick = {
                onClickO()
            },
            selected = selectedO
        )
    }
}