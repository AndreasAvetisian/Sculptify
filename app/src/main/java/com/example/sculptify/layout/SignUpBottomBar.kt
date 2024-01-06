package com.example.sculptify.layout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun SignUpBottomBar(
    navController: NavHostController,
    backOnClick: () -> Unit,
    nextOnClick: () -> Unit,
    nextBgColor: Color
) {
    Row {
        RegConfirmButton(
            text = "LOG IN",
            bgColor = Color(0xff1C1C1E),
            modifier = Modifier
                .width(100.dp)
                .padding(end = 10.dp)
                .height(60.dp)
                .clickable {
                    backOnClick()
                },
        )
        RegConfirmButton(
            text = "NEXT",
            bgColor = nextBgColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable {
                    nextOnClick()
                }
        )
    }
}