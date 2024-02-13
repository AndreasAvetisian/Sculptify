package com.example.sculptify.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UserViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore

    private val _successMessage = MutableStateFlow("")
    val successMessage: StateFlow<String> = _successMessage.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()


    private val _userdata = MutableStateFlow<Map<String, Any>>(emptyMap())
    val userdata: StateFlow<Map<String, Any>> = _userdata.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
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

        // Default values for deleteUserData
        private const val DEFAULT_RBE = 30
        private const val DEFAULT_CBS = 15
        private const val DEFAULT_WEEKLY_GOAL = 4
        private const val DEFAULT_DAY_STREAK = 0
    }

    fun getUserData(){
        _isLoading.value = true
        _isError.value = false
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
}