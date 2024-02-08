package com.example.sculptify.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.layout.av.AchievementItem
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.viewModels.AchievementsViewModel
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun AchievementsView(navController: NavHostController) {

    val achievementsVM: AchievementsViewModel = viewModel()
    val achievementsState by achievementsVM.achievementsState.collectAsState()

    val userVM: UserViewModel = viewModel()
    val userData by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value
    )

    LaunchedEffect(true) {
        achievementsVM.getAchievements()
        userVM.getUserData()
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBarView(
            title = "Achievements",
            navController = navController
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.675.dp),
            verticalArrangement = Arrangement.spacedBy(15.675.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(achievementsState.toList()) { (_, achievement) ->
                val isActivated = false
                AchievementItem(
                    title = achievement.title,
                    description = achievement.conditionDescription,
                    isActivated = isActivated
                )
            }
        }
    }
}