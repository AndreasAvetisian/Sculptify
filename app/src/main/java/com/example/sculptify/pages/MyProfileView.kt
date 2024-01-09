package com.example.sculptify.pages

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.R
import com.example.sculptify.layout.MyProfileButton
import com.example.sculptify.main.MAIN_ROUTE
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.viewModels.AuthenticationViewModel
import com.example.sculptify.viewModels.UserViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun MyProfileView(navController: NavHostController) {
    val authVM: AuthenticationViewModel = viewModel()
    val userVM: UserViewModel = viewModel()

    var isOpen by remember { mutableStateOf(false) }

    val animation = remember { Animatable(0.dp.value) }

    // Use a set to keep track of modified inputs
    val modifiedInputs = remember { mutableSetOf<String>() }

    // Function to update the modifiedInputs set
    fun updateModifiedInputs(key: String) {
        modifiedInputs.add(key)
    }

    // LaunchedEffect to start the animation when any input is modified
    LaunchedEffect(modifiedInputs.toList()) {
        if (modifiedInputs.isNotEmpty()) {
            animation.animateTo(
                targetValue = 60.dp.value,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = LinearEasing
                )
            )
        }
    }


    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val userEmail = Firebase.auth.currentUser?.email.toString()
    val userFirstName = userVM.userdata.value["firstName"].toString()
    val userGender = userVM.userdata.value["gender"].toString()
    val userYOB = userVM.userdata.value["yearOfBirth"].toString()
    val userHeight = userVM.userdata.value["height"].toString() + " cm"
    val userWeight = userVM.userdata.value["weight"].toString() + " kg"


    var pwValue by remember { mutableStateOf("") }
    var firstNameValue by remember { mutableStateOf("") }
    var genderValue by remember { mutableStateOf("") }
    var yobValue by remember { mutableStateOf("") }
    var heightValue by remember { mutableStateOf("") }
    var weightValue by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        // Top Bar
        Row (
            modifier = Modifier
                .padding(15.675.dp, 22.8.dp, 15.675.dp, 22.8.dp)
                .fillMaxWidth()
                .height(31.35.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card (
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .clickable {
                        navController.popBackStack()
                        navController.navigate(MAIN_ROUTE)
                    },
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(Color(0xff1C1C1E))
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .width(16.dp)
                            .height(16.dp),
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = "arrow",
                        tint = Color.White
                    )
                }
            }
            Text(
                text = "My profile",
                fontSize = 20.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xffFCFCFC)
            )
            Card (
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {}
        } // Top Bar ending
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(392.dp)
                .background(Color(0xFF1C1C1C)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                modifier = Modifier
                    .padding(15.675.dp, 0.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                UserInput(
                    title = "Email",
                    value = userEmail,
                    onValueChange = {},
                    placeholder = userEmail,
                    readOnly = true,
                    keyboardType = KeyboardType.Email
                )
                UserInput(
                    title = "Modify Password",
                    value = pwValue,
                    onValueChange = {
                        pwValue = it
                        isOpen = true
                        updateModifiedInputs("pwValue")
                    },
                    placeholder = "********",
                    readOnly = false,
                    keyboardType = KeyboardType.Password
                )
                UserInput(
                    title = "Name",
                    value = firstNameValue,
                    onValueChange = {
                        firstNameValue = it
                        isOpen = true
                        updateModifiedInputs("firstNameValue")
                    },
                    placeholder = userFirstName,
                    readOnly = false,
                    keyboardType = KeyboardType.Text
                )
                UserInput(
                    title = "Gender",
                    value = genderValue,
                    onValueChange = {
                        genderValue = it
                        isOpen = true
                        updateModifiedInputs("genderValue")
                    },
                    placeholder = userGender,
                    readOnly = false,
                    keyboardType = KeyboardType.Text
                )
                UserInput(
                    title = "Year of Birth",
                    value = yobValue,
                    onValueChange = {
                        yobValue = it
                        isOpen = true
                        updateModifiedInputs("yobValue")
                    },
                    placeholder = userYOB,
                    readOnly = false,
                    keyboardType = KeyboardType.Number
                )
                UserInput(
                    title = "Height",
                    value = heightValue,
                    onValueChange = {
                        heightValue = it
                        isOpen = true
                        updateModifiedInputs("heightValue")
                    },
                    placeholder = userHeight,
                    readOnly = false,
                    keyboardType = KeyboardType.Number
                )
                UserInput(
                    title = "Weight",
                    value = weightValue,
                    onValueChange = {
                        weightValue = it
                        isOpen = true
                        updateModifiedInputs("weightValue")
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
            MyProfileButton(
                onClick = {},
                text = "Save",
                textColor = Color.Blue,
                height = animation.value.dp
            )
            Spacer(modifier = Modifier.height(10.dp))
            MyProfileButton(
                onClick = {
                    authVM.signOut(navController)
                },
                text = "Sign out",
                textColor = Color.White,
                height = 56.dp
            )
            Spacer(modifier = Modifier.height(10.dp))
            MyProfileButton(
                onClick = {
                    authVM.deleteUser(navController)
                },
                text = "Delete account",
                textColor = Color.Red,
                height = 56.dp
            )
        }
    }
}

@Composable
fun UserInput(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    readOnly: Boolean,
    keyboardType: KeyboardType
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(0.45f)
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xffffffff)
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = placeholder,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                },
                singleLine = true,
                readOnly = readOnly,
                shape = RoundedCornerShape(0.dp),
                colors = TextFieldDefaults.colors(
                    cursorColor = Color.White,
                    focusedTextColor = Color(0xFF909090),
                    unfocusedTextColor = Color(0xFF909090),
                    disabledTextColor = Color(0xFF909090),
                    focusedPlaceholderColor = Color(0xFF909090),
                    unfocusedPlaceholderColor = Color(0xFF909090),
                    disabledPlaceholderColor = Color(0xFF909090),
                    focusedContainerColor = Color(0xFF1C1C1C),
                    unfocusedContainerColor = Color(0xFF1C1C1C),
                    disabledContainerColor = Color(0xFF1C1C1C),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    disabledLabelColor = Color.White,
                ),
                modifier = Modifier
                    .height(56.dp),
                trailingIcon = {
                    if (title != "Email") {
                        Icon(
                            modifier = Modifier
                                .scale(scaleX = -1f, scaleY = 1f)
                                .padding(0.dp, 3.dp, 0.dp, 0.dp),
                            painter = painterResource(id = R.drawable.arrow),
                            contentDescription = "arrow",
                            tint = Color.White
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}