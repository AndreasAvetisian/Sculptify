package com.example.sculptify.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.layout.wdv.WDV_Description
import com.example.sculptify.layout.wdv.WDV_Divider
import com.example.sculptify.layout.wdv.WDV_ExerciseItem
import com.example.sculptify.layout.wdv.WDV_ShowAllExercises
import com.example.sculptify.layout.wdv.WDV_StartButton

@Composable
fun WorkoutDetailsView(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments

    val focusArea = arguments?.getString("focusArea") ?: ""
    val level = arguments?.getString("level") ?: ""
    val time = arguments?.getString("time") ?: ""
    val exercises = arguments?.getString("exercises") ?: ""
    val exerciseList = convertToList(exercises)

    var showAllItems by remember { mutableStateOf(false) }

    var isFavClicked by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBarView(
            title = focusArea,
            navController = navController,
            withTwoButtons = true,
            onFavClick = { isFavClicked = !isFavClicked },
            isClicked = isFavClicked
        )
        WDV_Divider()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
                .padding(horizontal = 15.675.dp)
        ) {
            item {
                WDV_Description(
                    level = level,
                    time = time,
                    focusArea = focusArea,
                    exercises = exerciseList.size.toString()
                )
            }
            
            val exercisesCount = if (showAllItems) exerciseList.size else minOf(exerciseList.size, 4)
            items(exerciseList.take(exercisesCount)) { exercise ->
                WDV_ExerciseItem(exercise)
            }
           
            if (!showAllItems && exerciseList.size > 4) {
                item {
                    WDV_ShowAllExercises(
                        onClick = { showAllItems = true }
                    )
                }
            }
        }
        WDV_Divider()
        WDV_StartButton(
            onClick = {}
        )
    }
}

fun convertToList(string: String): List<MutableMap<String, String>> {
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