package com.example.sculptify.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.viewModels.AuthenticationViewModel

@Composable
fun ErrorMessage(authVM: AuthenticationViewModel) {
    if (authVM.errorMessage.value.isEmpty()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(34.dp)
        ) {}
    } else {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(34.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = authVM.errorMessage.value,
                fontSize = 18.sp,
                color = Color.Red,
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
            )
        }
    }
}