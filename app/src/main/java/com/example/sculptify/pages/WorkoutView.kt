package com.example.sculptify.pages

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.wv.CountdownBeforeStart
import com.example.sculptify.layout.wv.WV_CancelMenu
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun WorkoutView(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments

    val workoutID = arguments?.getString("workoutID") ?: ""
    val focusArea = arguments?.getString("focusArea") ?: ""
    val level = arguments?.getString("level") ?: ""
    val time = arguments?.getString("time") ?: ""
    val exercises = arguments?.getString("exercises") ?: ""
    val exerciseList = convertToList(exercises)
    val cbsValue = arguments?.getString("cbs")?.toInt()

    var count by remember { mutableIntStateOf(1355) }
    val context = LocalContext.current

    var isCountdownActive by remember { mutableStateOf(true) }

    var isCancelMenuOpen by remember { mutableStateOf(false) }

    LaunchedEffect(isCountdownActive) {
        if (isCountdownActive) {
            countdown(context = context, value = count) { newCount ->
                count = newCount
            }
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(15.675.dp)
    ) {
        CustomText(
            modifier = Modifier,
            text = "Back",
            color = Black
        )
    }


    if (isCountdownActive && !isCancelMenuOpen) {
        CountdownBeforeStart(
            count = count.toString(),
            list = exerciseList,
            onCancelClick = {
                isCancelMenuOpen = true
            },
            onStartClick = {
                isCountdownActive = false
                count = 0
            }
        )
    }

    if (isCancelMenuOpen) {
        WV_CancelMenu(
            onResumeClick = { isCancelMenuOpen = false },
            onQuitClick = { navController.popBackStack() },
            percentage = 0,
            exercisesLeft = 1
        )
    }
}

suspend fun countdown(
    context: Context,
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