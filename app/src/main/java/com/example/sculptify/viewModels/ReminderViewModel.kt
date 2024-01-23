package com.example.sculptify.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class ReminderViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore

    var isCreateDialogShown by mutableStateOf(false)
        private set

    var isEditDialogShown by mutableStateOf(false)
        private set

    var editingReminderIndex by mutableStateOf(-1)

    fun onAddReminderClick() {
        isCreateDialogShown = true
        isEditDialogShown = false
    }

    fun onEditReminderClick(index: Int) {
        editingReminderIndex = index
        isCreateDialogShown = false
        isEditDialogShown = true
    }

    fun onDismissDialog() {
        isCreateDialogShown = false
        isEditDialogShown = false
    }

    fun addReminder(
        hourValue: Int,
        minuteValue: Int,
        amOrPm: String,
        daysOfWeek: List<String>
    ) {
        val newReminder = mapOf(
            "hourValue" to hourValue,
            "minuteValue" to minuteValue,
            "amOrPm" to amOrPm,
            "active" to true,
            "daysOfWeek" to daysOfWeek
        )

        fAuth.currentUser?.uid?.let { userId ->
            // Fetch the current list of reminders from Firestore
            fireStore
                .collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener { document ->
                    val currentReminders = document.get("reminders") as? List<Map<String, Any>> ?: emptyList()

                    val updatedReminders = currentReminders.toMutableList()
                    updatedReminders.add(newReminder)

                    updatedReminders.sortBy { reminder ->
                        val hour = reminder["hourValue"].toString().toInt()
                        val minute = reminder["minuteValue"].toString().toInt()
                        val amOrPm = reminder["amOrPm"].toString()

                        // minutes since midnight
                        val convertedValue = when (amOrPm) {
                            "AM" -> hour * 60 + minute
                            else -> (hour + 12) * 60 + minute
                        }

                        convertedValue
                    }

                    fireStore
                        .collection("users")
                        .document(userId)
                        .update("reminders", updatedReminders)
                        .addOnSuccessListener {
                            Log.d("************************", "GREAT SUCCESS")
                        }
                        .addOnFailureListener {
                            Log.d("************************", "INSIGNIFICANT FAILURE")
                        }
                }
        }
    }

    fun changeReminderState(
        reminderIndex: Int,
        state: Boolean
    ) {
        val userDocument = fireStore.collection("users").document(fAuth.currentUser!!.uid)

        userDocument.get()
            .addOnSuccessListener { documentSnapshot ->
                val reminders = documentSnapshot.get("reminders") as? List<Map<String, Any>> ?: emptyList()

                if (reminderIndex in reminders.indices) {
                    val reminderItem = reminders[reminderIndex] as MutableMap<String, Any>
                    reminderItem["active"] = state
                }

                userDocument.update("reminders", reminders)
                    .addOnSuccessListener {
                        Log.d("************************", "Reminder state modified")
                    }
                    .addOnFailureListener {
                        Log.d("************************", "Error with modifying reminder state")
                    }
            }
            .addOnFailureListener {
                Log.d("************************", "Failed to fetch document")
            }
    }

    fun deleteReminder(reminderIndex: Int) {
        fAuth.currentUser?.uid?.let { userId ->
            val userDocumentRef = fireStore.collection("users").document(userId)

            // Get the current reminders array
            userDocumentRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    val currentReminders = documentSnapshot.get("reminders") as? List<Map<String, Any>> ?: emptyList()

                    // Remove the reminder at the specified index
                    if (currentReminders.isNotEmpty() && reminderIndex >= 0 && reminderIndex < currentReminders.size) {
                        val modifiedReminders = ArrayList(currentReminders)
                        modifiedReminders.removeAt(reminderIndex)

                        // Update the document with the modified reminders array
                        userDocumentRef.update("reminders", modifiedReminders)
                            .addOnSuccessListener {
                                Log.d("************************", "Reminder deleted")
                            }
                            .addOnFailureListener {
                                Log.d("************************", "Error with deleting reminder")
                            }
                    } else {
                        Log.d("************************", "Invalid index or empty reminders array")
                    }
                }
                .addOnFailureListener {
                    Log.d("************************", "Failed to fetch reminders")
                }
        }
    }
}