package com.example.sculptify.pages

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.wdv.WDV_TopBar
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.White

@Composable
fun WorkoutDetailsView(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val arguments = navBackStackEntry?.arguments


    val focusArea = arguments?.getString("focusArea") ?: ""
    val level = arguments?.getString("level") ?: ""
    val time = arguments?.getString("time") ?: ""
    val exercisesString = arguments?.getString("exercises") ?: ""

    val maps = exercisesString
        .removeSurrounding("[", "]") // Remove surrounding square brackets
        .split("}, ") // Split by '}, ' to separate individual maps

    val list = maps.map { mapStr ->
        // Remove '{' and '}' from the map string
        val cleanedMapStr = mapStr.trim('{', '}')

        // Split the map string by ', ' to get key-value pairs
        val keyValuePairs = cleanedMapStr.split(", ")

        // Create a map from the key-value pairs
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

    Log.d("NEW", list.toString())

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(White)
    ) {
        WDV_TopBar(navController)
        Column (
            modifier = Modifier
                .fillMaxSize()
            ) {
            Card (
                colors = CardDefaults.cardColors(Black),
                shape = RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.675.dp)
                ) {
                    CustomText(text = focusArea)
                    CustomText(text = level)
                    CustomText(text = time)
                    LazyColumn (
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(list) { exercise ->
                            ExerciseItem(exercise)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ExerciseItem(exercise: Map<String, String>) {
    val title = exercise["title"] ?: ""
    val duration = exercise["duration"] ?: ""
    val repetitions = exercise["repetitions"] ?: ""

    Row (
        modifier = Modifier
            .fillMaxWidth(),

    ) {
        Card (
            modifier = Modifier
                .size(60.dp)
        ) {}
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            CustomText(text = title, color = White)
            if (duration.isNotEmpty()) {
                CustomText(text = "00:$duration", color = White)
            }
            if (repetitions.isNotEmpty()) {
                CustomText(text = "x $repetitions", color = White)
            }
        }
    }
}