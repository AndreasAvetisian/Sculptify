package com.example.sculptify.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.R
import com.example.sculptify.layout.mv.MV_Button
import com.example.sculptify.layout.mv.MV_SwipeMenu
import com.example.sculptify.layout.mv.MV_TopBar
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
            Row (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                MV_Button(
                    data = "0",
                    iconId = R.drawable.day_streak_main_icon,
                    iconColor = Color(0xffff4e28),
                    title = "Day Streak",
                    stat = "Personal best: 0",
                    width = 0.5f,
                    paddingStart = 0.dp,
                    paddingEnd = 10.dp,
                    onClick = {}
                )
                MV_Button(
                    data = "0/4",
                    iconId = R.drawable.active_days_main_icon,
                    iconColor = Color(0xff0060FE),
                    title = "This week",
                    stat = "in Total: 0",
                    width = 1f,
                    paddingStart = 10.dp,
                    paddingEnd = 0.dp,
                    onClick = {}
                )
            }
            MV_SwipeMenu()
        } // item
    }
}