package com.example.sculptify.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class UserViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore
    var successMessage = mutableStateOf("")
    var errorMessage = mutableStateOf("")
    var userdata = mutableStateOf(mapOf<String,Any>())
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

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
                        Log.d("UserViewModel", "Retrieved user data: $userDataMap")
                        userdata.value = documentSnapshot.data ?: emptyMap()
                    } else {
                        Log.d("UserViewModel", "User document does not exist.")
                        userdata.value = emptyMap()
                    }
                }
                .addOnFailureListener { exception ->
                    isLoading = false
                    isError = true
                    Log.e("UserViewModel", "Error retrieving user data", exception)
                }
        }
    }

    fun modifyPassword(pw:String){
        if (pw.isNotEmpty()) {
            fAuth.currentUser?.updatePassword(pw)
                ?.addOnSuccessListener {
                    Log.d("********", "Password modified successfully")
                    successMessage.value = "Password modified successfully"
                    errorMessage.value = ""
                }
                ?.addOnFailureListener { exception ->
                    Log.e("********", "Error modifying password", exception)
                    errorMessage.value = "Error modifying password"
                    successMessage.value = ""
                }
        }
    }
}