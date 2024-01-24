package com.example.sculptify.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class UserViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore

    var successMessage = mutableStateOf("")
        private set
    var errorMessage = mutableStateOf("")
        private set


    var userdata = mutableStateOf(mapOf<String,Any>())

    var isLoading by mutableStateOf(false)
        private set
    var isError by mutableStateOf(false)
        private set

    companion object {
        private const val getUserData_TAG = "GetUserData"
        private const val modifyUser_TAG = "ModifyUser"
        private const val modifyPassword_TAG = "ModifyPassword"
        private const val modifyWeeklyGoal_TAG = "ModifyWeeklyGoal"
        private const val modifyTimerSettings_TAG = "ModifyTimerSettings"
        private const val deleteUserData_TAG = "DeleteUserData"
    }

    fun getUserData(){
        isLoading = true
        isError = false
        fAuth.currentUser?.uid?.let { userId ->
            fireStore
                .collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    isLoading = false
                    if (documentSnapshot.exists()) {
                        val userDataMap = documentSnapshot.data ?: emptyMap()
                        Log.d(getUserData_TAG, "Retrieved user data: $userDataMap")
                        userdata.value = documentSnapshot.data ?: emptyMap()
                    } else {
                        Log.d(getUserData_TAG, "User document does not exist.")
                        userdata.value = emptyMap()
                    }
                }
                .addOnFailureListener { exception ->
                    isLoading = false
                    isError = true
                    Log.e(getUserData_TAG, "Error retrieving user data", exception)
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
            val tempUserdata = userdata.value.toMutableMap()

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
                    Log.d(modifyUser_TAG, "Error converting year of birth: ${e.message}")
                    // Handle the error, e.g., show a message to the user
                }
            }

            fireStore
                .collection("users")
                .document(fAuth.currentUser!!.uid)
                .set(tempUserdata)
                .addOnSuccessListener {
                    Log.d(modifyUser_TAG, "Profile data updated successfully")
                }
                .addOnFailureListener { error ->
                    Log.d(modifyUser_TAG, error.message.toString())
                }
        }
    }

    fun modifyPassword(pw:String){
        if (pw.isNotEmpty()) {
            fAuth.currentUser?.updatePassword(pw)
                ?.addOnSuccessListener {
                    Log.d(modifyPassword_TAG, "Password modified successfully")
                    successMessage.value = "Password modified successfully"
                    errorMessage.value = ""
                }
                ?.addOnFailureListener { exception ->
                    Log.e(modifyPassword_TAG, "Error modifying password", exception)
                    errorMessage.value = "Error modifying password"
                    successMessage.value = ""
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
                    Log.d(modifyWeeklyGoal_TAG, "WeeklyGoal updated successfully")
                }
                .addOnFailureListener { error ->
                    Log.d(modifyWeeklyGoal_TAG, error.message.toString())
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
                FirebaseFirestore
                    .getInstance()
                    .collection("users")
                    .document(fAuth.currentUser!!.uid)
                    .update(dataToUpdate)
                    .addOnSuccessListener {
                        Log.d(modifyTimerSettings_TAG, "Timer Settings updated successfully")
                    }
                    .addOnFailureListener { e ->
                        Log.d(modifyTimerSettings_TAG, e.message.toString())
                    }
            }
        } catch (e: Exception) {
            Log.d(modifyTimerSettings_TAG, e.message.toString())
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
            if (userHeight != 0 || userWeight.toInt() != 0) {
                FirebaseFirestore
                    .getInstance()
                    .collection("users")
                    .document(fAuth.currentUser!!.uid)
                    .update(dataToUpdate)
                    .addOnSuccessListener {
                        Log.d(modifyTimerSettings_TAG, "Timer Settings updated successfully")
                    }
                    .addOnFailureListener { e ->
                        Log.d(modifyTimerSettings_TAG, e.message.toString())
                    }
            }
        } catch (e: Exception) {
            Log.d(modifyTimerSettings_TAG, e.message.toString())
        }
    }

    fun deleteUserData() {
        try {
            val dataToUpdate = mapOf(
                "rbe" to 30,
                "cbs" to 15,
                "weeklyGoal" to 4,
                "dayStreak" to 0
            )

            FirebaseFirestore
                .getInstance()
                .collection("users")
                .document(fAuth.currentUser!!.uid)
                .update(dataToUpdate)
                .addOnSuccessListener {
                    Log.d(deleteUserData_TAG, "Data deleted successfully")
                }
                .addOnFailureListener { e ->
                    Log.d(deleteUserData_TAG, e.message.toString())
                }

        } catch (e: Exception) {
            Log.d(deleteUserData_TAG, e.message.toString())
        }
    }
}