package com.example.sculptify.data.meMBS

import androidx.compose.ui.graphics.Color
import com.example.sculptify.R
import com.example.sculptify.main.GENERAL_SETTINGS_ROUTE
import com.example.sculptify.main.MY_FAVORITE_MY_HISTORY_ROUTE
import com.example.sculptify.main.MY_PROFILE_ROUTE
import com.example.sculptify.main.WORKOUT_SETTINGS_ROUTE
import com.example.sculptify.ui.theme.GS_Orange
import com.example.sculptify.ui.theme.MF_TomatoRed
import com.example.sculptify.ui.theme.MH_Purple
import com.example.sculptify.ui.theme.MP_SkyBlue
import com.example.sculptify.ui.theme.WS_Green

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
        bgColor = MP_SkyBlue
    )

    data object MyFavorite: MeMBSButton(
        route = MY_FAVORITE_MY_HISTORY_ROUTE,
        text = "My Favorite",
        icon = R.drawable.favorite,
        bgColor = MF_TomatoRed
    )

    data object MyHistory: MeMBSButton(
        route = MY_FAVORITE_MY_HISTORY_ROUTE,
        text = "My History",
        icon = R.drawable.history_of_workouts,
        bgColor = MH_Purple
    )

    data object WorkoutSettings: MeMBSButton(
        route = WORKOUT_SETTINGS_ROUTE,
        text = "Workout Settings",
        icon = R.drawable.workout_settings,
        bgColor = WS_Green
    )

    data object GeneralSettings: MeMBSButton(
        route = GENERAL_SETTINGS_ROUTE,
        text = "General Settings",
        icon = R.drawable.general_settings,
        bgColor = GS_Orange
    )
}