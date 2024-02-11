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
import com.example.sculptify.data.auth.RegEditDataItem
import com.example.sculptify.layout.auth.signUp.EditDataButton
import com.example.sculptify.layout.general.customText.CustomText
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

@Composable
fun Confirmation() {
    val dataItems = listOf(
        RegEditDataItem("Email: $regEmail") { regPageCounter = 1 },
        RegEditDataItem("Password: ********") {
            regPageCounter = 1
            regPw = ""
            regConfirmPw = ""
        },
        RegEditDataItem("Name: $regFirstName") { regPageCounter = 2 },
        RegEditDataItem("Year of Birth: $regYearOfBirth") { regPageCounter = 2 },
        RegEditDataItem("Height: $regHeight cm") { regPageCounter = 2 },
        RegEditDataItem("Weight: $regWeight kg") { regPageCounter = 2 },
        RegEditDataItem("Gender: $regGender") { regPageCounter = 2 },
        RegEditDataItem(
            "Weekly Goal: $regWeeklyGoal ${
                if (regWeeklyGoal == 1) "time/week" else "times/week"
            }"
        ) { regPageCounter = 3 }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp),
        verticalArrangement = if (!isAccountBeingCreated) Arrangement.spacedBy(30.dp) else Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!isAccountBeingCreated) {
            CustomText(text = "Confirm your data:", fontSize = 30.sp)
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                dataItems.forEach { item ->
                    EditDataButton(text = item.text, onClick = item.onClick)
                }
            }
        } else {
            CustomText(text = "Creating an account...", fontSize = 30.sp)
        }
    }
}