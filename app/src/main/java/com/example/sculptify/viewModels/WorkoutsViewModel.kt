package com.example.sculptify.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WorkoutsViewModel: ViewModel() {
    private val fireStore = Firebase.firestore

    private val _workoutsList = MutableStateFlow<Map<String, Any>>(emptyMap())
    val workoutsList: StateFlow<Map<String, Any>> = _workoutsList.asStateFlow()

    companion object {
        private const val getWorkoutsDataTAG = "GetWorkoutsData"
    }

    fun getWorkoutsData() {
        fireStore
            .collection("workouts")
            .get()
            .addOnSuccessListener { documents ->
                val workoutsMap = mutableMapOf<String, Any>()

                for (document in documents) {
                    val title = document.getString("title") ?: ""
                    val workouts = document.get("workouts") as Map<String, Any>

                    workoutsMap[title] = workouts
                }

                _workoutsList.value = workoutsMap
            }
            .addOnFailureListener { exception ->
                Log.e(getWorkoutsDataTAG, "Error getting workouts", exception)
            }
    }
}