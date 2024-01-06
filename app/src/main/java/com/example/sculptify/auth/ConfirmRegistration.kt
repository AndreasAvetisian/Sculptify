package com.example.sculptify.auth

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.WEEKLY_GOAL
import com.example.sculptify.layout.RegConfirmButton
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.viewModels.AuthenticationViewModel
import kotlin.math.roundToInt

@Composable
fun ConfirmRegistration(
    navController: NavHostController,
    authVM: AuthenticationViewModel
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
                text = "Done!",
                color = Color.White,
                fontSize = 60.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Let's get started!",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold
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
                        navController.navigate(WEEKLY_GOAL)
                    },
            )
            RegConfirmButton(
                text = "CREATE AN ACCOUNT",
                bgColor = if (regEmail.isNotEmpty() && regPw.isNotEmpty()) {
                    Color(0xff0060FE)
                } else Color(0xff0060FE).copy(alpha = 0.2f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                        if (regEmail.isNotEmpty() && regPw.isNotEmpty()) {
                            authVM.signUpUser(
                                regEmail,
                                regPw,
                                regFirstName,
                                regIsAdmin,
                                regCbs,
                                regRbe,
                                regDayStreak,
                                regWeeklyGoal.roundToInt(),
                                regGender,
                                regHeight.toInt(),
                                regWeight.toInt(),
                                regYearOfBirth.toInt(),
                                navController
                            )

                            regEmail = ""
                            regPw = ""
                            regFirstName = ""
                            regGender = ""
                            regHeight = ""
                            regWeight = ""
                            regYearOfBirth = ""
                        }
                    }
            )
        }
    }
}