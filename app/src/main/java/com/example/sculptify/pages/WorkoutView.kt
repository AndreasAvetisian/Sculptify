package com.example.sculptify.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.wv.WV_CBS
import com.example.sculptify.layout.wv.WV_CancelMenu
import com.example.sculptify.layout.wv.WV_ExerciseScreen
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.White
import com.example.sculptify.viewModels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

    //------------------------Countdown Before Start------------------------
    var count by remember { mutableIntStateOf(cbsValue ?: 15) }

    LaunchedEffect(cbsValue) {
        if (cbsValue != null) {
            count = cbsValue
        }
    }

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
    //----------------------------------------------------------------

    var exerciseIndex by remember { mutableIntStateOf(0) }
    val nextExerciseIndex = exerciseIndex + 1

    val exerciseAmount = exerciseList.size

    var isExerciseOn by remember { mutableStateOf(true) }

    val exerciseTitle = exerciseList[exerciseIndex]["title"].toString()
    val exerciseDuration = exerciseList[exerciseIndex]["duration"] ?: ""
    val exerciseRepetitions = exerciseList[exerciseIndex]["repetitions"] ?: ""

    val exerciseValue = if (exerciseDuration.isNotEmpty()) {
        "00:$exerciseDuration"
    } else {
        "x $exerciseRepetitions"
    }

    var exercisesCompleted by remember { mutableIntStateOf(0) }
    val exercisesLeft = exerciseAmount - exercisesCompleted

    val percentageAsDecimal: Float = if (exercisesLeft != 0) {
        (exercisesCompleted.toFloat() / exerciseAmount.toFloat()) * 100
    } else {
        0f
    }

    val roundedPercentage = String.format("%.2f", percentageAsDecimal)

    val percentageAsInt = roundedPercentage.toFloat().toInt()

    Log.d("AAAAAAAAAAAAAAAAAAAAAAA", "$exercisesCompleted $exerciseAmount")

    if (isExerciseOn) {
        WV_ExerciseScreen(
            exerciseIndex = exerciseIndex,
            exerciseAmount = exerciseAmount,
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
            }
        )
    } else {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Blue),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 110.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomText(
                    text = "Rest",
                    fontSize = 30.sp
                )
                CustomText(
                    text = "00:10",
                    fontSize = 70.sp
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    ConfirmButton(
                        text = "+20s",
                        bgColor = Color(0xff3483FE),
                        modifier = Modifier
                            .size(110.dp, 40.dp),
                        onClick = { /*TODO*/ }
                    )
                    Spacer(modifier = Modifier.width(30.dp))
                    ConfirmButton(
                        text = "SKIP",
                        bgColor = White,
                        textColor = Blue,
                        modifier = Modifier
                            .size(110.dp, 40.dp),
                        onClick = {
                            isExerciseOn = true
                        }
                    )
                }
            }
            Column {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.675.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    CustomText(text = "NEXT $nextExerciseIndex/$exerciseAmount")
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CustomText(text = exerciseTitle)
                        CustomText(text = exerciseValue)
                    }
                }
                Card (
                    colors = CardDefaults.cardColors(White),
                    shape = RoundedCornerShape(
                        topStart = 40.dp,
                        topEnd = 40.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(320.dp)
                ) {
                    Column (
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Card (
                            modifier = Modifier
                                .size(200.dp)
                        ) {
                            // Image here
                        }
                    }
                }
            }
        }
    }

    if (isCountdownActive) {
        WV_CBS(
            count = count.toString(),
            list = exerciseList,
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