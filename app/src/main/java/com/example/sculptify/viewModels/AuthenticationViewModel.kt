package com.example.sculptify.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.sculptify.data.user.User
import com.example.sculptify.screens.Screen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthenticationViewModel: ViewModel() {
    private val fAuth = Firebase.auth
    private val fireStore = Firebase.firestore


    private val _isAuthorized = MutableStateFlow(false)
    val isAuthorized: StateFlow<Boolean> = _isAuthorized.asStateFlow()

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage.asStateFlow()

    fun setErrorMessage(message: String) {
        _errorMessage.value = message
    }

    fun getErrorMessage(): String {
        return errorMessage.value
    }

    companion object {
        private const val SignUpTAG = "SignUpUser"
        private const val SignInTAG = "SignInUser"
        private const val SignOutTAG = "SignOutUser"
        private const val DeleteUserTAG = "DeleteUser"
    }

    init {
        _isAuthorized.value = fAuth.currentUser?.uid?.isNotEmpty() == true
    }

    fun logInUser(email: String, pw: String, navController: NavHostController) {
        if (email.isNotEmpty() && pw.isNotEmpty()) {
            fAuth
                .signInWithEmailAndPassword(email, pw)
                .addOnSuccessListener {
                    Log.d(SignInTAG, "Logged in successfully")
                    _isAuthorized.value = true
                    navController.navigate(Screen.Main.route)
                    _errorMessage.value = ""
                }
                .addOnFailureListener { error: Exception ->
                    when (error) {
                        is FirebaseAuthInvalidCredentialsException -> {
                            _errorMessage.value = "Incorrect email or password. Please try again."
                        }
                        else -> {
                            _errorMessage.value = "Authentication failed. Please check your email and password again."
                        }
                    }
                }
        } else {
            _errorMessage.value = "Please, fill email and password fields"
        }
    }

    fun signUpUser(
        regEmail: String,
        regPw: String,
        regFirstName: String,
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
                    saveUserInformation(authResult.user!!.uid, regFirstName, regWeeklyGoal, regGender, regHeight, regWeight, regYearOfBirth)
                    logInUser(regEmail, regPw, navController)
                    navController.navigate(Screen.Main.route)
                }
                .addOnFailureListener { error: Exception ->
                    when (error) {
                        is FirebaseAuthUserCollisionException -> {
                            _errorMessage.value = "Email already exists. Please use a different email."
                        }
                        is FirebaseAuthWeakPasswordException -> {
                            _errorMessage.value = "Weak password. Please choose a stronger password."
                        }
                        else -> {
                            _errorMessage.value = "Registration failed. Please check your email and password again."
                        }
                    }
                }

        } else {
            _errorMessage.value = "Please fill in email and password fields."
        }
    }

    private fun saveUserInformation(
        userId: String,
        regFirstName: String,
        regWeeklyGoal: Int,
        regGender: String,
        regHeight: Int,
        regWeight: Float,
        regYearOfBirth: Int
    ) {
//        val user = User(
//            firstName = regFirstName,
//            weeklyGoal = regWeeklyGoal,
//            gender = regGender,
//            height = regHeight,
//            weight = regWeight,
//            yearOfBirth = regYearOfBirth
//        )
        val user = User(
            firstName = regFirstName,
            weeklyGoal = regWeeklyGoal,
            gender = regGender,
            height = regHeight,
            weight = regWeight,
            yearOfBirth = regYearOfBirth
        )

        fireStore.collection("users").document(userId)
            .set(user)
            .addOnSuccessListener {
                Log.d(SignUpTAG, "User's information added successfully!")
            }
            .addOnFailureListener { error ->
                Log.d(SignUpTAG, error.message.toString())
            }
    }

    fun signOut(navController: NavHostController) {
        fAuth.signOut()
        Log.d(SignOutTAG, "Logout successfully")
        _isAuthorized.value = false
        _errorMessage.value = ""
        navController.navigate(Screen.Authentication.route)
    }

    fun deleteUser(navController: NavHostController) {

        if (_isAuthorized.value) {
            fireStore
                .collection("users")
                .document(fAuth.currentUser!!.uid)
                .delete()
                .addOnSuccessListener {
                    Log.d(DeleteUserTAG, "User deleted from FireBase successfully")
                }
                .addOnFailureListener {
                    Log.d(DeleteUserTAG, "Something went wrong :(")
                }
            fAuth.currentUser!!
                .delete()
                .addOnCompleteListener {
                    Log.d(DeleteUserTAG, "User deleted")
                    _isAuthorized.value = false
                }
            navController.navigate(Screen.Authentication.route)
        }
    }
}
