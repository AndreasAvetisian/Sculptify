package com.example.sculptify.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.sculptify.data.achievements.Achievement
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AchievementsViewModel : ViewModel() {
    private val fireStore = Firebase.firestore

    private val _achievementsState = MutableStateFlow<Map<String, Achievement>>(emptyMap())
    val achievementsState: StateFlow<Map<String, Achievement>> = _achievementsState

    companion object {
        private const val getAchievementsTAG = "GetAchievements"
    }

    fun getAchievements() {
        fireStore
            .collection("achievements")
            .get()
            .addOnSuccessListener { documents ->
                val achievementsMap = mutableMapOf<String, Achievement>()
                for (document in documents) {
                    achievementsMap[document.id] =
                        Achievement(
                            title = document.getString("title") ?: "",
                            condition = document.getLong("condition")?.toInt() ?: 0,
                            conditionDescription = document.getString("conditionDescription") ?: "",
                            description = document.getString("description") ?: ""
                        )
                }
                _achievementsState.value = achievementsMap
                Log.d(getAchievementsTAG, "Retrieved achievements: $achievementsMap")
            }
            .addOnFailureListener { exception ->
                Log.e(getAchievementsTAG, "Error getting achievements", exception)
            }
    }
}