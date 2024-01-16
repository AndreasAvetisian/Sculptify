package com.example.sculptify.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.layout.general.buttons.OpenableLineButton
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.main.MAIN_ROUTE
import com.example.sculptify.main.WORKOUT_SETTINGS_ROUTE
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun WorkoutSettingsView(navController: NavHostController) {
    var isOpen by remember { mutableStateOf(false) }
    var isPageRefreshed by remember { mutableStateOf(false) }

    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBarView(
            title = "Workout Settings",
            onClick = {
                navController.popBackStack()
                navController.navigate(MAIN_ROUTE)
            }
        )
        OpenableLineButton(
            onClick = {
                isOpen = !isOpen
            },
            text = "Timer Settings",
            textColor = Color.White,
            isOpenable = true,
            openOnClick = {},
            isDeleteView = false,
            route = WORKOUT_SETTINGS_ROUTE
        )
        OpenableLineButton(
            onClick = {
                isOpen = !isOpen
            },
            text = "Delete all data",
            textColor = Color.Red,
            isOpenable = true,
            openOnClick = {
                userVM.deleteUserData()
                isPageRefreshed = true
            },
            isDeleteView = true,
            route = WORKOUT_SETTINGS_ROUTE
        )
    }

    if (isPageRefreshed) {
        LaunchedEffect(true) {
            userVM.getUserData()
        }
        isPageRefreshed = false
    }
}