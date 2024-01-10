package com.example.sculptify.layout.MP

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun MP_ModifyGender(
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
            .height(56.dp)
            .padding(end = 15.675.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Gender",
            fontSize = 20.sp,
            fontFamily = balooFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color(0xffffffff)
        )
        MP_ModifyGenderButton(
            text = "Male",
            onClick = {
                onClickM()
            },
            selected = selectedM
        )
        MP_ModifyGenderButton(
            text = "Female",
            onClick = {
                onClickF()
            },
            selected = selectedF
        )
        MP_ModifyGenderButton(
            text = "Others",
            onClick = {
                onClickO()
            },
            selected = selectedO
        )
    }
}