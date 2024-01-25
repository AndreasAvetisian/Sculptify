package com.example.sculptify.layout.mv.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.example.sculptify.enumClasses.BottomBarButton
import com.example.sculptify.layout.mbs.MBS
import com.example.sculptify.layout.sv.selectedTabIndexForMyStatistics
import com.example.sculptify.main.ACHIEVEMENTS_ROUTE
import com.example.sculptify.main.MAIN_ROUTE
import com.example.sculptify.main.STATISTICS_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(navController: NavHostController) {

    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedButton by remember { mutableStateOf(BottomBarButton.Main) }
    var lastClickedButton by remember { mutableStateOf(BottomBarButton.Main) }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    // Callback function to be passed to MeMBS
    val onBottomSheetDismiss: () -> Unit = {
        showBottomSheet = false
        selectedButton = lastClickedButton
    }

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Column {
        Divider( thickness = 2.dp, color = Color(0xff909090))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(88.35.dp)
            .background(Color(0xFF000000))
        ){
            Row (
                modifier = Modifier
                    .padding(15.675.dp, 0.dp, 15.675.dp, 0.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                BottomBarButton(
                    iconId = painterResource(id = R.drawable.history_of_workouts),
                    text = "Main",
                    iconDescription = "main page icon",
                    modifier = Modifier,
                    selected = selectedButton == BottomBarButton.Main,
                    onClick = {
                        if (currentRoute !== MAIN_ROUTE) {
                            navController.navigate(MAIN_ROUTE)
                            lastClickedButton = BottomBarButton.Main
                            selectedButton = BottomBarButton.Main
                        }
                    }
                )
                BottomBarButton(
                    iconId = painterResource(id = R.drawable.statistics_icon),
                    text = "Statistics",
                    iconDescription = "statistics page icon",
                    modifier = Modifier,
                    selected = selectedButton == BottomBarButton.Statistics,
                    onClick = {
                        navController.navigate(STATISTICS_ROUTE)
                        selectedTabIndexForMyStatistics = 0
                        lastClickedButton = BottomBarButton.Statistics
                        selectedButton = BottomBarButton.Statistics
                    }
                )
                BottomBarButton(
                    iconId = painterResource(id = R.drawable.achievements_icon),
                    text = "Achievements",
                    iconDescription = "achievements page icon",
                    modifier = Modifier,
                    selected = selectedButton == BottomBarButton.Achievements,
                    onClick = {
                        navController.navigate(ACHIEVEMENTS_ROUTE)
                        lastClickedButton = BottomBarButton.Achievements
                        selectedButton = BottomBarButton.Achievements
                    }
                )
                BottomBarButton(
                    iconId = painterResource(id = R.drawable.profile_icon),
                    text = "Me",
                    iconDescription = "me page icon",
                    modifier = Modifier,
                    selected = selectedButton == BottomBarButton.Me,
                    onClick = {
                        showBottomSheet = true
                        lastClickedButton = selectedButton
                    }
                )

                if (showBottomSheet) {
                    MBS(
                        sheetState = sheetState,
                        scope = scope,
                        onDismiss = onBottomSheetDismiss,
                        navController = navController
                    )
                }
            }
        }
    }
}