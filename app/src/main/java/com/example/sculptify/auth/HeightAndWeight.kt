package com.example.sculptify.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.auth.AuthField
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.balooFontFamily

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HeightAndWeight() {
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
                    text = "Your height is",
                    fontSize = 30.sp,
                )
                AuthField(
                    value = regHeight,
                    onValueChange = {
                        if (it.length <= 3) regHeight = it
                    },
                    label = "",
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.height_icon
                            ),
                            contentDescription = "password icon",
                            tint = Color.White,
                            modifier = Modifier
                                .size(50.dp)
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(40.dp, 10.dp, 40.dp, 10.dp)
                        .width(200.dp),
                    containerColor = Color(0xff1C1C1E),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                )
                CustomText(
                    text = "and weight is",
                    fontSize = 30.sp,
                )
                AuthField(
                    value = regWeight,
                    onValueChange = {
                        regWeight = it
                    },
                    label = "",
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.weight_icon
                            ),
                            contentDescription = "password icon",
                            tint = Color.White,
                            modifier = Modifier
                                .size(30.dp)
                        )
                    },
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(40.dp, 10.dp, 40.dp, 10.dp)
                        .width(200.dp),
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