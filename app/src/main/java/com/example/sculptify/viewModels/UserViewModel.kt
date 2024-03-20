package com.example.sculptify.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sculptify.data.workout.CompletedWorkoutInfo
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class UserViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore

    private val _successMessage = MutableStateFlow("")
    val successMessage: StateFlow<String> = _successMessage.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    private val _userdata = MutableStateFlow<Map<String, Any>>(emptyMap())
    val userdata: StateFlow<Map<String, Any>> = _userdata.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError.asStateFlow()

    companion object {
        private const val getUserDataTAG = "GetUserData"
        private const val modifyUserTAG = "ModifyUser"
        private const val modifyPasswordTAG = "ModifyPassword"
        private const val modifyWeeklyGoalTAG = "ModifyWeeklyGoal"
        private const val modifyTimerSettingsTAG = "ModifyTimerSettings"
        private const val modifyHeightAndWeightTAG = "ModifyHeightAndWeight"
        private const val deleteUserDataTAG = "DeleteUserData"
        private const val updateFavoriteListTAG = "updateFavoriteList"
        private const val finishWorkoutTAG = "finishWorkout"

        // Default values for deleteUserData
        private const val DEFAULT_RBE = 30
        private const val DEFAULT_CBS = 15
        private const val DEFAULT_WEEKLY_GOAL = 4
        private const val DEFAULT_DAY_STREAK = 0
    }

    fun getUserData(){
        fAuth.currentUser?.uid?.let { userId ->
            fireStore
                .collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    _isLoading.value = false
                    if (documentSnapshot.exists()) {
                        val userDataMap = documentSnapshot.data ?: emptyMap()
                        Log.d(getUserDataTAG, "Retrieved user data: $userDataMap")
                        _userdata.value = documentSnapshot.data ?: emptyMap()
                    } else {
                        Log.d(getUserDataTAG, "User document does not exist.")
                        _userdata.value = emptyMap()
                    }
                }
                .addOnFailureListener { exception ->
                    _isLoading.value = false
                    _isError.value = true
                    Log.e(getUserDataTAG, "Error retrieving user data", exception)
                }
        }
    }

    fun modifyUser(
        firstNameValue: String,
        genderValue: String,
        yobValue: String
    ) {
        if (
            firstNameValue.isNotEmpty() ||
            genderValue.isNotEmpty() ||
            yobValue.isNotEmpty()
        ) {
            val tempUserdata = _userdata.value.toMutableMap()

            if (firstNameValue.isNotEmpty()) {
                tempUserdata["firstName"] = firstNameValue
            }

            if (genderValue.isNotEmpty()) {
                tempUserdata["gender"] = genderValue
            }

            if (yobValue.isNotEmpty()) {
                try {
                    tempUserdata["yearOfBirth"] = yobValue.toInt()
                } catch (e: NumberFormatException) {
                    Log.d(modifyUserTAG, "Error converting year of birth: ${e.message}")
                    // Handle the error, e.g., show a message to the user
                }
            }

            fireStore
                .collection("users")
                .document(fAuth.currentUser!!.uid)
                .set(tempUserdata)
                .addOnSuccessListener {
                    Log.d(modifyUserTAG, "Profile data updated successfully")
                    getUserData()
                }
                .addOnFailureListener { error ->
                    Log.d(modifyUserTAG, error.message.toString())
                }
        }
    }

    fun modifyPassword(pw:String){
        if (pw.isNotEmpty()) {
            fAuth.currentUser?.updatePassword(pw)
                ?.addOnSuccessListener {
                    Log.d(modifyPasswordTAG, "Password modified successfully")
                    _successMessage.value = "Password modified successfully"
                    _errorMessage.value = ""
                }
                ?.addOnFailureListener { exception ->
                    Log.e(modifyPasswordTAG, "Error modifying password", exception)
                    _errorMessage.value = "Error modifying password"
                    _successMessage.value = ""
                }
        }
    }

    fun modifyWeeklyGoal(weeklyGoal:Int) {
        if (weeklyGoal != 0) {
            fireStore
                .collection("users")
                .document(fAuth.currentUser!!.uid)
                .update("weeklyGoal", weeklyGoal)
                .addOnSuccessListener {
                    Log.d(modifyWeeklyGoalTAG, "WeeklyGoal updated successfully")
                    getUserData()
                }
                .addOnFailureListener { error ->
                    Log.d(modifyWeeklyGoalTAG, error.message.toString())
                }
        }
    }

    fun modifyTimerSettings(
        rbeValue: Int,
        cbsValue: Int,
    ){
        try {
            val dataToUpdate = mapOf(
                "rbe" to rbeValue,
                "cbs" to cbsValue
            )
            if (rbeValue != 0 || cbsValue != 0) {
                fireStore
                    .collection("users")
                    .document(fAuth.currentUser!!.uid)
                    .update(dataToUpdate)
                    .addOnSuccessListener {
                        Log.d(modifyTimerSettingsTAG, "Timer Settings updated successfully")
                        getUserData()
                    }
                    .addOnFailureListener { e ->
                        Log.d(modifyTimerSettingsTAG, e.message.toString())
                    }
            }
        } catch (e: Exception) {
            Log.d(modifyTimerSettingsTAG, e.message.toString())
        }
    }

    fun modifyHeightAndWeight(
        userHeight: Int,
        userWeight: Float,
    ){
        try {
            val dataToUpdate = mapOf(
                "height" to userHeight,
                "weight" to userWeight
            )
            if (userHeight != 0 || userWeight != 0f) {
                fireStore
                    .collection("users")
                    .document(fAuth.currentUser!!.uid)
                    .update(dataToUpdate)
                    .addOnSuccessListener {
                        Log.d(modifyHeightAndWeightTAG, "Timer Settings updated successfully")
                        getUserData()
                    }
                    .addOnFailureListener { e ->
                        Log.d(modifyHeightAndWeightTAG, e.message.toString())
                    }
            }
        } catch (e: Exception) {
            Log.d(modifyHeightAndWeightTAG, e.message.toString())
        }
    }

    fun resetUserData() {
        try {
            val dataToUpdate = mapOf(
                "rbe" to DEFAULT_RBE,
                "cbs" to DEFAULT_CBS,
                "weeklyGoal" to DEFAULT_WEEKLY_GOAL,
                "dayStreak" to DEFAULT_DAY_STREAK
            )

            fireStore
                .collection("users")
                .document(fAuth.currentUser!!.uid)
                .update(dataToUpdate)
                .addOnSuccessListener {
                    Log.d(deleteUserDataTAG, "Data deleted successfully")
                    getUserData()
                }
                .addOnFailureListener { e ->
                    Log.d(deleteUserDataTAG, e.message.toString())
                }

        } catch (e: Exception) {
            Log.d(deleteUserDataTAG, e.message.toString())
        }
    }

    fun updateFavoriteList(
        workoutID: String,
        focusArea: String,
        level: String,
        time: String,
        exercises: String
    ) {
        fAuth.currentUser?.uid?.let { userId ->
            val userRef = fireStore.collection("users").document(userId)

            fireStore.runTransaction { transaction ->
                val snapshot = transaction.get(userRef)
                val favoriteList = snapshot.get("favoriteList") as? MutableList<Map<String, String>> ?: mutableListOf()

                // Check if the item already exists in the favoriteList
                val existingItemIndex = favoriteList.indexOfFirst {
                    it["workoutID"] == workoutID
                }

                if (existingItemIndex != -1) {
                    favoriteList.removeAt(existingItemIndex)
                } else {
                    val favoriteListItemMap = mapOf(
                        "workoutID" to workoutID,
                        "focusArea" to focusArea,
                        "level" to level,
                        "time" to time,
                        "exercises" to exercises
                    )

                    favoriteList.add(favoriteListItemMap)
                }

                transaction.update(userRef, "favoriteList", favoriteList)
            }.addOnSuccessListener {
                getUserData()
                Log.d(updateFavoriteListTAG, "Favorite list item updated successfully")
            }.addOnFailureListener { exception ->
                Log.e(updateFavoriteListTAG, "Error updating favorite list item", exception)
            }
        }
    }

    fun finishWorkout(workoutInfo: CompletedWorkoutInfo) {
        val currentUser = Firebase.auth.currentUser
        if (currentUser != null) {
            val userRef = Firebase.firestore.collection("users").document(currentUser.uid)

            userRef.get()
                .addOnSuccessListener { documentSnapshot ->
                    val historyOfWorkouts = documentSnapshot.get("historyOfWorkouts") as? List<Map<String, Any>>

                    val workoutMap = mapOf(
                        "date" to workoutInfo.dateAndTime,
                        "focusArea" to workoutInfo.focusArea,
                        "level" to workoutInfo.level,
                        "caloriesBurned" to workoutInfo.caloriesBurned,
                        "finalDuration" to workoutInfo.finalDuration
                    )

                    val updatedHistoryOfWorkouts = historyOfWorkouts?.plus(workoutMap) ?: listOf(workoutMap)

                    userRef.update("historyOfWorkouts", updatedHistoryOfWorkouts)
                        .addOnSuccessListener {
                            Log.d(finishWorkoutTAG, "Workout added successfully")
                        }
                        .addOnFailureListener { exception ->
                            Log.d(finishWorkoutTAG, "Error adding workout", exception)
                        }

                    val today = LocalDate.now().toString()
                    val lastIncrementedDate = documentSnapshot.getString("lastIncrementedDate")

                    if (today != lastIncrementedDate) {
                        val dayStreak = documentSnapshot.getLong("dayStreak") ?: 0
                        val updatedDayStreak = dayStreak + 1
                        userRef.update("dayStreak", updatedDayStreak)
                            .addOnSuccessListener {
                                // Update lastIncrementedDate to today
                                userRef.update("lastIncrementedDate", today)
                                    .addOnSuccessListener {
                                        Log.d(finishWorkoutTAG, "Personal best streak updated successfully")
                                        // Increment pbs only if dayStreak was incremented
                                        incrementPbs(userRef, documentSnapshot, updatedDayStreak)
                                    }
                                    .addOnFailureListener { exception ->
                                        Log.d(finishWorkoutTAG, "Error updating personal best streak", exception)
                                    }
                            }
                            .addOnFailureListener { exception ->
                                Log.d(finishWorkoutTAG, "Error updating personal best streak", exception)
                            }
                    } else {
                        Log.d(finishWorkoutTAG, "User already finished one workout today")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(finishWorkoutTAG, "Error fetching data", exception)
                }
        }
    }

    private fun incrementPbs(userRef: DocumentReference, documentSnapshot: DocumentSnapshot, dayStreak: Long) {
        val lastPbsIncrementedDate = documentSnapshot.getString("lastPbsIncrementedDate")
        val today = LocalDate.now().toString()

        if (today != lastPbsIncrementedDate && dayStreak > (documentSnapshot.getLong("pbs") ?: 0)) {
            val updatedPbs = dayStreak
            userRef.update("pbs", updatedPbs)
                .addOnSuccessListener {
                    // Update lastPbsIncrementedDate to today
                    userRef.update("lastPbsIncrementedDate", today)
                        .addOnSuccessListener {
                            Log.d(finishWorkoutTAG, "Personal best streak incremented successfully")
                        }
                        .addOnFailureListener { exception ->
                            Log.d(finishWorkoutTAG, "Error updating personal best streak", exception)
                        }
                }
                .addOnFailureListener { exception ->
                    Log.d(finishWorkoutTAG, "Error updating personal best streak", exception)
                }
        } else {
            Log.d(finishWorkoutTAG, "Personal best streak was not incremented")
        }
    }
}