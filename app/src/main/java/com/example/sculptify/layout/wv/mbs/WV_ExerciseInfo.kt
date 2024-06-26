package com.example.sculptify.layout.wv.mbs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.sculptify.layout.wdv.WDV_EI_FocusAreaItems
import com.example.sculptify.ui.theme.Blue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WV_ExerciseInfo(
    scope: CoroutineScope,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    workoutInstruction: String,
    exerciseFocusAreas: String,
    exerciseEstCalBurned: String,
    exerciseValue: String
) {
    val instructions = workoutInstruction
        .replace(";", ",")
        .replace(":", ".\n \n")

    val listOfFocusAreas = convertToList(exerciseFocusAreas)

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.675.dp),
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
                text = instructions,
                fontSize = 18.sp
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CustomText(
                text = "ESTIMATED CALORIES BURNED",
                color = Blue
            )
            CustomText(
                text = "$exerciseEstCalBurned kcal (per $exerciseValue)",
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
            WDV_EI_FocusAreaItems(
                list = listOfFocusAreas,
                indicatorColor = Blue
            )
        }
    }
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.675.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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

private fun convertToList(input: String): List<Map<String, String>> {
    val items = input.split(";")
    val resultList = mutableListOf<Map<String, String>>()

    for (item in items) {
        val pair = item.split(":")
        val title = pair[0]
        val dos = pair[1]
        val map = mapOf("title" to title, "dos" to dos)
        resultList.add(map)
    }

    return resultList
}