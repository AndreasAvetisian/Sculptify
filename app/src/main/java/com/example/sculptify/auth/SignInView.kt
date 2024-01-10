package com.example.sculptify.auth

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.R
import com.example.sculptify.layout.auth.AuthField
import com.example.sculptify.layout.auth.ErrorMessage
import com.example.sculptify.main.SIGN_UP_ROUTE
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.viewModels.AuthenticationViewModel

@Composable
fun AuthenticationView(
    navController: NavHostController,
    authVM: AuthenticationViewModel
) {
    val heightAnimation = remember { Animatable(1f) }
    LaunchedEffect(true) {
        heightAnimation.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 1500,
                easing = LinearEasing
            )
        )
    }

    val cornerRadiusAnimation = remember { Animatable(40.dp.value) }
    LaunchedEffect(true) {
        cornerRadiusAnimation.animateTo(
            targetValue = 0.dp.value,
            animationSpec = tween(
                durationMillis = 1500,
                easing = LinearEasing
            )
        )
    }

    var email by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var isHiddenPw by remember { mutableStateOf(true) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(heightAnimation.value),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(id = R.drawable.sculptify_logo),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(0.9f)
            )
        }
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            colors = CardDefaults.cardColors(Color(0xff000000)),
            shape = RoundedCornerShape(
                topStart = cornerRadiusAnimation.value.dp,
                topEnd = cornerRadiusAnimation.value.dp,
                bottomEnd = 0.dp,
                bottomStart = 0.dp
            )
        ) {
            Column (
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "SCULPTIFY",
                    fontSize = 60.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 50.dp)
                )
                AuthField(
                    value = email,
                    onValueChange = { email = it },
                    label = "Email",
                    keyboardType = KeyboardType.Email,
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = null,
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .padding(40.dp, 10.dp, 40.dp, 10.dp)
                        .fillMaxWidth()
                )
                AuthField(
                    value = pw,
                    onValueChange = { pw = it},
                    label = "Password",
                    keyboardType = KeyboardType.Password,
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
                        .padding(40.dp, 10.dp, 40.dp, 10.dp)
                        .fillMaxWidth()
                )
                Card (
                    colors = CardDefaults.cardColors(
                        if (email.isNotEmpty() && pw.isNotEmpty())
                            Color(0xff0060FE)
                        else
                            Color(0xff0060FE).copy(alpha = 0.2f)
                    ),
                    shape = MaterialTheme.shapes.extraLarge,
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .clickable {
                            authVM.logInUser(email, pw, navController)
                        },
                ) {
                    Icon(
                        Icons.Filled.ArrowForward,
                        contentDescription = "log in",
                        modifier = Modifier
                            .padding(50.dp, 10.dp),
                        tint = Color.White
                    )
                }
                Row {
                    Text(
                        text = "Don't have an account?",
                        fontSize = 18.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = " Sign up!",
                        fontSize = 18.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff0060FE),
                        modifier = Modifier
                            .clickable {
                                navController.navigate(SIGN_UP_ROUTE)
                            }
                    )
                }
                ErrorMessage(authVM)
            }
        }
    }
}