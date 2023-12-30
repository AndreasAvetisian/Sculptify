package com.example.sculptify.pages

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.MAIN_ROUTE
import com.example.sculptify.MY_FAVORITE_MY_HISTORY_ROUTE
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.R
import com.example.sculptify.SIGN_UP_ROUTE

@Composable
fun AuthenticationView(navController: NavHostController) {
    val heightAnimation = remember { Animatable(1f) }
    val cornerRadiusAnimation = remember { Animatable(40.dp.value) }
    LaunchedEffect(true) {
        heightAnimation.animateTo(
            targetValue = 0f,
            animationSpec = tween(
                durationMillis = 1500,
                easing = LinearEasing
            )
        )
    }
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
                InputField(
                    value = email,
                    onValueChange = { email = it},
                    label = "Email",
                    keyboardType = KeyboardType.Email,
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = null
                )
                InputField(
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
                    }
                )
                Card (
                    colors = CardDefaults.cardColors(Color(0xff0060FE)),
                    shape = MaterialTheme.shapes.extraLarge,
                    modifier = Modifier
                        .padding(0.dp, 10.dp)
                        .clickable { navController.navigate(MAIN_ROUTE) },
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
                        fontSize = 12.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = " Sign up!",
                        fontSize = 12.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xff0060FE),
                        modifier = Modifier
                            .clickable {
                                navController.navigate(MAIN_ROUTE)
                            }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation,
    trailingIcon: @Composable (() -> Unit)?
) {
    val containerColor = Color(0xff1C1C1E)
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        label = {
            Text(text = label,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Normal
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        singleLine = true,
        modifier = Modifier
            .padding(40.dp, 0.dp, 40.dp, 0.dp)
            .fillMaxWidth()
            .padding(0.dp, 10.dp, 0.dp, 10.dp),
        colors = TextFieldDefaults.colors(
            cursorColor = Color.White,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            disabledLabelColor = Color.White,
        ),
        textStyle = TextStyle(
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = balooFontFamily,
            fontWeight = FontWeight.Normal
        ),
        shape = MaterialTheme.shapes.large,
        trailingIcon = trailingIcon
    )
}