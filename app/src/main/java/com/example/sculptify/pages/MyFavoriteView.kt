package com.example.sculptify.pages

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.layout.myFavMyHisView.mfv.MFV_FavoriteWorkoutItem
import com.example.sculptify.layout.settings.general.reminder.EmptyListIcon
import com.example.sculptify.screens.Screen
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun MyFavoriteView(navController: NavHostController,) {
    val userVM: UserViewModel = viewModel()

    val userData by userVM.userdata.collectAsState()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val favoriteList = userData["favoriteList"] as? List<Map<String, String>> ?: emptyList()
    val workoutIDs: List<String> = favoriteList.mapNotNull { it["workoutID"] }

    val heightAnimation = remember { Animatable(1f) }
    LaunchedEffect(true) {
        heightAnimation.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 500,
                easing = LinearEasing
            )
        )
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(heightAnimation.value))
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (favoriteList.isNotEmpty()) {
                itemsIndexed(favoriteList.reversed()) { _, item ->
                    val itemMap = item as? Map<String, Any>
                    val itemID = itemMap?.get("workoutID") as? String
                    val itemFocusArea = itemMap?.get("focusArea") as? String
                    val itemLevel = itemMap?.get("level") as? String
                    val itemTime = itemMap?.get("time") as? String
                    val itemExercises = itemMap?.get("exercises") as? String

                    val isWorkoutOnTheList = workoutIDs.contains(itemID)

                    MFV_FavoriteWorkoutItem(
                        onClick = {
                            navController.navigate(
                                "workout/${itemID}/${itemFocusArea}/${itemLevel}/${itemTime}/${itemExercises}"
                            )
                        },
                        workoutID = itemID.toString(),
                        focusArea = itemFocusArea.toString(),
                        level = itemLevel.toString(),
                        onTheList = isWorkoutOnTheList,
                        onHeartClick = {
                            userVM.updateFavoriteList(
                                workoutID = itemID as String,
                                focusArea = itemFocusArea as String,
                                level = itemLevel as String,
                                time = itemTime as String,
                                exercises = itemExercises as String
                            )
                        }
                    )
                }
            } else {
                item {
                    EmptyListIcon(
                        route = Screen.MFMH.route
                    )
                }
            }
        }
    }
}