package com.example.sculptify.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.layout.mv.MV_HistoryOfWorkouts
import com.example.sculptify.layout.mv.MV_TopBar
import com.example.sculptify.layout.mv.buttons.MV_ButtonsLayout
import com.example.sculptify.layout.mv.swipeMenu.MV_SwipeMenu
import com.example.sculptify.layout.mv.workouts.MV_ClassicWorkouts
import com.example.sculptify.viewModels.UserViewModel

@SuppressLint("SimpleDateFormat")
@Composable
fun MainView(
    navController: NavHostController,
    userVM: UserViewModel = viewModel()
) {
    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val scrollState = rememberLazyListState()

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp, 15.675.dp, 15.675.dp, 104.dp)
    ) {
        item {
            MV_TopBar()
            MV_ButtonsLayout(navController)
            MV_SwipeMenu()
            MV_HistoryOfWorkouts()
            MV_ClassicWorkouts()
        } // item
    }
}