package com.example.sculptify.data.settings.general.reminder

import java.security.Timestamp
import java.util.UUID

data class Reminder(
    val id: String? = UUID.randomUUID().toString(),
    val hourValue: Int,
    val minuteValue: Int,
    val amOrPm: String,
    val active: Boolean = true,
    val daysOfWeek: List<String>,
    val createdAt: Timestamp? = null,
    val modifiedAt: Timestamp? = null
) {
    fun toMap(): Map<String, Any> {
        val map = mutableMapOf<String, Any>()
        map["id"] = id ?: ""
        map["hourValue"] = hourValue
        map["minuteValue"] = minuteValue
        map["amOrPm"] = amOrPm
        map["active"] = active
        map["daysOfWeek"] = daysOfWeek

        return map
    }
}