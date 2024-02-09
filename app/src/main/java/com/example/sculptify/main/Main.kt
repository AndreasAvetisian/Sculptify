package com.example.sculptify.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.sculptify.auth.AuthenticationView
import com.example.sculptify.auth.ConfirmRegistration
import com.example.sculptify.auth.EmailAndPassword
import com.example.sculptify.auth.GenderSelection
import com.example.sculptify.auth.HeightAndWeight
import com.example.sculptify.auth.NameAndYOB
import com.example.sculptify.auth.NewSignUpView
import com.example.sculptify.auth.SignUpView
import com.example.sculptify.auth.WeeklyGoal
import com.example.sculptify.auth.regEmail
import com.example.sculptify.auth.regFirstName
import com.example.sculptify.auth.regGender
import com.example.sculptify.auth.regHeight
import com.example.sculptify.auth.regPw
import com.example.sculptify.auth.regWeeklyGoal
import com.example.sculptify.auth.regWeight
import com.example.sculptify.auth.regYearOfBirth
import com.example.sculptify.auth.weakPwError
import com.example.sculptify.layout.auth.SignUpBottomBar
import com.example.sculptify.layout.dayStreakActiveDaysView.DayStreak_ActiveDaysView
import com.example.sculptify.layout.mbs.MBS
import com.example.sculptify.layout.msv.StatisticsView
import com.example.sculptify.layout.mv.bottomBar.BottomBar
import com.example.sculptify.pages.AchievementsView
import com.example.sculptify.pages.MainView
import com.example.sculptify.pages.MyFavorite_MyHistoryView
import com.example.sculptify.pages.MyProfileView
import com.example.sculptify.pages.ReminderView
import com.example.sculptify.pages.settings.GeneralSettingsView
import com.example.sculptify.pages.settings.WorkoutSettingsView
import com.example.sculptify.screens.Screen
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.viewModels.AuthenticationViewModel
import com.example.sculptify.viewModels.ReminderViewModel
import com.example.sculptify.viewModels.UserViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlin.math.roundToInt

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

    val authVM: AuthenticationViewModel = viewModel()

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
            } else if (
                currentRoute == Screen.SignUp.route ||
                currentRoute == Screen.EmailAndPassword.route ||
                currentRoute == Screen.NameAndYOB.route ||
                currentRoute == Screen.HeightAndWeight.route ||
                currentRoute == Screen.GenderSelection.route ||
                currentRoute == Screen.WeeklyGoal.route ||
                currentRoute == Screen.ConfirmRegistration.route
            ){
                SignUpBottomBar(
                    backText = if (currentRoute == Screen.SignUp.route) "LOG IN" else "BACK",
                    backOnClick = {
                        navController.popBackStack()
                        if (currentRoute == Screen.SignUp.route) authVM.setErrorMessage("")
                    },
                    nextText =
                        when (currentRoute) {
                            Screen.SignUp.route -> "I'M READY"
                            Screen.ConfirmRegistration.route -> "CREATE AN ACCOUNT"
                            else -> "NEXT"
                        },
                    nextBgColor =
                        if (regEmail.isNotEmpty() && regPw.isNotEmpty() && currentRoute == Screen.EmailAndPassword.route && weakPwError.isEmpty()) {
                            Blue
                        } else if (regFirstName.isNotEmpty() && regYearOfBirth.isNotEmpty() && currentRoute == Screen.NameAndYOB.route) {
                            Blue
                        } else if (regHeight.isNotEmpty() && regWeight.isNotEmpty() && currentRoute == Screen.HeightAndWeight.route) {
                            Blue
                        } else if (regGender.isNotEmpty() && currentRoute == Screen.GenderSelection.route) {
                            Blue
                        } else if (currentRoute == Screen.SignUp.route) {
                            Blue
                        } else if (currentRoute == Screen.WeeklyGoal.route) {
                            Blue
                        } else if (currentRoute == Screen.ConfirmRegistration.route) {
                            Blue
                        } else {
                            Blue.copy(alpha = 0.2f)
                        },
                    nextOnClick = {
                        when (currentRoute) {
                            Screen.SignUp.route -> { navController.navigate(Screen.EmailAndPassword.route) }
                            Screen.EmailAndPassword.route -> {
                                if (regEmail.isNotEmpty() && regPw.isNotEmpty() && weakPwError.isEmpty()) {
                                    navController.navigate(Screen.NameAndYOB.route)
                                    weakPwError = ""
                                }
                            }
                            Screen.NameAndYOB.route -> {
                                if (regFirstName.isNotEmpty() && regYearOfBirth.isNotEmpty()) {
                                    navController.navigate(Screen.HeightAndWeight.route)
                                }
                            }
                            Screen.HeightAndWeight.route -> {
                                if (regHeight.isNotEmpty() && regWeight.isNotEmpty()) {
                                    navController.navigate(Screen.GenderSelection.route)
                                }
                            }
                            Screen.GenderSelection.route -> {
                                if (regGender.isNotEmpty()) {
                                    navController.navigate(Screen.WeeklyGoal.route)
                                }
                            }
                            Screen.WeeklyGoal.route -> { navController.navigate(Screen.ConfirmRegistration.route) }
                            Screen.ConfirmRegistration.route -> {
                                 if (regEmail.isNotEmpty() && regPw.isNotEmpty()) {
                                    authVM.signUpUser(
                                        regEmail = regEmail,
                                        regPw = regPw,
                                        regFirstName = regFirstName,
                                        regWeeklyGoal = regWeeklyGoal.roundToInt(),
                                        regGender = regGender,
                                        regHeight = regHeight.toInt(),
                                        regWeight = regWeight.toFloat(),
                                        regYearOfBirth = regYearOfBirth.toInt(),
                                        navController
                                    )

                                    regEmail = ""
                                    regPw = ""
                                    regFirstName = ""
                                    regGender = ""
                                    regHeight = ""
                                    regWeight = ""
                                    regYearOfBirth = ""
                                }
                            }
                        }
                    }
                )
            }
        }
    )
}

@Composable
fun MainContentView(navController: NavHostController) {
    val authVM: AuthenticationViewModel = viewModel()
    val reminderVM: ReminderViewModel = viewModel()
    val isAuthorized = Firebase.auth.currentUser?.uid?.isNotEmpty() == true

    NavHost(
        navController = navController,
        startDestination = if (isAuthorized) Screen.Main.route else Screen.Authentication.route,
        modifier = Modifier.background(Black)
    ){
        composable( route = Screen.Authentication.route ) { AuthenticationView(navController, authVM) }
        // -------------------------------------SignUp--------------------------------------------

        composable( route = Screen.NewSignUp.route ) { NewSignUpView(navController) }

        composable( route = Screen.SignUp.route ) { SignUpView() }
        composable( route = Screen.EmailAndPassword.route ) { EmailAndPassword() }
        composable( route = Screen.NameAndYOB.route ) { NameAndYOB() }
        composable( route = Screen.HeightAndWeight.route ) { HeightAndWeight() }
        composable( route = Screen.GenderSelection.route ) { GenderSelection() }
        composable( route = Screen.WeeklyGoal.route ) { WeeklyGoal() }
        composable( route = Screen.ConfirmRegistration.route ) { ConfirmRegistration() }
        // ---------------------------------------------------------------------------------------
        composable( route = Screen.Main.route ){ MainView(navController) }
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