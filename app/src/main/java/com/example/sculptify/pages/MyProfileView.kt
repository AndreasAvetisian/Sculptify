package com.example.sculptify.pages

import android.widget.Toast
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.enumClasses.GenderButton
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.buttons.CounterInput
import com.example.sculptify.layout.general.buttons.OpenableLineButton
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.layout.mpv.MPV_ModifyGender
import com.example.sculptify.layout.mpv.MPV_ModifyInput
import com.example.sculptify.layout.mpv.MPV_ModifyPassword
import com.example.sculptify.main.MAIN_ROUTE
import com.example.sculptify.main.MY_PROFILE_ROUTE
import com.example.sculptify.viewModels.AuthenticationViewModel
import com.example.sculptify.viewModels.UserViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

@Composable
fun MyProfileView(
    navController: NavHostController,
    animationDuration: Int = 50,
    scaleDown: Float = 0.9f
) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val authVM: AuthenticationViewModel = viewModel()
    val context = LocalContext.current


    val userEmail = Firebase.auth.currentUser?.email.toString()
    val userFirstName = userVM.userdata.value["firstName"].toString()
    val userYOB = userVM.userdata.value["yearOfBirth"].toString()
    val userHeight = userVM.userdata.value["height"]?.toString()?.toInt() ?: 0
    val userWeight = "%.1f".format(userVM.userdata.value["weight"]) + " kg"

    var selectedGenderButton by remember { mutableStateOf(GenderButton.None) }

    var isPageRefreshed by remember { mutableStateOf(false) }

    LaunchedEffect(userVM.userdata.value) {
        userVM.userdata.value.let {
            selectedGenderButton = when (it["gender"]) {
                "Male" -> GenderButton.Male
                "Female" -> GenderButton.Female
                "Others" -> GenderButton.Others
                else -> GenderButton.None
            }
        }
    }

    val interactionSource = MutableInteractionSource()
    val coroutineScope = rememberCoroutineScope()

    val scale = remember {
        Animatable(1f)
    }


    var pwValue by remember { mutableStateOf("") }
    var confirmPwValue by remember { mutableStateOf("") }
    var isPwOpen by remember { mutableStateOf(false) }
    var weakPasswordError by remember { mutableStateOf("") }

    var firstNameValue by remember { mutableStateOf("") }
    var genderValue by remember { mutableStateOf("") }
    var yobValue by remember { mutableStateOf("") }
    var heightValue by remember{ mutableIntStateOf(userHeight) }
    var weightValue by remember { mutableStateOf("") }

    var isDeleteOpen by remember { mutableStateOf(false) }

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
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF1C1C1C)),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column (
                    modifier = Modifier
                        .padding(15.675.dp, 0.dp, 15.675.dp, 0.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    MPV_ModifyInput(
                        title = "Email",
                        value = userEmail,
                        onValueChange = {},
                        placeholder = userEmail,
                        readOnly = true,
                        keyboardType = KeyboardType.Email
                    )
                    MPV_ModifyPassword(
                        pwValue = pwValue,
                        pwOnValueChange = { pwValue = it },
                        confirmPwValue = confirmPwValue,
                        confirmPwOnValueChange = { confirmPwValue = it },
                        onClick = {
                            isPwOpen = !isPwOpen
                            if (!isPwOpen) {
                                pwValue = ""
                                confirmPwValue = ""
                            }
                        },
                        isPwOpen = isPwOpen
                    )
                    if (pwValue.isEmpty()) {
                        weakPasswordError = ""
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {}
                    } else if (pwValue.length < 6) {
                        weakPasswordError = "Weak password. Your password must be at least 6 characters."
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = weakPasswordError,
                                fontSize = 16.sp,
                                color = Color.Red,
                                modifier = Modifier.padding(0.dp, 0.dp, 15.675.dp, 0.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    } else {
                        weakPasswordError = ""
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {}
                    }
                    if ((pwValue.isNotEmpty() || confirmPwValue.isNotEmpty()) && (pwValue.length >= 6)) {
                        Row (
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            ConfirmButton(
                                text = "Modify Password",
                                bgColor = if (pwValue.isNotEmpty() && confirmPwValue.isNotEmpty() && pwValue.length >= 6) {
                                    Color(0xff0000ff)
                                } else {
                                    Color(0xff0060FE).copy(alpha = 0.2f)
                                },
                                textColor = Color.White,
                                modifier = Modifier
                                    .scale(scale = scale.value)
                                    .fillMaxWidth(0.6f)
                                    .padding(start = 15.675.dp, end = 15.675.dp, top = 10.dp)
                                    .clickable(
                                        interactionSource = interactionSource,
                                        indication = null
                                    ) {
                                        coroutineScope.launch {
                                            scale.animateTo(
                                                scaleDown,
                                                animationSpec = tween(animationDuration),
                                            )
                                            scale.animateTo(
                                                1f,
                                                animationSpec = tween(animationDuration),
                                            )
                                            if (pwValue != confirmPwValue) {
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "Passwords are not matching",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    .show()
                                                confirmPwValue = ""
                                            } else if (pwValue.length >= 6) {
                                                userVM.modifyPassword(confirmPwValue)
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "Password modified successfully",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    .show()
                                                isPwOpen = false
                                                pwValue = ""
                                                confirmPwValue = ""
                                            } else {
                                                Toast
                                                    .makeText(
                                                        context,
                                                        "Error modifying password",
                                                        Toast.LENGTH_SHORT
                                                    )
                                                    .show()
                                                isPwOpen = false
                                                pwValue = ""
                                                confirmPwValue = ""
                                            }
                                        }
                                    }
                            )
                        }
                    }
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
                    CounterInput(
                        title = "Height",
                        titleFontSize = 20.sp,
                        value = "$heightValue cm",
                        onValueIncreaseClick = {
                            if (heightValue < 250) {
                                heightValue++
                            }
                        },
                        onValueDecreaseClick = {
                            if (heightValue > 100) {
                                heightValue--
                            }
                        },
                        paddingBottom = 0.dp,
                        buttonWidth = 200.dp,
                        circleSize = 90.dp
                    )
                    MPV_ModifyInput(
                        title = "Weight",
                        value = weightValue,
                        onValueChange = {
                            weightValue = it
                        },
                        placeholder = userWeight,
                        readOnly = false,
                        keyboardType = KeyboardType.Number
                    )
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ConfirmButton(
                    text = "Save",
                    textColor = Color.White,
                    bgColor = if (
                        firstNameValue.isNotEmpty() ||
                        genderValue.isNotEmpty() ||
                        yobValue.isNotEmpty() ||
                        userHeight != heightValue ||
                        weightValue.isNotEmpty()
                        ) {
                            Color(0xff0000ff)
                        } else {
                            Color(0xff0060FE).copy(alpha = 0.2f)
                        },
                    modifier = Modifier
                        .scale(scale = scale.value)
                        .fillMaxWidth(0.5f)
                        .padding(start = 15.675.dp, end = 15.675.dp)
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            coroutineScope.launch {
                                scale.animateTo(
                                    scaleDown,
                                    animationSpec = tween(animationDuration),
                                )
                                scale.animateTo(
                                    1f,
                                    animationSpec = tween(100),
                                )
                                if (
                                    firstNameValue.isNotEmpty() ||
                                    genderValue.isNotEmpty() ||
                                    yobValue.isNotEmpty() ||
                                    userHeight != heightValue ||
                                    weightValue.isNotEmpty()
                                ) {
                                    userVM.modifyUser(
                                        firstNameValue = firstNameValue,
                                        genderValue = genderValue,
                                        yobValue = yobValue,
                                        heightValue = heightValue.toString(),
                                        weightValue = weightValue
                                    )
                                    userVM.getUserData()
                                    isPageRefreshed = true

                                    firstNameValue = ""
                                    genderValue = ""
                                    yobValue = ""
                                    weightValue = ""
                                }
                            }
                        }
                )
                OpenableLineButton(
                    onClick = {
                        authVM.signOut(navController)
                    },
                    text = "Sign out",
                    textColor = Color.White,
                    isOpenable = false,
                    openOnClick = {},
                    isDeleteView = false,
                    route = MY_PROFILE_ROUTE
                )
                OpenableLineButton(
                    onClick = {
                        isDeleteOpen = !isDeleteOpen
                    },
                    text = "Delete account",
                    textColor = Color.White,
                    isOpenable = true,
                    openOnClick = {
                        authVM.deleteUser(navController)
                    },
                    isDeleteView = true,
                    route = MY_PROFILE_ROUTE
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