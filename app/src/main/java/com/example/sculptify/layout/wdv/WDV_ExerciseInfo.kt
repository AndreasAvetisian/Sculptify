package com.example.sculptify.layout.wdv

import android.util.Log
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
import com.example.sculptify.ui.theme.Advanced_Red
import com.example.sculptify.ui.theme.Beginner_Green
import com.example.sculptify.ui.theme.Intermediate_Orange
import com.example.sculptify.ui.theme.Transparent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WDV_ExerciseInfo(
    scope: CoroutineScope,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    exercise: Map<String, String>,
    workoutLevel: String
) {
    val instructions = exercise["instructions"]
        ?.replace(";", ",")
        ?.replace(":", ".\n \n")

    val focusArea = exercise["focusArea"].toString()
    val listOfFocusAreas = convertToList(focusArea)

    val duration = exercise["duration"]
    val repetitions = exercise["repetitions"]

    val value = if (duration?.isNotEmpty() == true) {
        "$duration seconds"
    } else {
        "$repetitions repetitions"
    }

    val estCalBurned = exercise["estCalBurned"].toString()

    Log.d("AAAAAAAAAAAAAAAAAAAAAAaaa", "$duration $repetitions")

    val mainColor = when(workoutLevel) {
        "Beginner" -> Beginner_Green
        "Intermediate" -> Intermediate_Orange
        "Advanced" -> Advanced_Red
        else -> Transparent
    }

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
                color = mainColor
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
                text = "ESTIMATED CALORIES BURNED",
                color = mainColor
            )
            CustomText(
                text = "$estCalBurned kcal (per $value)",
                fontSize = 18.sp
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CustomText(
                text = "FOCUS AREA",
                color = mainColor
            )
            WDV_EI_FocusAreaItems(
                list = listOfFocusAreas,
                indicatorColor = mainColor
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
            bgColor = mainColor,
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