package com.example.sculptify.layout.mv.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sculptify.R
import com.example.sculptify.data.mv.BottomBarScreen
import com.example.sculptify.layout.mbs.MBS
import com.example.sculptify.main.MAIN_ROUTE

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

    val currentRoute = currentDestination?.route

    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val onBottomSheetDismiss: () -> Unit = {
        showBottomSheet = false
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xff1C1C1E)),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        screens.forEach { screen ->
            BottomBarButton(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
        if (currentRoute == MAIN_ROUTE) {
            Icon(
                painter = painterResource(id = R.drawable.profile_icon),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.clickable {
                    showBottomSheet = true
                }
            )
        }
    }
    if (showBottomSheet) {
        MBS(
            sheetState = sheetState,
            scope = scope,
            onDismiss = onBottomSheetDismiss,
            navController = navController
        )
    }
}