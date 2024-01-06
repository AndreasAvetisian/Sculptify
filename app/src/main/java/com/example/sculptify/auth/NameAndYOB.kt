package com.example.sculptify.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.EMAIL_AND_PASSWORD
import com.example.sculptify.HEIGHT_AND_WEIGHT
import com.example.sculptify.layout.InputField
import com.example.sculptify.layout.RegConfirmButton
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun NameAndYOB(navController: NavHostController) {
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
                Text(
                    text = "Your name is",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold
                )
                InputField(
                    value = regFirstName,
                    onValueChange = { regFirstName = it },
                    label = "",
                    keyboardType = KeyboardType.Text,
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = null,
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(40.dp, 10.dp, 40.dp, 10.dp)
                        .width(200.dp)
                )
                Text(
                    text = "and you were born in",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold
                )
                InputField(
                    value = regYearOfBirth,
                    onValueChange = { regYearOfBirth = it },
                    label = "",
                    keyboardType = KeyboardType.Number,
                    visualTransformation = VisualTransformation.None,
                    trailingIcon = null,
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .padding(40.dp, 10.dp, 40.dp, 10.dp)
                        .width(200.dp)
                )
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                RegConfirmButton(
                    text = "BACK",
                    bgColor = Color(0xff1C1C1E),
                    modifier = Modifier
                        .width(100.dp)
                        .padding(end = 10.dp)
                        .height(60.dp)
                        .clickable {
                            navController.navigate(EMAIL_AND_PASSWORD)
                        },
                )
                RegConfirmButton(
                    text = "NEXT",
                    bgColor = if (regFirstName.isNotEmpty() && regYearOfBirth.isNotEmpty()) {
                        Color(0xff0060FE)
                    } else Color(0xff0060FE).copy(alpha = 0.2f),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable {
                            if (regFirstName.isNotEmpty() && regYearOfBirth.isNotEmpty()) {
                                navController.navigate(HEIGHT_AND_WEIGHT)
                            }
                        }
                )
            }
        }
    }
}