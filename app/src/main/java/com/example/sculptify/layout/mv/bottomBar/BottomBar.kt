package com.example.sculptify.layout.mv.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sculptify.data.mv.BottomBarScreen
import com.example.sculptify.ui.theme.Dark_Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Main,
        BottomBarScreen.Statistics,
        BottomBarScreen.Achievements
    )

    val navStackBackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navStackBackEntry?.destination

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Dark_Gray)
            .padding(horizontal = 15.675.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            BottomBarButton(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}