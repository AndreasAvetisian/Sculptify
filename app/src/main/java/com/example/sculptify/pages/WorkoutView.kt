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
import com.example.sculptify.layout.wv.cs.WV_CompleteScreen
import com.example.sculptify.layout.wv.es.WV_ExerciseScreen
import com.example.sculptify.layout.wv.rs.WV_RestScreen
import com.example.sculptify.layout.wv.rs.countdown
import com.example.sculptify.layout.wv.states.WV_CBS
import com.example.sculptify.layout.wv.states.WV_CancelMenu
import com.example.sculptify.screens.Screen
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
    val estCalBurned = arguments?.getString("estCalBurned") ?: ""
    val exercises = arguments?.getString("exercises") ?: ""
    val exerciseList = convertToList(exercises)

    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val cbsValue = userVM.userdata.collectAsState().value["cbs"].toString().toIntOrNull()

    var isCancelMenuOpen by remember { mutableStateOf(false) }
    //----------------------------------------------------------------------

    var exerciseIndex by remember { mutableIntStateOf(0) }
    val nextExerciseIndex = exerciseIndex + 1

    val exerciseAmount = exerciseList.size

    var isExerciseOn by remember { mutableStateOf(true) }


    val exerciseTitle = exerciseList[exerciseIndex]["title"].toString()
    val exerciseInstruction = exerciseList[exerciseIndex]["instructions"].toString()
    val exerciseFocusAreas = exerciseList[exerciseIndex]["focusArea"].toString()
    val exerciseEstCalBurned = exerciseList[exerciseIndex]["estCalBurned"].toString()
    val exerciseDuration = exerciseList[exerciseIndex]["duration"] ?: ""
    val exerciseRepetitions = exerciseList[exerciseIndex]["repetitions"] ?: ""

    val exerciseValue = if (exerciseDuration.isNotEmpty()) {
        if (exerciseDuration.toInt() < 60) "00:$exerciseDuration" else "01:00"
    } else {
        "x $exerciseRepetitions"
    }

    val exerciseValueForMBS = if (exerciseDuration.isNotEmpty()) {
        "$exerciseDuration seconds"
    } else {
        "$exerciseRepetitions repetitions"
    }

    //-----------------------Exercise Info (MBS)----------------------------

    var showBottomSheet by remember { mutableStateOf(false) }

    val infoSheetState = rememberModalBottomSheetState()
    val infoScope = rememberCoroutineScope()

    val onInfoBottomSheetDismiss: () -> Unit = {
        showBottomSheet = false
    }

    //-----------------------Exercise List (MBS)----------------------------

    var isExerciseListOpen by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val onBottomSheetDismiss: () -> Unit = {
        isExerciseListOpen = false
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

    //----------------Completed Exercises Indicator-------------------------
    val exercisesLeft = exerciseAmount - exerciseIndex

    val percentageAsDecimal: Float = if (exercisesLeft != 0) {
        (exerciseIndex.toFloat() / exerciseAmount.toFloat()) * 100
    } else {
        0f
    }

    val roundedPercentage = String.format("%.2f", percentageAsDecimal)

    val percentageAsInt = roundedPercentage.toFloat().toInt()

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

    //--------------------------Estimated Calories Burned-------------------------

    val estimatedCaloriesBurned = when(workoutID) {
        "absBeginner" -> 256
        "armBeginner" -> 260
        "chestBeginner" -> 226
        "legBeginner" -> 375
        "shoulderAndBackBeginner" -> 222
        "absIntermediate" -> 396
        "armIntermediate" -> 382
        "chestIntermediate" -> 248
        "legIntermediate" -> 401
        "shoulderAndBackIntermediate" -> 234
        "absAdvanced" -> 406
        "armAdvanced" -> 387
        "chestAdvanced" -> 345
        "legAdvanced" -> 512
        "shoulderAndBackAdvanced" -> 295
        else -> 330
    }

    val estimatedTimeInSeconds = when(workoutID) {
        "absBeginner" -> 1080
        "armBeginner" -> 960
        "chestBeginner" -> 600
        "legBeginner" -> 1320
        "shoulderAndBackBeginner" -> 900
        "absIntermediate" -> 1500
        "armIntermediate" -> 1440
        "chestIntermediate" -> 780
        "legIntermediate" -> 2160
        "shoulderAndBackIntermediate" -> 1020
        "absAdvanced" -> 1800
        "armAdvanced" -> 1740
        "chestAdvanced" -> 960
        "legAdvanced" -> 2820
        "shoulderAndBackAdvanced" -> 1020
        else -> 1340
    }

    //----------------------------------------------------------------------------

    var isCompleteOn by remember { mutableStateOf(false) }

    val completeAction = {
        if (exerciseIndex <= exerciseAmount - 1) {
            exerciseIndex++
            isExerciseOn = false
            if  (exerciseIndex > exerciseAmount - 1) {
                exerciseIndex = 0
                isCompleteOn = true
            }
        }
    }

    if (isExerciseOn && !isCompleteOn) {
        WV_ExerciseScreen(
            exerciseIndex = exerciseIndex,
            exerciseAmount = exerciseAmount,
            exercisesCompleted = exerciseIndex,
            isCountdownActive = isCountdownActive,
            isExerciseListOpen = isExerciseListOpen,
            onExerciseListOpen = {
                isExerciseListOpen = true
            },
            isCancelMenuOpen = isCancelMenuOpen,
            showBottomSheet = showBottomSheet,
            onCancelMenuClick = {
                isStopwatchRunning = false
                isCancelMenuOpen = true
                stopwatchJob.value?.cancel()
            },
            elapsedSeconds = elapsedSeconds,
            exerciseTitle = exerciseTitle,
            exerciseDuration = exerciseDuration,
            exerciseRepetitions = exerciseRepetitions,
            onCompleteClick = {
                completeAction()
            },
            onExerciseDescriptionClick = { showBottomSheet = true },
            onExerciseFinished = {
                completeAction()
            }
        )
    } else if (!isExerciseOn && !isCompleteOn) {
        WV_RestScreen(
            initialRestingTimeSeconds = 30,
            onSkipClick = { isExerciseOn = true },
            onExerciseDescriptionClick = { showBottomSheet = true },
            nextExerciseIndex = nextExerciseIndex,
            exerciseAmount = exerciseAmount,
            exerciseTitle = exerciseTitle,
            exerciseValue = exerciseValue
        )
    } else if (!isExerciseOn){
        WV_CompleteScreen(
            focusArea = focusArea,
            level = level,
            exerciseAmount = exerciseAmount,
            estimatedCaloriesBurned = estimatedCaloriesBurned,
            estimatedTimeInSeconds = estimatedTimeInSeconds,
            durationInSeconds = elapsedSeconds,
            onFinishClick = {
                navController.navigate(Screen.Main.route)
            }
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
            sheetState = infoSheetState,
            scope = infoScope,
            onDismiss = onInfoBottomSheetDismiss,
            navController = navController,
            workoutInstruction = exerciseInstruction,
            exerciseFocusAreas = exerciseFocusAreas,
            exerciseEstCalBurned = exerciseEstCalBurned,
            exerciseValue = exerciseValueForMBS,
            view = 0
        )
    }

    if (isExerciseListOpen) {
        MBS(
            sheetState = sheetState,
            scope = scope,
            onDismiss = onBottomSheetDismiss,
            navController = navController,
            exerciseList = exerciseList,
            view = 1
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