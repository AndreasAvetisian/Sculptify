package com.example.sculptify.data.meMBS

import androidx.compose.ui.graphics.Color
import com.example.sculptify.R
import com.example.sculptify.main.GENERAL_SETTINGS_ROUTE
import com.example.sculptify.main.MY_FAVORITE_MY_HISTORY_ROUTE
import com.example.sculptify.main.MY_PROFILE_ROUTE
import com.example.sculptify.main.WORKOUT_SETTINGS_ROUTE

sealed class MeMBSButton(
    val route: String,
    val text: String,
    val icon: Int,
    val bgColor: Color
) {
    data object MyProfile: MeMBSButton(
        route = MY_PROFILE_ROUTE,
        text = "My Profile",
        icon = R.drawable.smile,
        bgColor = Color(0xff4D67AC)
    )

    data object MyFavorite: MeMBSButton(
        route = MY_FAVORITE_MY_HISTORY_ROUTE,
        text = "My Favorite",
        icon = R.drawable.favorite,
        bgColor = Color(0xffF54336)
    )

    data object MyHistory: MeMBSButton(
        route = MY_FAVORITE_MY_HISTORY_ROUTE,
        text = "My History",
        icon = R.drawable.history_of_workouts,
        bgColor = Color(0xFFAD16E4)
    )

    data object WorkoutSettings: MeMBSButton(
        route = WORKOUT_SETTINGS_ROUTE,
        text = "Workout Settings",
        icon = R.drawable.workout_settings,
        bgColor = Color(0xff49714A)
    )

    data object GeneralSettings: MeMBSButton(
        route = GENERAL_SETTINGS_ROUTE,
        text = "General Settings",
        icon = R.drawable.general_settings,
        bgColor = Color(0xffFF8E00)
    )
}