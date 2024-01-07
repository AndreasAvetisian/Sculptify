package com.example.sculptify.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
import com.example.sculptify.layout.InputField
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun EmailAndPassword(navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(15.675.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.92f)
                    .padding(15.675.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputField(
                    value = regEmail,
                    onValueChange = { regEmail = it},
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
                InputField(
                    value = regPw,
                    onValueChange = { regPw = it},
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
            }
        }
    }
}