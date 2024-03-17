package com.example.sculptify.layout.wv.rs.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.White

@Composable
fun WV_RS_TimerAndAction(
    countdown: String,
    onAddMoreTimeClick: () -> Unit,
    onSkipClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(
            text = "Rest",
            fontSize = 30.sp
        )
        CustomText(
            text = countdown,
            fontSize = 70.sp
        )
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ConfirmButton(
                text = "+20s",
                bgColor = Color(0xff3483FE),
                modifier = Modifier
                    .size(110.dp, 40.dp),
                onClick = {
                    onAddMoreTimeClick()
                }
            )
            Spacer(modifier = Modifier.width(30.dp))
            ConfirmButton(
                text = "SKIP",
                bgColor = White,
                textColor = Blue,
                modifier = Modifier
                    .size(110.dp, 40.dp),
                onClick = {
                    onSkipClick()
                }
            )
        }
    }
}