package com.example.sculptify.layout.general.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Red
import com.example.sculptify.ui.theme.White

@Composable
fun ConfirmDeletion(
    onClick: () -> Unit,
    text: String
) {
    val checkedState = remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Dark_Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.675.dp)
                .height(56.dp)
                .background(Dark_Gray),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomText(text = text)
                Checkbox(
                    checked = checkedState.value,
                    onCheckedChange = { checkedState.value = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Blue,
                        uncheckedColor = White,
                        checkmarkColor = White
                    )
                )
            }
            ConfirmButton(
                text = "Delete",
                bgColor = if (checkedState.value) Red else Red.copy(0.2f),
                textColor = White,
                onClick = {
                    if (checkedState.value) {
                        onClick()
                    }
                    checkedState.value = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
            )
        }
    }
}