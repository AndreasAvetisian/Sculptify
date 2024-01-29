package com.example.sculptify.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText

var regEmail by mutableStateOf("")
var regPw by mutableStateOf("")
var isHiddenPw by mutableStateOf(true)
var regFirstName by mutableStateOf("")
var regWeeklyGoal by mutableFloatStateOf(3f)
var regGender by mutableStateOf("")
var regHeight by mutableStateOf("")
var regWeight by mutableStateOf("")
var regYearOfBirth by mutableStateOf("")

@Composable
fun SignUpView() {
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
                    text = "Hello!",
                    fontSize = 60.sp,
                )
                CustomText(
                    text = "Here are some questions to set up an account for you!",
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}


