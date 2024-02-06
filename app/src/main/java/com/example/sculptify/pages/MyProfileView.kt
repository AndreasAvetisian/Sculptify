package com.example.sculptify.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.enumClasses.GenderButton
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.buttons.OpenableLineButton
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.layout.mpv.MPV_BottomButtonsLayout
import com.example.sculptify.layout.mpv.MPV_ModifyGender
import com.example.sculptify.layout.mpv.MPV_ModifyInput
import com.example.sculptify.main.MAIN_ROUTE
import com.example.sculptify.main.MY_PROFILE_ROUTE
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.White
import com.example.sculptify.viewModels.AuthenticationViewModel
import com.example.sculptify.viewModels.UserViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun MyProfileView(
    navController: NavHostController
) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val authVM: AuthenticationViewModel = viewModel()

    val userEmail = Firebase.auth.currentUser?.email.toString()

    val userFirstName by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value["firstName"].toString()
    )

    val userYOB by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value["yearOfBirth"].toString()
    )

    var selectedGenderButton by remember { mutableStateOf(GenderButton.None) }

    var isPageRefreshed by remember { mutableStateOf(false) }

    LaunchedEffect(userVM.userdata.collectAsState().value) {
        userVM.userdata.value.let {
            selectedGenderButton = when (it["gender"]) {
                "Male" -> GenderButton.Male
                "Female" -> GenderButton.Female
                "Others" -> GenderButton.Others
                else -> GenderButton.None
            }
        }
    }

    var pwValue by remember { mutableStateOf("") }
    var confirmPwValue by remember { mutableStateOf("") }

    var firstNameValue by remember { mutableStateOf("") }
    var genderValue by remember { mutableStateOf("") }
    var yobValue by remember { mutableStateOf("") }

    var isModifyPasswordOpen by remember { mutableStateOf(false) }
    var isBodyParametersOpen by remember { mutableStateOf(false) }

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            TopBarView(
                title = "My Profile",
                onClick = {
                    navController.popBackStack()
                    navController.navigate(MAIN_ROUTE)
                }
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Dark_Gray)
                    .padding(15.675.dp, 0.dp, 15.675.dp, 0.dp)
            ) {
                MPV_ModifyInput(
                    title = "Email",
                    value = userEmail,
                    onValueChange = {},
                    placeholder = userEmail,
                    readOnly = true,
                    keyboardType = KeyboardType.Email
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OpenableLineButton(
                    text = "Modify Password",
                    route = MY_PROFILE_ROUTE,
                    index = 0,
                    onClick = {
                        isModifyPasswordOpen = !isModifyPasswordOpen
                        if (!isModifyPasswordOpen) {
                            pwValue = ""
                            confirmPwValue = ""
                        }
                    },
                    onPasswordModified = {
                        if (pwValue == confirmPwValue) {
                            isModifyPasswordOpen = false
                            pwValue = ""
                            confirmPwValue = ""
                        } else {
                            confirmPwValue = ""
                        }
                    },
                    isOpen = isModifyPasswordOpen,
                    pwValue = pwValue,
                    pwOnValueChange = { pwValue = it },
                    confirmPwValue = confirmPwValue,
                    confirmPwOnValueChange = { confirmPwValue = it }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OpenableLineButton(
                    text = "Body Parameters",
                    route = MY_PROFILE_ROUTE,
                    index = 1,
                    onClick = {
                        isBodyParametersOpen = !isBodyParametersOpen
                    },
                    isOpen = isBodyParametersOpen
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Dark_Gray)
            ) {
                Column (
                    modifier = Modifier
                        .padding(horizontal = 15.675.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    MPV_ModifyInput(
                        title = "Name",
                        value = firstNameValue,
                        onValueChange = {
                            firstNameValue = it
                        },
                        placeholder = userFirstName,
                        readOnly = false,
                        keyboardType = KeyboardType.Text
                    )
                    MPV_ModifyGender(
                        onClickM = {
                            selectedGenderButton = GenderButton.Male
                            genderValue = "Male"
                        },
                        onClickF = {
                            selectedGenderButton = GenderButton.Female
                            genderValue = "Female"
                        },
                        onClickO = {
                            selectedGenderButton = GenderButton.Others
                            genderValue = "Others"
                        },
                        selectedM = selectedGenderButton == GenderButton.Male,
                        selectedF = selectedGenderButton == GenderButton.Female,
                        selectedO = selectedGenderButton == GenderButton.Others
                    )
                    MPV_ModifyInput(
                        title = "Year of Birth",
                        value = yobValue,
                        onValueChange = {
                            if (it.length <= 4) yobValue = it
                        },
                        placeholder = userYOB,
                        readOnly = false,
                        keyboardType = KeyboardType.Number
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ConfirmButton(
                    text = "Save",
                    textColor = White,
                    bgColor = if (
                        firstNameValue.isNotEmpty() ||
                        genderValue.isNotEmpty() ||
                        yobValue.isNotEmpty()
                        ) {
                            Blue
                        } else {
                            Blue.copy(alpha = 0.2f)
                        },
                    onClick = {
                        if (
                            firstNameValue.isNotEmpty() ||
                            genderValue.isNotEmpty() ||
                            yobValue.isNotEmpty()
                        ) {
                            userVM.modifyUser(
                                firstNameValue = firstNameValue,
                                genderValue = genderValue,
                                yobValue = yobValue
                            )
                            userVM.getUserData()

                            firstNameValue = ""
                            genderValue = ""
                            yobValue = ""

                            isPageRefreshed = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(start = 15.675.dp, end = 15.675.dp)
                )
                MPV_BottomButtonsLayout(
                    onSignOutClick = {
                        authVM.signOut(navController)
                    },
                    onDeleteAccountClick = {
                        authVM.deleteUser(navController)
                    }
                )
            }
        }
    }
    if (isPageRefreshed) {
        LaunchedEffect(true) {
            userVM.getUserData()
        }
        isPageRefreshed = false
    }
}