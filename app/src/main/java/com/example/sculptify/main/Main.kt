package com.example.sculptify.main

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sculptify.layout.dayStreakActiveDaysView.DayStreak_ActiveDaysView
import com.example.sculptify.layout.mbs.MBS
import com.example.sculptify.layout.msv.StatisticsView
import com.example.sculptify.layout.mv.bottomBar.BottomBar
import com.example.sculptify.layout.myFavMyHisView.MyFavorite_MyHistoryView
import com.example.sculptify.pages.AchievementsView
import com.example.sculptify.pages.MainView
import com.example.sculptify.pages.MyProfileView
import com.example.sculptify.pages.ReminderView
import com.example.sculptify.pages.WorkoutDetailsView
import com.example.sculptify.pages.WorkoutView
import com.example.sculptify.pages.auth.AuthenticationView
import com.example.sculptify.pages.auth.SignUpView
import com.example.sculptify.pages.settings.GeneralSettingsView
import com.example.sculptify.pages.settings.WorkoutSettingsView
import com.example.sculptify.screens.Screen
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.viewModels.AuthenticationViewModel
import com.example.sculptify.viewModels.ReminderViewModel
import com.example.sculptify.viewModels.UserViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

const val VERSION = "1.0.0"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeMBS_NavControllerHandler(
    navController: NavHostController
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    MBS(
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
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    Scaffold(
        content = {
            MainContentView(navController)
        },
        bottomBar = {
            if (
                currentRoute == Screen.Main.route ||
                currentRoute == Screen.Statistics.route ||
                currentRoute == Screen.Achievements.route
            ) {
                BottomBar(navController)
            }
        }
    )
}

@Composable
fun MainContentView(navController: NavHostController) {
    val authVM: AuthenticationViewModel = viewModel()
    val reminderVM: ReminderViewModel = viewModel()
    val userVM: UserViewModel = viewModel()
    val isAuthorized = Firebase.auth.currentUser?.uid?.isNotEmpty() == true

    userVM.checkDayStreak()

    var previousRoute by remember { mutableStateOf<String?>(null) }
    var currentRoute by remember { mutableStateOf<String?>(null) }

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val newRoute = currentDestination?.route

    if (newRoute != currentRoute) {
        previousRoute = currentRoute
        currentRoute = newRoute
    }

    NavHost(
        navController = navController,
        startDestination = if (isAuthorized) Screen.Main.route else Screen.Authentication.route,
        modifier = Modifier.background(Black),
        enterTransition = {
            slideIntoContainer(
                towards =
                if (previousRoute == Screen.Achievements.route && currentRoute == Screen.Statistics.route) {
                    AnimatedContentTransitionScope.SlideDirection.Right
                } else {
                    AnimatedContentTransitionScope.SlideDirection.Left
                },
                animationSpec = tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards =
                if (previousRoute == Screen.Achievements.route && currentRoute == Screen.Statistics.route) {
                    AnimatedContentTransitionScope.SlideDirection.Right
                } else {
                    AnimatedContentTransitionScope.SlideDirection.Left
                },
                animationSpec = tween(500)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(500)
            )
        },
    ){
        composable( route = Screen.Authentication.route ) { AuthenticationView(navController, authVM) }
        composable( route = Screen.SignUp.route ) { SignUpView(navController) }
        composable( route = Screen.Main.route ){ MainView(navController) }
        composable( route = Screen.WorkoutDetails.route ){ WorkoutDetailsView(navController) }
        composable( route = Screen.Workout.route ){ WorkoutView(navController) }
        composable( route = Screen.DSAD.route ){ DayStreak_ActiveDaysView(navController) }
        composable( route = Screen.Statistics.route ){ StatisticsView(navController) }
        composable( route = Screen.Achievements.route ){ AchievementsView(navController) }
        composable( route = Screen.Me.route ){ MeMBS_NavControllerHandler(navController) }
        composable( route = Screen.MyProfile.route ) { MyProfileView(navController) }
        composable( route = Screen.MFMH.route ) { MyFavorite_MyHistoryView(navController) }
        composable( route = Screen.WorkoutSettings.route ) { WorkoutSettingsView(navController) }
        composable( route = Screen.GeneralSettings.route ) { GeneralSettingsView(navController) }
        composable( route = Screen.Reminder.route ) { ReminderView(navController, reminderVM) }
    }
}