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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.AUTHENTICATION_ROUTE
import com.example.sculptify.EMAIL_AND_PASSWORD
import com.example.sculptify.layout.RegConfirmButton
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.viewModels.AuthenticationViewModel

var regEmail by mutableStateOf("")
var regPw by mutableStateOf("")
var isHiddenPw by mutableStateOf(true)
var regFirstName by mutableStateOf("")
var regIsAdmin by mutableStateOf(false)
var regCbs by mutableIntStateOf(15)
var regRbe by mutableIntStateOf(30)
var regDayStreak by mutableIntStateOf(0)
var regWeeklyGoal by mutableFloatStateOf(3f)
var regGender by mutableStateOf("")
var regHeight by mutableStateOf("")
var regWeight by mutableStateOf("")
var regYearOfBirth by mutableStateOf("")

@Composable
fun SignUpView(
    navController: NavHostController,
    authVM: AuthenticationViewModel
) {
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
                    text = "Hello!",
                    color = Color.White,
                    fontSize = 60.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Here are some questions to set up an account for you!",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Row {
                RegConfirmButton(
                    text = "LOG IN",
                    bgColor = Color(0xff1C1C1E),
                    modifier = Modifier
                        .width(100.dp)
                        .padding(end = 10.dp)
                        .height(60.dp)
                        .clickable {
                            authVM.errorMessage.value = ""
                            navController.navigate(AUTHENTICATION_ROUTE)
                        },
                )
                RegConfirmButton(
                    text = "I'M READY",
                    bgColor = Color(0xff0060FE),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable {
                            navController.navigate(EMAIL_AND_PASSWORD)
                        }
                )
            }
        }
    }
}


