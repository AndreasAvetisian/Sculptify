package com.example.sculptify.layout.wdv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Blue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WDV_ExerciseInfo(
    scope: CoroutineScope,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    exercise: Map<String, String>,
) {
    val instructions = exercise["instructions"]
        ?.replace(";", ",")
        ?.replace(":", ".\n \n")

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CustomText(
                text = "INSTRUCTIONS",
                color = Blue
            )
            CustomText(
                text = instructions.toString(),
                fontSize = 18.sp
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CustomText(
                text = "FOCUS AREA",
                color = Blue
            )
            // CODE HERE
        }
        ConfirmButton(
            text = "Close",
            bgColor = Blue,
            textColor = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onDismiss()
                    }
                }
            }
        )
    }
}