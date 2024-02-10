package com.example.sculptify.layout.auth.signUp.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText

@Composable
fun Introduction() {
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CustomText(
            text = "Hello!",
            fontSize = 60.sp,
        )
        CustomText(
            text = "Let's start setting up an account for you!",
            fontSize = 30.sp,
            textAlign = TextAlign.Center
        )
    }
}