package com.example.sculptify.layout.auth.signUp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.pages.auth.isAccountBeingCreated
import com.example.sculptify.pages.auth.regConfirmPw
import com.example.sculptify.pages.auth.regEmail
import com.example.sculptify.pages.auth.regFirstName
import com.example.sculptify.pages.auth.regGender
import com.example.sculptify.pages.auth.regHeight
import com.example.sculptify.pages.auth.regPageCounter
import com.example.sculptify.pages.auth.regPw
import com.example.sculptify.pages.auth.regWeeklyGoal
import com.example.sculptify.pages.auth.regWeight
import com.example.sculptify.pages.auth.regYearOfBirth
import com.example.sculptify.layout.auth.signUp.EditDataButton
import com.example.sculptify.layout.general.customText.CustomText

@Composable
fun Confirmation() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp),
        verticalArrangement = if (!isAccountBeingCreated) Arrangement.spacedBy(30.dp) else Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isAccountBeingCreated) {
            CustomText(
                text = "Confirm your data:",
                fontSize = 30.sp
            )
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                EditDataButton(
                    text = "Email: $regEmail",
                    onClick = { regPageCounter = 1 }
                )
                EditDataButton(
                    text = "Password: ********",
                    onClick = {
                        regPageCounter = 1
                        regPw = ""
                        regConfirmPw = ""
                    }
                )
                EditDataButton(
                    text = "Name: $regFirstName",
                    onClick = { regPageCounter = 2 }
                )
                EditDataButton(
                    text = "Year of Birth: $regYearOfBirth",
                    onClick = { regPageCounter = 2 }
                )
                EditDataButton(
                    text = "Height: $regHeight cm",
                    onClick = { regPageCounter = 2 }
                )
                EditDataButton(
                    text = "Weight: $regWeight kg",
                    onClick = { regPageCounter = 2 }
                )
                EditDataButton(
                    text = "Gender: $regGender",
                    onClick = { regPageCounter = 2 }
                )
                EditDataButton(
                    text = "Weekly Goal: $regWeeklyGoal ${ if (regWeeklyGoal == 1) "time/week" else "times/week" }",
                    onClick = { regPageCounter = 3 }
                )
            }
        } else {
            CustomText(
                text = "Creating an account...",
                fontSize = 30.sp
            )
        }
    }
}