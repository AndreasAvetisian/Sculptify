package com.example.sculptify.layout.auth.signUp.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.pages.auth.isHiddenPw
import com.example.sculptify.pages.auth.regConfirmPw
import com.example.sculptify.pages.auth.regEmail
import com.example.sculptify.pages.auth.regPw
import com.example.sculptify.layout.auth.AuthField
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Red
import com.example.sculptify.ui.theme.White
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun EmailAndPassword() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.675.dp, 180.dp, 15.675.dp, 0.dp),
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
                .padding(horizontal = 20.dp),
            containerColor = Dark_Gray,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
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
                .padding(horizontal = 20.dp),
            containerColor = Dark_Gray,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        )
        AuthField(
            value = regConfirmPw,
            onValueChange = { regConfirmPw = it},
            label = "Confirm password",
            labelColor = if (regPw == regConfirmPw || regConfirmPw.isEmpty()) White else Red,
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            containerColor = Dark_Gray,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        )
        if (regPw.isNotEmpty() && regPw.length < 6) {
            CustomText(
                text = "Weak password. Your password must be at least 6 characters.",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 20.dp),
            )
        }
        if (regPw != regConfirmPw && regConfirmPw.isNotEmpty()) {
            CustomText(
                text = "Passwords not matching.",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Red,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 20.dp),
            )
        }
    }
}