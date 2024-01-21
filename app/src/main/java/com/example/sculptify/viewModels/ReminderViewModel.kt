package com.example.sculptify.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sculptify.data.settings.general.reminder.Reminder
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

class ReminderViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore

    var  isDialogShown by mutableStateOf(false)
        private set

    fun onAddReminderClick() {
        isDialogShown = true
    }

    fun onDismissDialog() {
        isDialogShown = false
    }

    fun addReminder(
        hourValue: Int,
        minuteValue: Int,
        amOrPm: String,
        isActive: Boolean,
        daysOfWeek: List<String>
    ) {
        val newReminder = Reminder(
            hourValue = hourValue,
            minuteValue = minuteValue,
            amOrPm = amOrPm,
            isActive = isActive,
            daysOfWeek = daysOfWeek
        )

        fAuth.currentUser?.uid?.let { userId ->
            fireStore
                .collection("users")
                .document(userId)
                .update("reminders", FieldValue.arrayUnion(newReminder))
                .addOnSuccessListener {
                    Log.d("************************", "GREAT SUCCESS")
                }
                .addOnFailureListener {
                    Log.d("************************", "INSIGNIFICANT FAILURE")
                }
        }

    }

//    fun addReminder(
//        hourValue: Int,
//        minuteValue: Int,
//        amOrPm: String,
//        daysOfWeek: List<String>
//    ) {
//        val newReminder = mapOf(
//            "hourValue" to hourValue,
//            "minuteValue" to minuteValue,
//            "amOrPm" to amOrPm,
//            "isActive" to true,  // Use the default value
//            "daysOfWeek" to daysOfWeek
//        )
//
//        fAuth.currentUser?.uid?.let { userId ->
//            // Fetch the current list of reminders from Firestore
//            fireStore
//                .collection("users")
//                .document(userId)
//                .get()
//                .addOnSuccessListener { document ->
//                    val currentReminders = document.get("reminders") as? List<Map<String, Any>> ?: emptyList()
//
//                    // Add the new reminder to the list and sort the list
//                    val updatedReminders = (currentReminders + newReminder)
//                        .sortedBy { reminder ->
//                            val hour = reminder["hourValue"] as? Int ?: 0
//                            val minute = reminder["minuteValue"] as? Int ?: 0
//                            val amOrPm = reminder["amOrPm"] as? String ?: "AM"
//
//                            // Convert time to a comparable value, e.g., minutes since midnight
//                            val adjustedHour = if (amOrPm == "AM") {
//                                hour
//                            } else {
//                                hour + 12
//                            }
//
//                            adjustedHour * 60 + minute
//                        }
//
//                    // Update the sorted list in Firestore
//                    fireStore
//                        .collection("users")
//                        .document(userId)
//                        .update("reminders", FieldValue.arrayUnion(updatedReminders))
//                        .addOnSuccessListener {
//                            Log.d("************************", "GREAT SUCCESS")
//                        }
//                        .addOnFailureListener {
//                            Log.d("************************", "INSIGNIFICANT FAILURE")
//                        }
//                }
//        }
//    }
}