package com.example.sculptify

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sculptify.layout.BottomBar
import com.example.sculptify.layout.MeMBS
import com.example.sculptify.pages.AchievementsView
import com.example.sculptify.pages.MainView
import com.example.sculptify.pages.StatisticsView


const val MAIN_ROUTE = "main"
const val STATISTICS_ROUTE = "statistics"
const val ACHIEVEMENTS_ROUTE = "achievements"
const val ME_ROUTE = "me"


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeMBS_NavControllerHandler(
    navController: NavHostController
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    MeMBS(
        sheetState = sheetState,
        scope = scope,
        onDismiss = {
            navController.popBackStack()
        }
    )
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffoldView() {
    val navController = rememberNavController()

    Scaffold(
        content = { MainContentView(navController) },
        bottomBar = { BottomBar(navController) },
    )
}

@Composable
fun MainContentView(navController: NavHostController) {

    NavHost(navController = navController, startDestination = MAIN_ROUTE ){
        composable( route = MAIN_ROUTE ){ MainView() }
        composable( route = STATISTICS_ROUTE ){ StatisticsView() }
        composable( route = ACHIEVEMENTS_ROUTE ){ AchievementsView() }
        composable( route = ME_ROUTE ){ MeMBS_NavControllerHandler(navController) }
    }
}


