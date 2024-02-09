package com.example.sculptify.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.R
import com.example.sculptify.layout.auth.AuthField
import com.example.sculptify.layout.auth.SignUpBottomBar
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.balooFontFamily



var regEmail by mutableStateOf("")
var regPw by mutableStateOf("")
var regConfirmPw by mutableStateOf("")
var isHiddenPw by mutableStateOf(true)
@Composable
fun NewSignUpView(navController: NavHostController) {
    var clickCounter by remember { mutableIntStateOf(0) }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = if (clickCounter == 0) Arrangement.Center else Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(clickCounter) {
            0 -> {
                Introduction()
            }
            1 -> {
                EmailAndPassword1()
            }
        }
    }
    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignUpBottomBar(
            backText = "Back",
            backOnClick = {
                if (clickCounter == 0) navController.popBackStack() else clickCounter--
            },
            nextText = "Next",
            nextBgColor =
            when(clickCounter) {
                0 -> Blue
                1 -> if (regEmail.isNotEmpty() && regPw.isNotEmpty() && regConfirmPw.isNotEmpty()) {
                         Blue
                     } else {
                         Blue.copy(0.2f)
                     }
                else -> Blue.copy(0.2f)
            },
            nextOnClick = {
                when(clickCounter) {
                    0 -> {
                        clickCounter++
                    }
                    1 -> {
                        if (regEmail.isNotEmpty() && regPw.isNotEmpty() && regConfirmPw.isNotEmpty()) {
                            clickCounter++
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun Introduction() {
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CustomText(
            text = "Hello!",
            fontSize = 60.sp,
        )
        CustomText(
            text = "Let's start setting up an account for you!",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EmailAndPassword1() {
    val keyboardController = LocalSoftwareKeyboardController.current

    val focusRequester = remember { FocusRequester() }

    Column (
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 0.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AuthField(
                value = regEmail,
                onValueChange = { regEmail = it },
                label = "Email",
                visualTransformation = VisualTransformation.None,
                trailingIcon = null,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                containerColor = Dark_Gray,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { keyboardController?.hide() }
                ),
            )
            AuthField(
                value = regPw,
                onValueChange = { regPw = it},
                label = "Password",
                visualTransformation = if (isHiddenPw) PasswordVisualTransformation() else  VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        painter = painterResource(
                            id = if (isHiddenPw) R.drawable.password_hidden else R.drawable.password_revealed
                        ),
                        contentDescription = "password icon",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            isHiddenPw = !isHiddenPw
                        },
                    )
                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                containerColor = Dark_Gray,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
            )
            AuthField(
                value = regConfirmPw,
                onValueChange = { regConfirmPw = it},
                label = "Confirm password",
                visualTransformation = PasswordVisualTransformation(),
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp),
                containerColor = Dark_Gray,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }
                ),
            )
        }
    }
}

