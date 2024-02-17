package com.example.sculptify.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sculptify.data.settings.general.reminder.Reminder
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ReminderViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore

    companion object {
        private const val addReminderTAG = "AddReminder"
        private const val modifyReminderTAG = "ModifyReminder"
        private const val changeReminderStateTAG = "ChangeReminderState"
        private const val deleteReminderTAG = "DeleteReminder"
    }

    private val _remindersList = MutableStateFlow<List<Map<String, Any>>>(emptyList())
    val remindersList: StateFlow<List<Map<String, Any>>> = _remindersList.asStateFlow()

    private fun updateRemindersList(newList: List<Map<String, Any>>) {
        _remindersList.value = newList
    }

    var isCreateDialogShown by mutableStateOf(false)
        private set

    var isEditDialogShown by mutableStateOf(false)
        private set

    var editingReminderId by mutableStateOf<String?>(null)
        private set

    private val _userdata = MutableStateFlow<Map<String, Any>>(emptyMap())
    val userdata: StateFlow<Map<String, Any>> = _userdata.asStateFlow()

    var doesReminderAlreadyExist by mutableStateOf(false)

    fun onAddReminderClick() {
        isCreateDialogShown = true
        isEditDialogShown = false
    }

    fun onEditReminderClick(reminderId: String) {
        editingReminderId = reminderId
        isCreateDialogShown = false
        isEditDialogShown = true
    }

    fun onDismissDialog() {
        isCreateDialogShown = false
        isEditDialogShown = false
    }

    fun getUserData(){
        fAuth.currentUser?.uid?.let { userId ->
            fireStore
                .collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        val userDataMap = documentSnapshot.data ?: emptyMap()
                        Log.d("GetUserData", "Retrieved user data: $userDataMap")
                        _userdata.value = documentSnapshot.data ?: emptyMap()
                    } else {
                        Log.d("GetUserData", "User document does not exist.")
                        _userdata.value = emptyMap()
                    }
                }
                .addOnFailureListener { exception ->
                    Log.e("GetUserData", "Error retrieving user data", exception)
                }
        }
    }

    fun addReminder(
        hourValue: Int,
        minuteValue: Int,
        amOrPmValue: String,
        daysOfWeek: List<String>
    ) {
        val newReminder = Reminder(
            hourValue = hourValue,
            minuteValue = minuteValue,
            amOrPm = amOrPmValue,
            daysOfWeek = daysOfWeek
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
                        updatedReminders.add(newReminder.toMap())

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
                                Log.d(addReminderTAG, "Reminder added successfully.")
                                updateRemindersList(updatedReminders)
                            }
                            .addOnFailureListener {
                                Log.d(addReminderTAG, "Failed to add reminder.")
                            }
                    }
                    getUserData()
                }
        }
    }

    fun modifyReminder(
        reminderId: String,
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

                    val reminderIndex = currentReminders.indexOfFirst { it["id"] == reminderId }

                    if (reminderIndex != -1) {
                        val updatedReminders = currentReminders.toMutableList()
                        val modifiedReminder = updatedReminders[reminderIndex].toMap().toMutableMap().apply {
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
                                Log.d(modifyReminderTAG, "Reminder modified successfully.")
                                updateRemindersList(updatedReminders)
                            }
                            .addOnFailureListener {
                                Log.d(modifyReminderTAG, "Failed to modify reminder.")
                            }
                    }
                }
        }
    }

    fun changeReminderState(
        reminderId: String,
        state: Boolean
    ) {
        val userDocument = fireStore.collection("users").document(fAuth.currentUser!!.uid)

        userDocument.get()
            .addOnSuccessListener { documentSnapshot ->
                val reminders = documentSnapshot.get("reminders") as? List<Map<String, Any>> ?: emptyList()
                val reminderIndex = reminders.indexOfFirst { it["id"] == reminderId }

                if (reminderIndex != -1) {
                    val modifiedReminders = ArrayList(reminders)
                    val reminderItem = modifiedReminders[reminderIndex] as MutableMap<String, Any>
                    reminderItem["active"] = state

                    userDocument.update("reminders", modifiedReminders)
                        .addOnSuccessListener {
                            Log.d(changeReminderStateTAG, "Reminder state modified")
                            updateRemindersList(modifiedReminders)
                        }
                        .addOnFailureListener {
                            Log.d(changeReminderStateTAG, "Error with modifying reminder state")
                        }
                } else {
                    Log.d(changeReminderStateTAG, "Reminder with ID $reminderId not found")
                }
            }
            .addOnFailureListener {
                Log.d(changeReminderStateTAG, "Failed to fetch document")
            }
    }

    fun deleteReminder(reminderId: String) {
        fAuth.currentUser?.uid?.let { userId ->
            val userDocumentRef = fireStore.collection("users").document(userId)

            // Get the current reminders array
            userDocumentRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    val currentReminders = documentSnapshot.get("reminders") as? List<Map<String, Any>> ?: emptyList()

                    val reminderIndex = currentReminders.indexOfFirst { it["id"] == reminderId }

                    // Remove the reminder at the specified index
                    if (reminderIndex != -1) {
                        val modifiedReminders = ArrayList(currentReminders)
                        modifiedReminders.removeAt(reminderIndex)

                        // Update the document with the modified reminders array
                        userDocumentRef.update("reminders", modifiedReminders)
                            .addOnSuccessListener {
                                Log.d(deleteReminderTAG, "Reminder with ID $$reminderId was deleted")
                                updateRemindersList(modifiedReminders)
                            }
                            .addOnFailureListener {
                                Log.d(deleteReminderTAG, "Error with deleting reminder")
                            }
                    } else {
                        Log.d(deleteReminderTAG, "Reminder with ID $reminderId not found")
                    }
                }
                .addOnFailureListener {
                    Log.d(deleteReminderTAG, "Failed to fetch reminders")
                }
        }
    }
}