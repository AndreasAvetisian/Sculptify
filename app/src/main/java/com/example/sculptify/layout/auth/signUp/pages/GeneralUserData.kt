package com.example.sculptify.layout.auth.signUp.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.pages.auth.regFirstName
import com.example.sculptify.pages.auth.regHeight
import com.example.sculptify.pages.auth.regWeight
import com.example.sculptify.pages.auth.regYearOfBirth
import com.example.sculptify.layout.auth.AuthField
import com.example.sculptify.layout.auth.signUp.BodyParameter
import com.example.sculptify.layout.auth.signUp.RegGenderSelection
import com.example.sculptify.layout.auth.signUp.YOB_Slider
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun GeneralUserData() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.675.dp, 100.dp, 15.675.dp, 0.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AuthField(
            value = regFirstName,
            onValueChange = { regFirstName = it },
            label = "Name",
            visualTransformation = VisualTransformation.None,
            trailingIcon = null,
            textStyle = TextStyle(
                fontSize = 20.sp,
                color = Color.White,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            containerColor = Dark_Gray,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        )
        YOB_Slider(
            yearsList = (1970..2024).toList(),
            onYearsChanged = { regYearOfBirth = it.toString() },
        )
        BodyParameter(
            text = "Height: ",
            value = "$regHeight cm",
            onValueIncreaseClick = {
                if (regHeight < 250) {
                    regHeight++
                }
            },
            onValueDecreaseClick = {
                if (regHeight > 100) {
                    regHeight--
                }
            },
        )
        BodyParameter(
            text = "Weight: ",
            value = "$regWeight kg",
            onValueIncreaseClick = {
                if (regWeight < 250) {
                    regWeight += 0.5f
                }
            },
            onValueDecreaseClick = {
                if (regWeight > 30) {
                    regWeight -= 0.5f
                }
            },
        )
        RegGenderSelection()
    }
}