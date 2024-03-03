package com.example.sculptify.pages

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sculptify.layout.mbs.MBS
import com.example.sculptify.layout.wv.WV_CBS
import com.example.sculptify.layout.wv.WV_CancelMenu
import com.example.sculptify.layout.wv.WV_ExerciseScreen
import com.example.sculptify.layout.wv.WV_RestScreen
import com.example.sculptify.viewModels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutView(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments

    val workoutID = arguments?.getString("workoutID") ?: ""
    val focusArea = arguments?.getString("focusArea") ?: ""
    val level = arguments?.getString("level") ?: ""
    val time = arguments?.getString("time") ?: ""
    val exercises = arguments?.getString("exercises") ?: ""
    val exerciseList = convertToList(exercises)

    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val cbsValue = userVM.userdata.collectAsState().value["cbs"].toString().toIntOrNull()

    var isCancelMenuOpen by remember { mutableStateOf(false) }

    //-------------------------------MBS-------------------------------

    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val onBottomSheetDismiss: () -> Unit = {
        showBottomSheet = false
    }

    //------------------------Countdown Before Start------------------------
    var count by remember { mutableIntStateOf(cbsValue ?: 15) }

    LaunchedEffect(cbsValue) {
        if (cbsValue != null) {
            count = cbsValue
        }
    }

    var isCountdownActive by remember { mutableStateOf(true) }

    LaunchedEffect(isCountdownActive && !showBottomSheet) {
        if (isCountdownActive && !showBottomSheet) {
            countdown(value = count) { newCount ->
                count = newCount
                if (count == 0) {
                    isCountdownActive = false
                }
            }
        }
    }

    //------------------------Stopwatch----------------------------------------
    var isStopwatchRunning by remember { mutableStateOf(true) }

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

    fun startStopwatch() {
        stopwatchJob.value = CoroutineScope(Dispatchers.Default).launch {
            while (true) {
                if (isStopwatchRunning) {
                    delay(1000)
                    elapsedSeconds++
                }
            }
        }
    }
    //----------------------------------------------------------------------

    var exerciseIndex by remember { mutableIntStateOf(0) }
    val nextExerciseIndex = exerciseIndex + 1

    val exerciseAmount = exerciseList.size

    var isExerciseOn by remember { mutableStateOf(true) }

    val exerciseTitle = exerciseList[exerciseIndex]["title"].toString()
    val exerciseInstruction = exerciseList[exerciseIndex]["instructions"].toString()
    val exerciseFocusAreas = exerciseList[exerciseIndex]["focusArea"].toString()
    val exerciseDuration = exerciseList[exerciseIndex]["duration"] ?: ""
    val exerciseRepetitions = exerciseList[exerciseIndex]["repetitions"] ?: ""

    val exerciseValue = if (exerciseDuration.isNotEmpty()) {
        "00:$exerciseDuration"
    } else {
        "x $exerciseRepetitions"
    }

    //----------------Completed Exercises Indicator-------------------------

    var exercisesCompleted by remember { mutableIntStateOf(0) }
    val exercisesLeft = exerciseAmount - exercisesCompleted

    val percentageAsDecimal: Float = if (exercisesLeft != 0) {
        (exercisesCompleted.toFloat() / exerciseAmount.toFloat()) * 100
    } else {
        0f
    }

    val roundedPercentage = String.format("%.2f", percentageAsDecimal)

    val percentageAsInt = roundedPercentage.toFloat().toInt()

    //----------------------------------------------------------------------

    // Code for resting time here

    //----------------------------------------------------------------------

    if (isExerciseOn) {
        WV_ExerciseScreen(
            exerciseIndex = exerciseIndex,
            exerciseAmount = exerciseAmount,
            exercisesCompleted = exercisesCompleted,
            isCountdownActive = isCountdownActive,
            isCancelMenuOpen = isCancelMenuOpen,
            onCancelMenuClick = {
                isStopwatchRunning = false
                isCancelMenuOpen = true
                stopwatchJob.value?.cancel()
            },
            elapsedSeconds = elapsedSeconds,
            exerciseTitle = exerciseTitle,
            exerciseValue = exerciseValue,
            onCompleteClick = {
                if (exercisesCompleted != exerciseAmount) {
                    exerciseIndex++
                    exercisesCompleted++
                    isExerciseOn = false
                }
            },
            onExerciseDescriptionClick = { showBottomSheet = true },
        )
    } else {
        WV_RestScreen(
            initialRestingTimeSeconds = 30,
            onSkipClick = { isExerciseOn = true },
            onExerciseDescriptionClick = { showBottomSheet = true },
            nextExerciseIndex = nextExerciseIndex,
            exerciseAmount = exerciseAmount,
            exerciseTitle = exerciseTitle,
            exerciseValue = exerciseValue
        )
    }

    if (isCountdownActive) {
        WV_CBS(
            count = count.toString(),
            list = exerciseList,
            onExerciseDescriptionClick = { showBottomSheet = true },
            onStartClick = {
                isCountdownActive = false
            }
        )
    }

    if (isCancelMenuOpen) {
        WV_CancelMenu(
            onResumeClick = {
                isCancelMenuOpen = false
                isStopwatchRunning = true
                startStopwatch()
            },
            onQuitClick = { navController.popBackStack() },
            percentage = percentageAsInt,
            exercisesLeft = exercisesLeft
        )
    }

    if (showBottomSheet) {
        MBS(
            sheetState = sheetState,
            scope = scope,
            onDismiss = onBottomSheetDismiss,
            navController = navController,
            workoutInstruction = exerciseInstruction,
            exerciseFocusAreas = exerciseFocusAreas
        )
    }
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

fun CoroutineScope.countdown(
    value: Int,
    onUpdate: (Int) -> Unit
): Job {
    return launch {
        var count = value
        while (count >= 0) {
            onUpdate(count)
            count--
            delay(1000) // Delay for 1 second
        }
    }
}

fun formatTime(seconds: Long): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60

    return String.format("%02d:%02d", minutes, remainingSeconds)
}