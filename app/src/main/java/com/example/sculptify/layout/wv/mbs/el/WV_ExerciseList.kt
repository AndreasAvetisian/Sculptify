package com.example.sculptify.layout.wv.mbs.el

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.ui.theme.Blue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WV_ExerciseList(
    scope: CoroutineScope,
    sheetState: SheetState,
    onDismiss: () -> Unit,
    exerciseList: List<MutableMap<String, String>>,
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (exerciseList.isNotEmpty()) {
                items(exerciseList) { exercise ->
                    val title = exercise["title"].toString()
                    val duration = exercise["duration"]
                    val repetitions = exercise["repetitions"]
                    val exerciseValue = if (duration?.isNotEmpty() == true) {
                        if (duration.toInt() < 60) "00:$duration" else "01:00"
                    } else {
                        "x $repetitions"
                    }

                    WV_EL_Item(
                        title = title,
                        exerciseValue = exerciseValue
                    )
                }
            }
        }
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
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