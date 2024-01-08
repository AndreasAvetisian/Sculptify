package com.example.sculptify.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.sculptify.data.User
import com.example.sculptify.main.AUTHENTICATION_ROUTE
import com.example.sculptify.main.MAIN_ROUTE
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AuthenticationViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore
    val isAuthorized = Firebase.auth.currentUser?.uid.toString().isNotEmpty()
    var errorMessage = mutableStateOf("")

    companion object {
        private const val SignUp_TAG = "SignUpUser"
        private const val SignIp_TAG = "SignInUser"
        private const val SignOut_TAG = "SignOutUser"
        private const val DeleteUser_TAG = "DeleteUser"
    }

    fun logInUser(email: String, pw: String, navController: NavHostController) {
        if (email.isNotEmpty() && pw.isNotEmpty()) {
            fAuth
                .signInWithEmailAndPassword(email, pw)
                .addOnSuccessListener {
                    Log.d(SignIp_TAG, "Logged in successfully")
                    navController.navigate(MAIN_ROUTE)
                    errorMessage.value = ""
                }
                .addOnFailureListener { error: Exception ->
                    when (error) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            errorMessage.value = "Incorrect email or password. Please try again."
                        }
                        else -> {
                            errorMessage.value = "Authentication failed. Please check your email and password again."
                        }
                    }
                }
        } else {
            errorMessage.value = "Please, fill email and password fields"
        }
    }

    fun signUpUser(
        regEmail: String,
        regPw: String,
        regFirstName: String,
        regIsAdmin: Boolean,
        regCbs: Int,
        regRbe: Int,
        regDayStreak: Int,
        regWeeklyGoal: Int,
        regGender: String,
        regHeight: Int,
        regWeight: Float,
        regYearOfBirth: Int,
        navController: NavHostController
    ) {
        if (regEmail.isNotEmpty() && regPw.isNotEmpty()) {
            fAuth.createUserWithEmailAndPassword(regEmail, regPw)
                .addOnSuccessListener { authResult ->
                    saveUserInformation(authResult.user!!.uid, regFirstName, regIsAdmin, regCbs, regRbe, regDayStreak, regWeeklyGoal, regGender, regHeight, regWeight, regYearOfBirth)
                    logInUser(regEmail, regPw, navController)
                    navController.navigate(MAIN_ROUTE)
                }
                .addOnFailureListener { error: Exception ->
                    when (error) {
                        is FirebaseAuthUserCollisionException -> {
                            errorMessage.value = "Email already exists. Please use a different email."
                        }
                        is FirebaseAuthWeakPasswordException -> {
                            errorMessage.value = "Weak password. Please choose a stronger password."
                        }
                        else -> {
                            errorMessage.value = "Registration failed. Please check your email and password again."
                        }
                    }
                }

        } else {
            errorMessage.value = "Please fill in email and password fields."
        }
    }

    private fun saveUserInformation(
        userId: String,
        regFirstName: String,
        regIsAdmin: Boolean,
        regCbs: Int,
        regRbe: Int,
        regDayStreak: Int,
        regWeeklyGoal: Int,
        regGender: String,
        regHeight: Int,
        regWeight: Float,
        regYearOfBirth: Int
    ) {
        val user = User(
            regFirstName,
            regIsAdmin,
            regCbs,
            regRbe,
            regDayStreak,
            regWeeklyGoal,
            regGender,
            regHeight,
            regWeight,
            regYearOfBirth
        )

        fireStore.collection("users").document(userId)
            .set(user)
            .addOnSuccessListener {
                Log.d(SignUp_TAG, "User's information added successfully!")
            }
            .addOnFailureListener { error ->
                Log.d(SignUp_TAG, error.message.toString())
            }
    }

    fun signOut(navController: NavHostController) {
        fAuth.signOut()
        Log.d(SignOut_TAG, "Logout successfully")
        errorMessage.value = ""
        navController.navigate(AUTHENTICATION_ROUTE)
    }

    fun deleteUser(navController: NavHostController) {

        if (isAuthorized) {
            fireStore
                .collection("users")
                .document(fAuth.currentUser!!.uid)
                .delete()
                .addOnSuccessListener {
                    Log.d(DeleteUser_TAG, "User deleted from FireBase successfully")
                }
                .addOnFailureListener {
                    Log.d(DeleteUser_TAG, "Something went wrong :(")
                }
            fAuth.currentUser!!
                .delete()
                .addOnCompleteListener {
                    Log.d(DeleteUser_TAG, "User deleted")
                }
            navController.navigate(AUTHENTICATION_ROUTE)
        }
    }
}
