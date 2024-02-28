package com.example.sculptify.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.wv.CountdownBeforeStart
import com.example.sculptify.layout.wv.WV_CancelMenu
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.White
import com.example.sculptify.ui.theme.Workout_Gray
import com.example.sculptify.ui.theme.Workout_Timer_Gray
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WorkoutView(
    navController: NavHostController,
    isStopwatchRunning: Boolean,
    onCancelMenuClicked: () -> Unit,
    isCancelMenuOpen: Boolean,
    onResumeClicked: () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments

    val workoutID = arguments?.getString("workoutID") ?: ""
    val focusArea = arguments?.getString("focusArea") ?: ""
    val level = arguments?.getString("level") ?: ""
    val time = arguments?.getString("time") ?: ""
    val exercises = arguments?.getString("exercises") ?: ""
    val exerciseList = convertToList(exercises)
    val cbsValue = arguments?.getString("cbs")?.toInt()


    //----------------------------------------------------------------
    var count by remember { mutableIntStateOf(15) }

    var isCountdownActive by remember { mutableStateOf(true) }

    LaunchedEffect(isCountdownActive) {
        if (isCountdownActive) {
            countdown(value = count) { newCount ->
                count = newCount
                if (count == 0) {
                    isCountdownActive = false
                }
            }
        }
    }

    //------------------------Stopwatch---------------------------
    var elapsedSeconds by remember { mutableLongStateOf(0L) }

    val stopwatchJob = remember { mutableStateOf<Job?>(null) }

    LaunchedEffect(Unit) {
        stopwatchJob.value = launch {
            while (true) {
                if (isStopwatchRunning) {
                    delay(1000)
                    elapsedSeconds++
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            stopwatchJob.value?.cancel()
        }
    }
    //----------------------------------------------------------------

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(15.675.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp, 10.dp, 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            repeat(exerciseList.size) {
                Card (
                    colors = CardDefaults.cardColors(Workout_Gray),
                    shape = MaterialTheme.shapes.extraLarge,
                    modifier = Modifier
                        .weight(1f)
                        .height(5.dp)
                ) {
                    // Card content
                }
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card (
                colors = CardDefaults.cardColors(
                    if (isCountdownActive) Dark_Gray.copy(0.8f) else Workout_Gray
                ),
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        if (!isCancelMenuOpen) {
                            onCancelMenuClicked()
                        }
                    },
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "",
                        tint = if (isCountdownActive) White else Black
                    )
                }
            }
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                CustomText(
                    text = "Exercise 1/${exerciseList.size}",
                    color = Black
                )
                CustomText(
                    text = formatTime(elapsedSeconds),
                    color = Workout_Timer_Gray
                )
            }
        }
    }


    if (isCountdownActive && !isCancelMenuOpen) {
        CountdownBeforeStart(
            count = count.toString(),
            list = exerciseList,
            onCancelClick = {
                onCancelMenuClicked()
            },
            onStartClick = {
                isCountdownActive = false
            }
        )
    }

    if (isCancelMenuOpen) {
        WV_CancelMenu(
            onResumeClick = {
                onResumeClicked()
            },
            onQuitClick = { navController.popBackStack() },
            percentage = 0,
            exercisesLeft = 1
        )
    }
}

suspend fun countdown(
    value: Int,
    onUpdate: (Int) -> Unit
) {
    var count = value
    while (count >= 0) {
        onUpdate(count)
        count--
        delay(1000) // Delay for 1 second
    }
}

fun formatTime(seconds: Long): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60

    return String.format("%02d:%02d", minutes, remainingSeconds)
}

private fun convertToList(string: String): List<MutableMap<String, String>> {
    val maps = string
        .removeSurrounding("[", "]")
        .split("}, ")

    val list = maps.map { mapStr ->
        val cleanedMapStr = mapStr.trim('{', '}')

        val keyValuePairs = cleanedMapStr.split(", ")

        val map = mutableMapOf<String, String>()
        for (pair in keyValuePairs) {
            val pairSplit = pair.split("=")
            if (pairSplit.size == 2) {
                val (key, value) = pairSplit
                map[key] = value
            } else {
                println("Error: Unexpected format in pair: $pair")
            }
        }
        map
    }
    return list
}

// Define the state and click handlers outside of the Composable function
@Composable
fun WorkoutScreen(navController: NavHostController) {
    var isStopwatchRunning by remember { mutableStateOf(true) }
    var isCancelMenuOpen by remember { mutableStateOf(false) }

    val onCancelMenuClicked = {
        isStopwatchRunning = false
        isCancelMenuOpen = true
    }

    val onResumeClicked = {
        isCancelMenuOpen = false
        isStopwatchRunning = true
    }

    WorkoutView(
        navController = navController,
        isStopwatchRunning = isStopwatchRunning,
        onCancelMenuClicked = onCancelMenuClicked,
        isCancelMenuOpen = isCancelMenuOpen,
        onResumeClicked = onResumeClicked
    )
}