package com.example.sculptify.layout.wv.cs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.data.workout.CompletedWorkoutInfo
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.pages.formatTime
import com.example.sculptify.screens.Screen
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.viewModels.UserViewModel
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
@Composable
fun WV_CompleteScreen(
    navController: NavHostController,
    focusArea: String,
    level: String,
    exerciseAmount: Int,
    estimatedCaloriesBurned: Int,
    estimatedTimeInSeconds: Int,
    durationInSeconds: Long
) {
    val userVM: UserViewModel = viewModel()

    val dateAndTime = remember { SimpleDateFormat("h:mma;MMM d;yyyy").format(Date()) }

    val estCalBurned by remember { mutableIntStateOf(estimatedCaloriesBurned) }
    val estTimeInSec by remember { mutableIntStateOf(estimatedTimeInSeconds) }
    val finalDuration by remember { mutableLongStateOf(durationInSeconds) }

    val caloriesBurned = "%.1f".format(estCalBurned.toFloat() * (finalDuration/estTimeInSec.toFloat())).toFloat()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(
            text = "WORKOUT \n\nCOMPLETED!",
            textAlign = TextAlign.Center,
            fontSize = 50.sp,
            modifier = Modifier
                .padding(bottom = 20.dp)
        )
        CustomText(
            text = "$focusArea - $level".uppercase(),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(bottom = 70.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.675.dp)
        ) {
            WV_CS_Card(
                modifier = Modifier.weight(1f),
                value = exerciseAmount.toString(),
                title = "Exercises",
            )
            WV_CS_Card(
                modifier = Modifier.weight(1f),
                value = caloriesBurned.toString(),
                title = "Calories",
            )
            WV_CS_Card(
                modifier = Modifier.weight(1f),
                value = formatTime(finalDuration),
                title = "Duration",
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConfirmButton(
            text = "FINISH",
            bgColor = Blue,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = {
                val workoutInfo = CompletedWorkoutInfo(dateAndTime, focusArea, level, caloriesBurned, finalDuration)
                userVM.finishWorkout(workoutInfo)
                navController.navigate(Screen.Main.route)
            }
        )
    }
}