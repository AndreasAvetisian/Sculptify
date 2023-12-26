package com.example.sculptify

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.sculptify.layout.BottomBar
import com.example.sculptify.pages.AchievementsView
import com.example.sculptify.pages.MainView
import com.example.sculptify.pages.MeView
import com.example.sculptify.pages.StatisticsView


const val MAIN_ROUTE = "main"
const val STATISTICS_ROUTE = "statistics"
const val ACHIEVEMENTS_ROUTE = "achievements"
const val ME_ROUTE = "me"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffoldView() {
    val navController = rememberNavController()
//    val scState = rememberScaffoldState( rememberDrawerState(DrawerValue.Closed) )

    Scaffold(
//        scaffoldState = scState,
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
        composable( route = ME_ROUTE ){ MeView() }
    }
}


