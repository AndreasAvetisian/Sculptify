package com.example.sculptify.layout.mpv

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.R
import com.example.sculptify.layout.auth.AuthField
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun MPV_ModifyPassword(
    pwValue: String,
    pwOnValueChange: (String) -> Unit,
    confirmPwValue: String,
    confirmPwOnValueChange: (String) -> Unit
) {
    val userVM: UserViewModel = viewModel()

    var isOpen by remember { mutableStateOf(false) }
    var isHiddenPw by remember { mutableStateOf(true) }
    var weakPasswordError by remember { mutableStateOf("") }

    val context = LocalContext.current

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
                    isOpen = !isOpen
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            ) {
                CustomText(text = "Modify Password")
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.675.dp, 0.dp, 0.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomText(
                    text = "********",
                    modifier = Modifier
                        .padding(top = 7.dp),
                    color = Color(0xFF909090)
                )
                Icon(
                    modifier = Modifier
                        .scale(scaleX = -1f, scaleY = 1f)
                        .rotate(if (isOpen) -90f else 0f)
                        .padding(0.dp, 3.dp, 0.dp, 0.dp),
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "arrow",
                    tint = Color.White
                )
            }
        }
        if (isOpen) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = Color(0xff909090)
            )
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
                    .padding(40.dp, 10.dp, 40.dp, 0.dp)
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
            if (pwValue.isEmpty()) {
                weakPasswordError = ""
            } else if (pwValue.length < 6) {
                weakPasswordError = "Weak password. Your password must be at least 6 characters."
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CustomText(
                        text = weakPasswordError,
                        fontSize = 16.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(0.dp, 0.dp, 15.675.dp, 0.dp),
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                weakPasswordError = ""
            }
            if ((pwValue.isNotEmpty() || confirmPwValue.isNotEmpty()) && (pwValue.length >= 6)) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    ConfirmButton(
                        text = "Modify Password",
                        bgColor = if (pwValue.isNotEmpty() && confirmPwValue.isNotEmpty()) {
                            Color(0xff0000ff)
                        } else {
                            Color(0xff0060FE).copy(alpha = 0.2f)
                        },
                        textColor = Color.White,
                        onClick = {
                            if (pwValue != confirmPwValue) {
                                Toast
                                    .makeText(
                                        context,
                                        "Passwords are not matching",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            } else {
                                userVM.modifyPassword(confirmPwValue)
                                Toast
                                    .makeText(
                                        context,
                                        "Password modified successfully",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                                isOpen = false
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.6f)
                            .padding(start = 15.675.dp, end = 15.675.dp)
                    )
                }
            }
        }
    }

}