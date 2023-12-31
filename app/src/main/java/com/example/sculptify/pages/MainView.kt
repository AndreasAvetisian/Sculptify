package com.example.sculptify.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun MainView(
    userVM: UserViewModel = viewModel()
) {
    val getUserData = userVM.state.value

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = getUserData.firstName)
    }
}