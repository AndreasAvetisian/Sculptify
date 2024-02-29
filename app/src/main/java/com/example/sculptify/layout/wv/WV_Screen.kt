package com.example.sculptify.layout.wv

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.White
import com.example.sculptify.ui.theme.Workout_Gray
import com.example.sculptify.ui.theme.Workout_Timer_Gray
import com.example.sculptify.viewModels.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun WV_Screen(
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

    Log.d("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa", cbsValue.toString())



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

    val exerciseTitle = exerciseList[0]["title"].toString()
    val exerciseDuration = exerciseList[0]["duration"].toString()
    val exerciseRepetitions = exerciseList[0]["repetitions"].toString()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.675.dp, 15.675.dp, 15.675.dp, 0.dp)
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
                horizontalArrangement = if (isCountdownActive) Arrangement.End else Arrangement.SpaceBetween
            ) {
                if (!isCountdownActive) {
                    Card (
                        colors = CardDefaults.cardColors(
                            Workout_Gray
                        ),
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                if (!isCancelMenuOpen) {
                                    isStopwatchRunning = false
                                    isCancelMenuOpen = true
                                    stopwatchJob.value?.cancel()
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
        if (!isCountdownActive && !isCancelMenuOpen) {
            Card (
                modifier = Modifier
                    .size(300.dp)
            ) {
                // Image here
            }
            Card (
                colors = CardDefaults.cardColors(Color(0xff000000)),
                shape = RoundedCornerShape(
                    topStart = 40.dp,
                    topEnd = 40.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.65f)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.675.dp),
                    verticalArrangement = Arrangement.SpaceAround,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomText(
                        text = exerciseTitle.uppercase(Locale.ROOT),
                        fontSize = 30.sp
                    )
                    CustomText(
                        text =
                        if (exerciseDuration.isNotEmpty()) {
                            "00:${exerciseDuration}"
                        } else {
                            "x $exerciseRepetitions"
                        },
                        fontSize = 70.sp
                    )
                    ConfirmButton(
                        bgColor = Blue,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        onClick = { /*TODO*/ },
                        withText = false
                    )
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
            percentage = 0,
            exercisesLeft = 1
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