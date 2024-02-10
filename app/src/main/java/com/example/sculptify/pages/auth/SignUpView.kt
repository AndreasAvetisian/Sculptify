package com.example.sculptify.pages.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.layout.auth.signUp.SignUpBottomBar
import com.example.sculptify.layout.auth.signUp.pages.Confirmation
import com.example.sculptify.layout.auth.signUp.pages.EmailAndPassword
import com.example.sculptify.layout.auth.signUp.pages.GeneralUserData
import com.example.sculptify.layout.auth.signUp.pages.Introduction
import com.example.sculptify.layout.auth.signUp.pages.WeeklyGoalSelection
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.viewModels.AuthenticationViewModel


var regEmail by mutableStateOf("")
var regPw by mutableStateOf("")
var regConfirmPw by mutableStateOf("")
var isHiddenPw by mutableStateOf(true)
var regFirstName by mutableStateOf("")
var regYearOfBirth by mutableStateOf("")
var regHeight by mutableIntStateOf(175)
var regWeight by mutableFloatStateOf(75f)
var regGender by mutableStateOf("")
var regWeeklyGoal by mutableIntStateOf(3)

var regPageCounter by mutableIntStateOf(0)

var isEditClicked by mutableStateOf(false)

var isAccountBeingCreated by mutableStateOf(false)

@Composable
fun SignUpView(navController: NavHostController) {
    val authVM: AuthenticationViewModel = viewModel()

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = if (regPageCounter == 0) Arrangement.Center else Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(regPageCounter) {
            0 -> {
                Introduction()
            }
            1 -> {
                EmailAndPassword()
            }
            2 -> {
                GeneralUserData()
            }
            3 -> {
                WeeklyGoalSelection()
            }
            4 -> {
                Confirmation()
            }
        }
    }
    if (!isAccountBeingCreated) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SignUpBottomBar(
                backText = "Back",
                backOnClick = {
                    if (regPageCounter == 0) {
                        navController.popBackStack()

                        regEmail = ""
                        regPw = ""
                        regConfirmPw = ""
                        isHiddenPw = true
                        regFirstName = ""
                        regYearOfBirth = ""
                        regHeight = 175
                        regWeight = 75f
                        regGender = ""
                        regWeeklyGoal = 3

                        regPageCounter = 0
                        isEditClicked = false
                        isAccountBeingCreated = false

                    } else regPageCounter--

                    if (
                        isEditClicked &&
                        regPw.isNotEmpty() &&
                        regConfirmPw.isNotEmpty() &&
                        regPw == regConfirmPw
                    ) {
                        regPageCounter = 4
                    }
                },
                nextText = if (regPageCounter == 4) "Create an account" else if (isEditClicked) "Modify" else "Next",
                nextBgColor =
                when(regPageCounter) {
                    0 -> Blue
                    1 -> if (
                        regEmail.isNotEmpty() &&
                        regPw.isNotEmpty() &&
                        regConfirmPw.isNotEmpty() &&
                        regPw == regConfirmPw
                    ) Blue else Blue.copy(0.2f)
                    2 -> if (
                        regFirstName.isNotEmpty() &&
                        regGender.isNotEmpty()
                    ) Blue else Blue.copy(0.2f)
                    3 -> Blue
                    4 -> Blue
                    else -> Blue.copy(0.2f)
                },
                nextOnClick = {
                    when(regPageCounter) {
                        0 -> {
                            regPageCounter++
                        }
                        1 -> {
                            if (
                                regEmail.isNotEmpty() &&
                                regPw.isNotEmpty() &&
                                regConfirmPw.isNotEmpty() &&
                                regPw == regConfirmPw
                            ) {
                                regPageCounter++
                            }
                            if (isEditClicked) regPageCounter = 4
                            isEditClicked = false
                        }
                        2 -> {
                            if (
                                regFirstName.isNotEmpty() &&
                                regGender.isNotEmpty()
                            ) regPageCounter++
                            if (isEditClicked) regPageCounter = 4
                            isEditClicked = false
                        }
                        3 -> {
                            regPageCounter++
                            if (isEditClicked) regPageCounter = 4
                            isEditClicked = false
                        }
                        4 -> {
                            isAccountBeingCreated = true
                            authVM.signUpUser(
                                regEmail = regEmail,
                                regPw = regPw,
                                regFirstName = regFirstName,
                                regWeeklyGoal = regWeeklyGoal,
                                regGender = regGender,
                                regHeight = regHeight,
                                regWeight = regWeight,
                                regYearOfBirth = regYearOfBirth.toInt(),
                                navController
                            )

                            if (authVM.getSuccessMessage().isNotEmpty()) {
                                regPageCounter = 0
                                isEditClicked = false
                                isAccountBeingCreated = false
                                regEmail = ""
                                regPw = ""
                                regConfirmPw = ""
                                isHiddenPw = true
                                regFirstName = ""
                                regYearOfBirth = ""
                                regHeight = 175
                                regWeight = 75f
                                regGender = ""
                                regWeeklyGoal = 3
                            }
                        }
                    }
                }
            )
        }
    }
}