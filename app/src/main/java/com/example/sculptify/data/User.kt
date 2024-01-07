package com.example.sculptify.data

data class User(
    val firstName: String = "",
    val isAdmin: Boolean = false,
    val cbs: Int = 15,
    val rbe: Int = 30,
    val dayStreak: Int = 0,
    val weeklyGoal: Int = 3,
    val gender: String = "",
    val height: Int = 0,
    val weight: Float = 0f,
    val yearOfBirth: Int = 0
)