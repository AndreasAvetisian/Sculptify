package com.example.sculptify.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.layout.mv.MV_HistoryOfWorkouts
import com.example.sculptify.layout.mv.swipeMenu.MV_SwipeMenu
import com.example.sculptify.layout.mv.MV_TopBar
import com.example.sculptify.layout.mv.MV_TopButtonsLayout
import com.example.sculptify.viewModels.UserViewModel

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("SimpleDateFormat")
@Composable
fun MainView(
    userVM: UserViewModel = viewModel()
) {
    LaunchedEffect(true) {
        userVM.getUserData()
    }

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp)
    ) {
        item {
            MV_TopBar()
            MV_TopButtonsLayout()
            MV_SwipeMenu()
            MV_HistoryOfWorkouts()
        } // item
    }
}