package com.example.sculptify.pages

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.settings.general.reminder.EmptyListIcon
import com.example.sculptify.screens.Screen
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun MyFavoriteView() {
    val userVM: UserViewModel = viewModel()

    val userData by userVM.userdata.collectAsState()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val userFavoriteList = userData["favoriteList"] as? List<String> ?: emptyList()

    val heightAnimation = remember { Animatable(1f) }
    LaunchedEffect(true) {
        heightAnimation.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 500,
                easing = LinearEasing
            )
        )
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(heightAnimation.value))
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (userFavoriteList.isNotEmpty()) {
                itemsIndexed(userFavoriteList) { _, item ->
                    CustomText(text = item)
                }
            } else {
                item {
                    EmptyListIcon(
                        route = Screen.MFMH.route
                    )
                }
            }
        }
    }
}