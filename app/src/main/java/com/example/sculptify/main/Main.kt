package com.example.sculptify.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.sculptify.auth.SignUpView
import com.example.sculptify.auth.WeeklyGoal
import com.example.sculptify.auth.regCbs
import com.example.sculptify.auth.regDayStreak
import com.example.sculptify.auth.regEmail
import com.example.sculptify.auth.regFirstName
import com.example.sculptify.auth.regGender
import com.example.sculptify.auth.regHeight
import com.example.sculptify.auth.regIsAdmin
import com.example.sculptify.auth.regPw
import com.example.sculptify.auth.regRbe
import com.example.sculptify.auth.regWeeklyGoal
import com.example.sculptify.auth.regWeight
import com.example.sculptify.auth.regYearOfBirth
import com.example.sculptify.auth.weakPwError
import com.example.sculptify.layout.auth.SignUpBottomBar
import com.example.sculptify.layout.dayStreakActiveDaysView.DayStreak_ActiveDaysView
import com.example.sculptify.layout.mbs.MBS
import com.example.sculptify.layout.mv.bottomBar.BottomBar
import com.example.sculptify.pages.AchievementsView
import com.example.sculptify.pages.MainView
import com.example.sculptify.pages.MyFavorite_MyHistoryView
import com.example.sculptify.pages.MyProfileView
import com.example.sculptify.pages.ReminderView
import com.example.sculptify.pages.StatisticsView
import com.example.sculptify.pages.settings.GeneralSettingsView
import com.example.sculptify.pages.settings.WorkoutSettingsView
import com.example.sculptify.viewModels.AuthenticationViewModel
import com.example.sculptify.viewModels.TimeViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlin.math.roundToInt

const val AUTHENTICATION_ROUTE = "authentication"
// -------------------------------------SignUp--------------------------------------------
const val SIGN_UP_ROUTE = "sign up"
const val EMAIL_AND_PASSWORD = "email and password"
const val NAME_AND_YOB = "name and year of birth"
const val HEIGHT_AND_WEIGHT = "height and weight"
const val GENDER_SELECTION = "gender selection"
const val WEEKLY_GOAL = "weekly goal"
const val CONFIRM_REGISTRATION = "confirm registration"
// ---------------------------------------------------------------------------------------
const val MAIN_ROUTE = "main"
const val DAY_STREAK_ACTIVE_DAYS_ROUTE = "day streak and active days"
const val STATISTICS_ROUTE = "statistics"
const val ACHIEVEMENTS_ROUTE = "achievements"
const val ME_ROUTE = "me"
const val MY_PROFILE_ROUTE = "my profile"
const val MY_FAVORITE_MY_HISTORY_ROUTE = "my favorite and my history"
const val WORKOUT_SETTINGS_ROUTE = "workout settings"
const val GENERAL_SETTINGS_ROUTE = "general settings"
const val REMINDER_ROUTE = "reminder"
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

    Scaffold(
        content = {
            MainContentView(navController)
        },
        bottomBar = {
            if (
                currentRoute == MAIN_ROUTE ||
                currentRoute == STATISTICS_ROUTE ||
                currentRoute == ACHIEVEMENTS_ROUTE
            ) {
                BottomBar(navController)
            } else if (
                currentRoute == SIGN_UP_ROUTE ||
                currentRoute == EMAIL_AND_PASSWORD ||
                currentRoute == NAME_AND_YOB ||
                currentRoute == HEIGHT_AND_WEIGHT ||
                currentRoute == GENDER_SELECTION ||
                currentRoute == WEEKLY_GOAL ||
                currentRoute == CONFIRM_REGISTRATION
            ){
                SignUpBottomBar(
                    backText = if (currentRoute == SIGN_UP_ROUTE) "LOG IN" else "BACK",
                    backOnClick = {
                        when (currentRoute) {
                            SIGN_UP_ROUTE -> {
                                authVM.errorMessage.value = ""
                                navController.navigate(AUTHENTICATION_ROUTE)
                            }
                            EMAIL_AND_PASSWORD -> { navController.navigate(SIGN_UP_ROUTE) }
                            NAME_AND_YOB -> { navController.navigate(EMAIL_AND_PASSWORD) }
                            HEIGHT_AND_WEIGHT -> { navController.navigate(NAME_AND_YOB) }
                            GENDER_SELECTION -> { navController.navigate(HEIGHT_AND_WEIGHT) }
                            WEEKLY_GOAL -> { navController.navigate(GENDER_SELECTION) }
                            CONFIRM_REGISTRATION -> { navController.navigate(WEEKLY_GOAL) }
                        }
                    },
                    nextText =
                        when (currentRoute) {
                            SIGN_UP_ROUTE -> "I'M READY"
                            CONFIRM_REGISTRATION -> "CREATE AN ACCOUNT"
                            else -> "NEXT"
                        },
                    nextBgColor =
                        if (regEmail.isNotEmpty() && regPw.isNotEmpty() && currentRoute == EMAIL_AND_PASSWORD && weakPwError.isEmpty()) {
                            Color(0xff0060FE)
                        } else if (regFirstName.isNotEmpty() && regYearOfBirth.isNotEmpty() && currentRoute == NAME_AND_YOB) {
                            Color(0xff0060FE)
                        } else if (regHeight.isNotEmpty() && regWeight.isNotEmpty() && currentRoute == HEIGHT_AND_WEIGHT) {
                            Color(0xff0060FE)
                        } else if (regGender.isNotEmpty() && currentRoute == GENDER_SELECTION) {
                            Color(0xff0060FE)
                        } else if (currentRoute == SIGN_UP_ROUTE) {
                            Color(0xff0060FE)
                        } else if (currentRoute == WEEKLY_GOAL) {
                            Color(0xff0060FE)
                        } else if (currentRoute == CONFIRM_REGISTRATION) {
                            Color(0xff0060FE)
                        } else {
                            Color(0xff0060FE).copy(alpha = 0.2f)
                        },
                    nextOnClick = {
                        when (currentRoute) {
                            SIGN_UP_ROUTE -> { navController.navigate(EMAIL_AND_PASSWORD) }
                            EMAIL_AND_PASSWORD -> {
                                if (regEmail.isNotEmpty() && regPw.isNotEmpty() && weakPwError.isEmpty()) {
                                    navController.navigate(NAME_AND_YOB)
                                    weakPwError = ""
                                }
                            }
                            NAME_AND_YOB -> {
                                if (regFirstName.isNotEmpty() && regYearOfBirth.isNotEmpty()) {
                                    navController.navigate(HEIGHT_AND_WEIGHT)
                                }
                            }
                            HEIGHT_AND_WEIGHT -> {
                                if (regHeight.isNotEmpty() && regWeight.isNotEmpty()) {
                                    navController.navigate(GENDER_SELECTION)
                                }
                            }
                            GENDER_SELECTION -> {
                                if (regGender.isNotEmpty()) {
                                    navController.navigate(WEEKLY_GOAL)
                                }
                            }
                            WEEKLY_GOAL -> { navController.navigate(CONFIRM_REGISTRATION) }
                            CONFIRM_REGISTRATION -> {
                                 if (regEmail.isNotEmpty() && regPw.isNotEmpty()) {
                                    authVM.signUpUser(
                                        regEmail,
                                        regPw,
                                        regFirstName,
                                        regIsAdmin,
                                        regCbs,
                                        regRbe,
                                        regDayStreak,
                                        regWeeklyGoal.roundToInt(),
                                        regGender,
                                        regHeight.toInt(),
                                        regWeight.toFloat(),
                                        regYearOfBirth.toInt(),
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
    val timeVM: TimeViewModel = viewModel()
    val isAuthorized = Firebase.auth.currentUser?.uid?.isNotEmpty() == true

    NavHost(
        navController = navController,
        startDestination = if (isAuthorized) REMINDER_ROUTE else AUTHENTICATION_ROUTE,
        modifier = Modifier.background(Color.Black)
    ){
        composable( route = AUTHENTICATION_ROUTE ) { AuthenticationView(navController, authVM) }
        // -------------------------------------SignUp--------------------------------------------
        composable( route = SIGN_UP_ROUTE ) { SignUpView() }
        composable( route = EMAIL_AND_PASSWORD ) { EmailAndPassword() }
        composable( route = NAME_AND_YOB ) { NameAndYOB() }
        composable( route = HEIGHT_AND_WEIGHT ) { HeightAndWeight() }
        composable( route = GENDER_SELECTION ) { GenderSelection() }
        composable( route = WEEKLY_GOAL ) { WeeklyGoal() }
        composable( route = CONFIRM_REGISTRATION ) { ConfirmRegistration() }
        // ---------------------------------------------------------------------------------------
        composable( route = MAIN_ROUTE ){ MainView(navController) }
        composable( route = DAY_STREAK_ACTIVE_DAYS_ROUTE ){ DayStreak_ActiveDaysView(navController) }
        composable( route = STATISTICS_ROUTE ){ StatisticsView() }
        composable( route = ACHIEVEMENTS_ROUTE ){ AchievementsView() }
        composable( route = ME_ROUTE ){ MeMBS_NavControllerHandler(navController) }
        composable( route = MY_PROFILE_ROUTE ) { MyProfileView(navController) }
        composable( route = MY_FAVORITE_MY_HISTORY_ROUTE ) { MyFavorite_MyHistoryView(navController) }
        composable( route = WORKOUT_SETTINGS_ROUTE ) { WorkoutSettingsView(navController) }
        composable( route = GENERAL_SETTINGS_ROUTE ) { GeneralSettingsView(navController) }
        composable( route = REMINDER_ROUTE ) { ReminderView(navController, timeVM) }
    }
}