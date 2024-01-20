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
}