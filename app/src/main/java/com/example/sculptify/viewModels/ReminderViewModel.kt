package com.example.sculptify.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class ReminderViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore
    var editingReminderIndex by mutableIntStateOf(-1)

    companion object {
        private const val addReminder_TAG = "AddReminder"
        private const val modifyReminder_TAG = "ModifyReminder"
        private const val changeReminderState_TAG = "ChangeReminderState"
        private const val deleteReminder_TAG = "DeleteReminder"
    }

    var remindersList by mutableStateOf<List<Map<String, Any>>>(emptyList())
        private set

    var isCreateDialogShown by mutableStateOf(false)
        private set

    var isEditDialogShown by mutableStateOf(false)
        private set

    var doesReminderAlreadyExist by mutableStateOf(false)

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
        amOrPmValue: String,
        daysOfWeek: List<String>
    ) {
        val newReminder = mapOf(
            "hourValue" to hourValue,
            "minuteValue" to minuteValue,
            "amOrPm" to amOrPmValue,
            "active" to true,
            "daysOfWeek" to daysOfWeek
        )

        fAuth.currentUser?.uid?.let { userId ->
            fireStore
                .collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener { document ->
                    val currentReminders = document.get("reminders") as? List<Map<String, Any>> ?: emptyList()

                    val doesReminderExist = currentReminders.any { existingReminder ->
                        val existingHour = existingReminder["hourValue"].toString().toInt()
                        val existingMinute = existingReminder["minuteValue"].toString().toInt()
                        val existingAmOrPm = existingReminder["amOrPm"].toString()
                        val existingDaysOfWeek = existingReminder["daysOfWeek"] as List<String>

                        existingHour == hourValue &&
                        existingMinute == minuteValue &&
                        existingAmOrPm == amOrPmValue &&
                        existingDaysOfWeek == daysOfWeek
                    }

                    doesReminderAlreadyExist = doesReminderExist

                    if (!doesReminderExist) {
                        val updatedReminders = currentReminders.toMutableList()
                        updatedReminders.add(newReminder)

                        updatedReminders.sortBy { reminder ->
                            val hour = reminder["hourValue"].toString().toInt()
                            val minute = reminder["minuteValue"].toString().toInt()

                            // minutes since midnight
                            val convertedValue = when (reminder["amOrPm"].toString()) {
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
                                Log.d(addReminder_TAG, "Reminder added successfully.")
                                remindersList = updatedReminders
                            }
                            .addOnFailureListener {
                                Log.d(addReminder_TAG, "Failed to add reminder.")
                            }
                    }
                }
        }
    }

    fun modifyReminder(
        reminderIndex: Int,
        hourValue: Int,
        minuteValue: Int,
        amOrPmValue: String,
        daysOfWeek: List<String>
    ) {
        fAuth.currentUser?.uid?.let { userId ->
            fireStore
                .collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener { document ->
                    val currentReminders = document.get("reminders") as? List<Map<String, Any>> ?: emptyList()

                    if (reminderIndex in currentReminders.indices) {
                        val updatedReminders = currentReminders.toMutableList()
                        val modifiedReminder = updatedReminders[reminderIndex].toMutableMap().apply {
                            this["hourValue"] = hourValue
                            this["minuteValue"] = minuteValue
                            this["amOrPm"] = amOrPmValue
                            this["daysOfWeek"] = daysOfWeek
                        }

                        updatedReminders[reminderIndex] = modifiedReminder

                        updatedReminders.sortBy { reminder ->
                            val hour = reminder["hourValue"].toString().toInt()
                            val minute = reminder["minuteValue"].toString().toInt()

                            // minutes since midnight
                            val convertedValue = when (reminder["amOrPm"].toString()) {
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
                                Log.d(modifyReminder_TAG, "Reminder modified successfully.")
                                remindersList = updatedReminders
                            }
                            .addOnFailureListener {
                                Log.d(modifyReminder_TAG, "Failed to modify reminder.")
                            }
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
                        Log.d(changeReminderState_TAG, "Reminder state modified")
                        remindersList = reminders
                    }
                    .addOnFailureListener {
                        Log.d(changeReminderState_TAG, "Error with modifying reminder state")
                    }
            }
            .addOnFailureListener {
                Log.d(changeReminderState_TAG, "Failed to fetch document")
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
                                Log.d(deleteReminder_TAG, "Reminder deleted")
                                remindersList = modifiedReminders
                            }
                            .addOnFailureListener {
                                Log.d(deleteReminder_TAG, "Error with deleting reminder")
                            }
                    } else {
                        Log.d(deleteReminder_TAG, "Invalid index or empty reminders array")
                    }
                }
                .addOnFailureListener {
                    Log.d(deleteReminder_TAG, "Failed to fetch reminders")
                }
        }
    }
}