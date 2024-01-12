package com.example.sculptify.layout.mpv

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.auth.AuthField
import com.example.sculptify.ui.theme.balooFontFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MPV_ModifyPassword(
    pwValue: String,
    pwOnValueChange: (String) -> Unit,
    confirmPwValue: String,
    confirmPwOnValueChange: (String) -> Unit,
    onClick: () -> Unit,
    isPwOpen: Boolean
) {
    var isHiddenPw by remember { mutableStateOf(true) }

    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clickable {
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.45f)
            ) {
                Text(
                    text = "Modify Password",
                    fontSize = 20.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xffffffff)
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.675.dp, 0.dp, 18.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "********",
                    modifier = Modifier
                        .padding(top = 7.dp),
                    fontSize = 20.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF909090)
                )
                Icon(
                    modifier = Modifier
                        .scale(scaleX = -1f, scaleY = 1f)
                        .rotate(if (isPwOpen) -90f else 0f)
                        .padding(0.dp, 3.dp, 0.dp, 0.dp),
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "arrow",
                    tint = Color.White
                )
            }
        }
        if (isPwOpen) {
            AuthField(
                value = pwValue,
                onValueChange = pwOnValueChange,
                label = "New Password",
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
                    .fillMaxWidth(),
                containerColor = Color(0xff202020)
            )
            AuthField(
                value = confirmPwValue,
                onValueChange = confirmPwOnValueChange,
                label = "Confirm Password",
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(),
                trailingIcon = null,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .padding(40.dp, 10.dp, 40.dp, 10.dp)
                    .fillMaxWidth(),
                containerColor = Color(0xff202020)
            )
        }
    }

}