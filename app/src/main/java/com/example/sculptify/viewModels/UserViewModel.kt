package com.example.sculptify.viewModels

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.example.sculptify.MAIN_ROUTE
import com.example.sculptify.data.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class UserViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore
    var isAuthorized = mutableStateOf(false)
    var username= mutableStateOf("")
    var successMessage = mutableStateOf("")
    var errorMessage = mutableStateOf("")
    var userdata = mutableStateOf(mapOf<String,Any>())

    fun logInUser(
        email: String,
        pw: String,
        navController: NavHostController
    ) {
        if (email.isNotEmpty() && pw.isNotEmpty()) {

            fAuth
                .signInWithEmailAndPassword(email, pw)
                .addOnSuccessListener {
                    Log.d("********", "Logged in successfully")
                    navController.navigate(MAIN_ROUTE)
                    errorMessage.value = ""
                    username.value= email
                    isAuthorized.value = true

                }
                .addOnFailureListener {
                    errorMessage.value = "Incorrect email or password"
                }
        } else {
            errorMessage.value = "Please, fill email and password fields"
        }
    }

//    fun signUpUser(
//        email: String,
//        pw: String,
//        firstName: String,
//        lastName: String,
//        root: Boolean,
//        weight: Int,
//        height: Int,
//        navController: NavHostController
//    ) {
//
//        if (email.isNotEmpty() && pw.isNotEmpty()) {
//            fAuth
//                .createUserWithEmailAndPassword(email, pw)
//                .addOnSuccessListener {
//                    isAnyUser.value = true
//                    logInUser(email, pw, navController)
//                    fireStore
//                        .collection("users")
//                        .document(it.user!!.uid)
//                        .set( User(firstName, lastName, weight, height, root) )
//                        .addOnSuccessListener {
//                            Log.d("********", "User's information added successfully!")
//                        }
//                        .addOnFailureListener { error ->
//                            Log.d("********", error.message.toString())
//                        }
//                }
//                .addOnFailureListener {
//                    errorMessage.value = "Check your email and password again"
//                }
//                .addOnFailureListener {
//                    errorMessage.value = "Incorrect form of email or password"
//                }
//        } else {
//            errorMessage.value = "Please, fill email and password fields"
//        }
//
//    }

    fun logout(navController: NavHostController) {
        fAuth.signOut()
        errorMessage.value = ""
        successMessage.value = ""
        isAuthorized.value = false
        navController.navigate(MAIN_ROUTE)
    }

    fun deleteUser() {
//        isAnyUser.value = false
//
//        if (fAuth.currentUser != null) {
//
//            fireStore
//                .collection("users")
//                .document(fAuth.currentUser!!.uid)
//                .delete()
//                .addOnSuccessListener {
//                    Log.d("********", "User deleted from FireBase successfully")
//
//                }
//                .addOnFailureListener {
//                    Log.d("********", "Something went wrong :(")
//                }
//
//
//            fAuth.currentUser!!
//                .delete()
//                .addOnCompleteListener {
//                    Log.d("********", "User deleted")
//                }
//        }
    }

    fun getUserData() {
        fireStore
            .collection("users")
            .document(fAuth.currentUser?.uid.toString())
            .get()
            .addOnSuccessListener { it ->
                val result = it.data
                var temp = mutableMapOf<String, Any>()
                if (result != null) {
                    temp = result
                }
                userdata.value = temp
            }
    }
}