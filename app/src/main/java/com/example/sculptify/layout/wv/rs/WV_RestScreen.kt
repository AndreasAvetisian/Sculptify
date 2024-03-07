package com.example.sculptify.layout.wv.rs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sculptify.layout.wv.rs.layout.WV_RS_BottomSheet
import com.example.sculptify.layout.wv.rs.layout.WV_RS_TimerAndAction
import com.example.sculptify.pages.formatTime
import com.example.sculptify.ui.theme.Blue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun WV_RestScreen(
    initialRestingTimeSeconds: Int,
    onSkipClick: () -> Unit,
    onExerciseDescriptionClick: () -> Unit,
    nextExerciseIndex: Int,
    exerciseAmount: Int,
    exerciseTitle: String,
    exerciseValue: String
) {
    var remainingTime by remember { mutableIntStateOf(initialRestingTimeSeconds) }
    val formattedTime = remember { mutableStateOf(formatTime(remainingTime.toLong())) }

    var countdownJob: Job? by remember { mutableStateOf(null) }

    LaunchedEffect(remainingTime) {
        countdownJob = countdown(remainingTime) { newTime ->
            remainingTime = newTime
            formattedTime.value = formatTime(newTime.toLong())

            if (remainingTime == 0) {
                onSkipClick()
            }
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Blue),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WV_RS_TimerAndAction(
            countdown = formattedTime.value,
            onAddMoreTimeClick = {
                remainingTime += 20
            },
            onSkipClick = {
                onSkipClick()
            }
        )
        WV_RS_BottomSheet(
            nextExerciseIndex = nextExerciseIndex,
            exerciseAmount = exerciseAmount,
            onExerciseDescriptionClick = {
                onExerciseDescriptionClick()
            },
            nextExerciseTitle = exerciseTitle,
            nextExerciseValue = exerciseValue
        )
    }
}

//private fun animateTimeChange(
//    targetTime: Int,
//    remainingTime: MutableState<Int>,
//    formattedTime: MutableState<String>
//) {
//    val increment = 1
//
//    CoroutineScope(Dispatchers.Default).launch {
//        var newTime = remainingTime.value
//        while (newTime < targetTime) {
//            delay(50) // Adjust speed as needed
//            newTime += increment
//            if (newTime > targetTime) newTime = targetTime // Ensure it doesn't overshoot
//            formattedTime.value = formatTime(newTime.toLong())
//        }
//        remainingTime.value = newTime
//    }
//}

private suspend fun animateTimeChange(
    targetTime: Int,
    remainingTime: Int,
    formattedTime: MutableState<String>
) {
    val timeDifference = targetTime - remainingTime

    val duration = abs(timeDifference) * 50L

    val startTime = System.currentTimeMillis()
    val endTime = startTime + duration

    while (System.currentTimeMillis() < endTime) {
        val elapsed = System.currentTimeMillis() - startTime
        val fraction = elapsed.toFloat() / duration.toFloat()
        val newTime = remainingTime + (timeDifference * fraction).toInt()

        formattedTime.value = formatTime(newTime.toLong())

        delay(16)
    }

    formattedTime.value = formatTime(targetTime.toLong())
}

fun CoroutineScope.countdown(
    value: Int,
    onUpdate: (Int) -> Unit
): Job {
    return launch {
        var count = value
        while (count >= 0) {
            onUpdate(count)
            count--
            delay(1000)
        }
    }
}