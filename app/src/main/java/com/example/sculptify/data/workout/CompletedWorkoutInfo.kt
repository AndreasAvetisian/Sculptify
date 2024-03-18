package com.example.sculptify.data.workout

data class CompletedWorkoutInfo(
    val dateAndTime: String,
    val focusArea: String,
    val level: String,
    val caloriesBurned: Float,
    val finalDuration: Long
)