package com.example.sculptify.layout.mpv

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.R
import com.example.sculptify.layout.auth.AuthField
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.AuthField_Gray
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Red
import com.example.sculptify.ui.theme.White
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.viewModels.UserViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MPV_ModifyPassword(
    pwValue: String,
    pwOnValueChange: (String) -> Unit,
    confirmPwValue: String,
    confirmPwOnValueChange: (String) -> Unit,
    onPasswordModified: () -> Unit
) {
    val userVM: UserViewModel = viewModel()

    var isHiddenPw by remember { mutableStateOf(true) }
    var weakPasswordError by remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current

    val context = LocalContext.current

    Column (
        modifier = Modifier
            .background(Dark_Gray)
            .padding(horizontal = 15.675.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthField(
            value = pwValue,
            onValueChange = pwOnValueChange,
            label = "New Password",
            visualTransformation =
            if (isHiddenPw) {
                PasswordVisualTransformation()
            } else  VisualTransformation.None,
            trailingIcon = {
                Icon(
                    painter = painterResource(
                        id = if (isHiddenPw) R.drawable.password_hidden else R.drawable.password_revealed
                    ),
                    contentDescription = "password icon",
                    tint = White,
                    modifier = Modifier.clickable {
                        isHiddenPw = !isHiddenPw
                    },
                )
            },
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = White,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(40.dp, 10.dp, 40.dp, 0.dp)
                .fillMaxWidth(),
            containerColor = AuthField_Gray,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
        )
        AuthField(
            value = confirmPwValue,
            onValueChange = confirmPwOnValueChange,
            label = "Confirm Password",
            visualTransformation = PasswordVisualTransformation(),
            trailingIcon = null,
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = White,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .padding(40.dp, 10.dp, 40.dp, 10.dp)
                .fillMaxWidth(),
            containerColor = AuthField_Gray,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
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
                    color = Red,
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
                        Blue
                    } else {
                        Blue.copy(alpha = 0.2f)
                    },
                    textColor = White,
                    onClick = {
                        if (pwValue != confirmPwValue) {
                            Toast
                                .makeText(
                                    context,
                                    "Passwords are not matching",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                            onPasswordModified()
                        } else {
                            userVM.modifyPassword(confirmPwValue)
                            Toast
                                .makeText(
                                    context,
                                    "Password modified successfully",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                            onPasswordModified()
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