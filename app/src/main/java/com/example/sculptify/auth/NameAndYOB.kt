package com.example.sculptify.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.auth.AuthField
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.balooFontFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NameAndYOB() {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column (
        modifier = Modifier
            .fillMaxSize()
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
                CustomText(
                    text = "Your name is",
                    fontSize = 30.sp
                )
                AuthField(
                    value = regFirstName,
                    onValueChange = { regFirstName = it },
                    label = "",
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = null,
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .padding(40.dp, 10.dp, 40.dp, 10.dp),
                    containerColor = Color(0xff1C1C1E),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                )
                CustomText(
                    text = "and you were born in",
                    fontSize = 30.sp
                )
                AuthField(
                    value = regYearOfBirth,
                    onValueChange = {
                        if (it.length <= 4) regYearOfBirth = it
                    },
                    label = "",
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = null,
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Normal
                    ),
                    modifier = Modifier
                        .padding(40.dp, 10.dp, 40.dp, 10.dp),
                    containerColor = Color(0xff1C1C1E),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                )
            }
        }
    }
}