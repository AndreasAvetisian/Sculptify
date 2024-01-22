package com.example.sculptify.data.settings.general.reminder

data class Reminder(
    val hourValue: Int,
    val minuteValue: Int,
    val amOrPm: String,
    val active: Boolean = true,
    val daysOfWeek: List<String>
)