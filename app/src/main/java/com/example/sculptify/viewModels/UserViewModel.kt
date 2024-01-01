package com.example.sculptify.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sculptify.data.User
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UserViewModel: ViewModel() {
    val state = mutableStateOf(User())


    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            state.value = getUserData()
        }
    }
}
suspend fun getUserData(): User {
    val db = FirebaseFirestore.getInstance()
    var userData = User()

    try {
        db.collection("users").get().await().map {
            val result = it.toObject(User::class.java)
            userData = result
        }
    } catch (e: FirebaseFirestoreException) {
        Log.d("error", "getDataFromFireStore: $e")
    }

    return userData
}