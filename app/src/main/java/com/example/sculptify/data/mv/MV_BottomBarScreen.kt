package com.example.sculptify.data.mv

import com.example.sculptify.R
import com.example.sculptify.screens.Screen

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val widthDistribution: Float
) {

    // for home
    data object Main: BottomBarScreen(
        route = Screen.Main.route,
        title = "Main",
        icon = R.drawable.history_of_workouts,
        widthDistribution = 0.33f
    )

    // for report
    data object Statistics: BottomBarScreen(
        route = Screen.Statistics.route,
        title = "Statistics",
        icon = R.drawable.statistics_icon,
        widthDistribution = 0.5f
    )

    // for report
    data object Achievements: BottomBarScreen(
        route = Screen.Achievements.route,
        title = "Achievements",
        icon = R.drawable.achievements_icon,
        widthDistribution = 1f
    )
}