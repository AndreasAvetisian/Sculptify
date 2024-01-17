package com.example.sculptify.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TimeViewModel: ViewModel() {
    var  isDialogShown by mutableStateOf(false)
        private set

    fun onTimeClick() {
        isDialogShown = true
    }

    fun onDismissDialog() {
        isDialogShown = false
    }

}