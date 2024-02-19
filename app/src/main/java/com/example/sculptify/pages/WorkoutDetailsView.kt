package com.example.sculptify.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.layout.mbs.MBS
import com.example.sculptify.layout.wdv.WDV_Description
import com.example.sculptify.layout.wdv.WDV_Divider
import com.example.sculptify.layout.wdv.WDV_ExerciseItem
import com.example.sculptify.layout.wdv.WDV_ShowAllExercises
import com.example.sculptify.layout.wdv.WDV_StartButton
import com.example.sculptify.ui.theme.Advanced_Red
import com.example.sculptify.ui.theme.Beginner_Green
import com.example.sculptify.ui.theme.Intermediate_Orange
import com.example.sculptify.ui.theme.Transparent
import com.example.sculptify.viewModels.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutDetailsView(
    navController: NavHostController
) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val userData by userVM.userdata.collectAsState()

    val favoriteList = userData["favoriteList"] as? List<Map<String, String>> ?: emptyList()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments


    val workoutID = arguments?.getString("workoutID") ?: ""
    val focusArea = arguments?.getString("focusArea") ?: ""
    val level = arguments?.getString("level") ?: ""
    val time = arguments?.getString("time") ?: ""
    val exercises = arguments?.getString("exercises") ?: ""
    val exerciseList = convertToList(exercises)

    var exerciseMapForMBS by remember { mutableStateOf<Map<String, String>?>(null) }

    var showAllItems by remember { mutableStateOf(false) }

    var isWorkoutOnTheList by remember {
        mutableStateOf(favoriteList.any { map ->
            map["workoutID"] == workoutID
        })
    }

    LaunchedEffect(favoriteList) {
        isWorkoutOnTheList = favoriteList.any { map ->
            map["workoutID"] == workoutID
        }
    }

    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    // Callback function to be passed to MeMBS
    val onBottomSheetDismiss: () -> Unit = {
        showBottomSheet = false
    }

    val mainColor = when(level) {
        "Beginner" -> Beginner_Green
        "Intermediate" -> Intermediate_Orange
        "Advanced" -> Advanced_Red
        else -> Transparent
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBarView(
            title = focusArea,
            navController = navController,
            withTwoButtons = true,
            onFavClick = {
                userVM.updateFavoriteList(
                    workoutID = workoutID,
                    focusArea = focusArea,
                    level = level,
                    time = time,
                    exercises = exercises
                )
            },
            isClicked = isWorkoutOnTheList
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
                val exerciseMap = exercise as Map<String, String>
                WDV_ExerciseItem(
                    exercise = exerciseMap,
                    onClick = { clickedExercise ->
                        showBottomSheet = true
                        // Set the clicked exercise map as the data for the MBS
                        exerciseMapForMBS = clickedExercise
                    }
                )
            }
           
            if (!showAllItems && exerciseList.size > 4) {
                item {
                    WDV_ShowAllExercises(
                        onClick = { showAllItems = true },
                        color = mainColor
                    )
                }
            }
        }
        WDV_Divider()
        WDV_StartButton(
            onClick = {},
            bgColor = mainColor
        )
    }
    if (showBottomSheet) {
        MBS(
            sheetState = sheetState,
            scope = scope,
            onDismiss = onBottomSheetDismiss,
            navController = navController,
            data = exerciseMapForMBS!!,
            workoutLevel = level
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