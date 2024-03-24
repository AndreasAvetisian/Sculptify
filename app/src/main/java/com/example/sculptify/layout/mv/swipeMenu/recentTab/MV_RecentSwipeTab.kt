package com.example.sculptify.layout.mv.swipeMenu.recentTab

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.mv.swipeMenu.MV_SwipeMenuTabItems
import com.example.sculptify.layout.myFavMyHisView.selectedTabIndexForMFMH
import com.example.sculptify.screens.Screen
import com.example.sculptify.ui.theme.Light_Gray
import com.example.sculptify.viewModels.UserViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.math.abs

@Composable
fun MV_RecentSwipeTab(navController: NavHostController) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val userData by userVM.userdata.collectAsState()

    val historyOfWorkouts = userData["historyOfWorkouts"] as? List<Map<String, Any>> ?: emptyList()

    Column (
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        CustomText(
            text = MV_SwipeMenuTabItems[0].title.uppercase(Locale.ROOT),
            fontSize = 26.sp
        )
        CustomText(
            text = MV_SwipeMenuTabItems[0].description,
            fontSize = 14.sp,
            color = Light_Gray
        )
    }
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(historyOfWorkouts.reversed().take(2)) { workout ->
                val workoutMap = workout.toMap()
                val workoutFocusArea = workoutMap["focusArea"].toString()
                val workoutLevel = workoutMap["level"].toString()
                val workoutDate = workoutMap["date"].toString()

                val dayOfMonth = workoutDate.split(";")[1]

                //--------------------------------------------------------------------------
                val inputFormat = SimpleDateFormat("h:mma;MMM dd;yyyy", Locale.ENGLISH)
                val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

                val date = inputFormat.parse(workoutDate)
                val formattedDate = date?.let { outputFormat.format(it) }

                val formatter = DateTimeFormatter.ISO_LOCAL_DATE

                val today = LocalDate.now()

                val lastDate = LocalDate.parse(formattedDate, formatter)

                val difference = abs(today.toEpochDay() - lastDate.toEpochDay())

                val time by remember { mutableStateOf(
                    if (today.toString() == formattedDate.toString()) {
                        "- Today"
                    } else if (difference == 1L) {
                        "- Yesterday"
                    } else {
                        ""
                    }
                ) }

                MV_RT_Item(
                    onClick = {
                        selectedTabIndexForMFMH = 1
                        navController.navigate(Screen.MFMH.route)
                    },
                    focusArea = workoutFocusArea,
                    level = workoutLevel,
                    date = "$dayOfMonth $time"
                )
            }
        }
    }
}