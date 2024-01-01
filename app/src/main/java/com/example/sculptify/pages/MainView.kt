package com.example.sculptify.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun MainView(
    userVM: UserViewModel = viewModel()
) {
    val userData = userVM.userdata.value
    val isLoading = userVM.isLoading
    val isError = userVM.isError

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        } else if (isError) {
            Text("Error loading user data", color = Color.Red)
        } else {
            Text(text = userData["firstName"].toString())
        }
    }
}