package com.example.sculptify.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.sculptify.AUTHENTICATION_ROUTE

@Composable
fun SignUpView(navController: NavHostController) {
    Row {
        Text(
            text = "Back",
            modifier = Modifier
                .clickable {
                    navController.navigate(AUTHENTICATION_ROUTE)
                }
        )
        Text(text = "Hello")
    }
}

