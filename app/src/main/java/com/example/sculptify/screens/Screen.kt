package com.example.sculptify.screens

sealed class Screen(val route: String) {
    data object Authentication : Screen("authentication")
    data object SignUp : Screen("new sign up")
    data object Main : Screen("main")
    data object WorkoutDetails : Screen("workoutDetail/{workoutID}/{focusArea}/{level}/{time}/{exercises}/{estCalBurned}")
    data object Workout : Screen("workout/{workoutID}/{focusArea}/{level}/{time}/{exercises}/{cbs}/{estCalBurned}")
    data object DSAD : Screen("day streak and active days")
    data object Statistics : Screen("statistics")
    data object Achievements : Screen("achievements")
    data object Me : Screen("me")
    data object MyProfile : Screen("my profile")
    data object MFMH : Screen("my favorite and my history")
    data object WorkoutSettings : Screen("workout settings")
    data object GeneralSettings : Screen("general settings")
    data object Reminder : Screen("reminder")
}