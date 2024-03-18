package com.example.sculptify.data.user

import com.example.sculptify.data.mf.FavoriteWorkout
import com.example.sculptify.data.settings.general.reminder.Reminder
import java.security.Timestamp

data class User(
    val firstName: String = "",
    val isAdmin: Boolean = false,
    val cbs: Int = 15, // Countdown Before Start
    val rbe: Int = 30, // Rest Before Exercises
    val dayStreak: Int = 0,
    val weeklyGoal: Int = 3,
    val gender: String = "",
    val height: Int = 0,
    val weight: Float = 0f,
    val yearOfBirth: Int = 0,
    val reminders: List<Reminder> = emptyList(),
    val userWorkoutStat: List<Map<Int, Timestamp>> = emptyList(),
    val favoriteList: List<FavoriteWorkout> = emptyList(),
    val pbs: Int = 0, // Personal Best Streak
    val historyOfWorkouts: List<Map<String, Any>> = emptyList()
)