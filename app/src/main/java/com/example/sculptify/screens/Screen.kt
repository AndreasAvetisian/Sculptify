package com.example.sculptify.screens

sealed class Screen(val route: String) {
    data object Authentication : Screen("authentication")
    data object SignUp : Screen("sign up")
    data object EmailAndPassword : Screen("email and password")
    data object NameAndYOB : Screen("name and year of birth")
    data object HeightAndWeight : Screen("height and weight")
    data object GenderSelection : Screen("gender selection")
    data object WeeklyGoal : Screen("weekly goal")
    data object ConfirmRegistration : Screen("confirm registration")
    data object Main : Screen("main")
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