package com.example.sculptify

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sculptify.layout.BottomBar
import com.example.sculptify.layout.MeMBS
import com.example.sculptify.pages.AchievementsView
import com.example.sculptify.pages.AuthenticationView
import com.example.sculptify.pages.MainView
import com.example.sculptify.pages.MyFavorite_MyHistoryView
import com.example.sculptify.pages.MyProfileView
import com.example.sculptify.pages.SignUpView
import com.example.sculptify.pages.StatisticsView
import com.example.sculptify.viewModels.AuthenticationViewModel
import com.example.sculptify.viewModels.UserViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

const val AUTHENTICATION_ROUTE = "authentication"
const val SIGN_UP_ROUTE = "sign up"
const val MAIN_ROUTE = "main"
const val STATISTICS_ROUTE = "statistics"
const val ACHIEVEMENTS_ROUTE = "achievements"
const val ME_ROUTE = "me"
const val MY_PROFILE_ROUTE = "my profile"
const val MY_FAVORITE_MY_HISTORY_ROUTE = "my favorite and my history"
const val VERSION = "1.0.0"


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
        },
        navController = navController
    )
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffoldView() {
    val navController = rememberNavController()

    Scaffold(

        content = {
            MainContentView(navController)
        },
        bottomBar = {
            val currentBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = currentBackStackEntry?.destination?.route

            if (currentRoute != MY_PROFILE_ROUTE
                && currentRoute != MY_FAVORITE_MY_HISTORY_ROUTE
                && currentRoute != AUTHENTICATION_ROUTE
                && currentRoute != SIGN_UP_ROUTE
                ) {
                BottomBar(navController)
            }
        }
    )
}

@Composable
fun MainContentView(navController: NavHostController) {
    val loginVM: AuthenticationViewModel = viewModel()
    val userVM: UserViewModel = viewModel()
    val isAuthorized = Firebase.auth.currentUser?.uid.toString().isNotEmpty()
    Log.d("********", isAuthorized.toString())

    NavHost(
        navController = navController,
        startDestination = if (isAuthorized) MAIN_ROUTE else AUTHENTICATION_ROUTE
    ){
        composable( route = AUTHENTICATION_ROUTE ) { AuthenticationView(navController, loginVM) }
        composable( route = SIGN_UP_ROUTE ) { SignUpView(navController) }
        composable( route = MAIN_ROUTE ){ MainView() }
        composable( route = STATISTICS_ROUTE ){ StatisticsView() }
        composable( route = ACHIEVEMENTS_ROUTE ){ AchievementsView() }
        composable( route = ME_ROUTE ){ MeMBS_NavControllerHandler(navController) }
        composable(
            route = MY_PROFILE_ROUTE,
            enterTransition = {
                fadeIn(animationSpec = tween(500))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(500))
            },
        ) {
            MyProfileView(navController)
        }
        composable( route = MY_FAVORITE_MY_HISTORY_ROUTE ) { MyFavorite_MyHistoryView(navController) }
    }
}



