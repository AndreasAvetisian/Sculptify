package com.example.sculptify.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.sculptify.AUTHENTICATION_ROUTE
import com.example.sculptify.MAIN_ROUTE
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class AuthenticationViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    var successMessage = mutableStateOf("")
    var errorMessage = mutableStateOf("")

    fun logInUser(email: String, pw: String, navController: NavHostController) {
        if (email.isNotEmpty() && pw.isNotEmpty()) {
            fAuth
                .signInWithEmailAndPassword(email, pw)
                .addOnSuccessListener {
                    Log.d("********", "Logged in successfully")
                    navController.navigate(MAIN_ROUTE)
                    errorMessage.value = ""
                    successMessage.value = ""
                }
                .addOnFailureListener {
                    errorMessage.value = "Incorrect email or password"
                }
        } else {
            errorMessage.value = "Please, fill email and password fields"
        }
    }

    fun logout(navController: NavHostController) {
        fAuth.signOut()
        navController.navigate(AUTHENTICATION_ROUTE)
        Log.d("********", "Logout successfully")
        errorMessage.value = ""
        successMessage.value = ""
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
