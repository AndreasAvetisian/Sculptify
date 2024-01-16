package com.example.sculptify.layout.auth

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
import com.example.sculptify.layout.general.buttons.ConfirmButton

@Composable
fun SignUpBottomBar(
    backText: String,
    backOnClick: () -> Unit,
    nextText: String,
    nextBgColor: Color,
    nextOnClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .padding(15.675.dp)
    ) {
        ConfirmButton(
            text = backText,
            textColor = Color.White,
            bgColor = Color(0xff1C1C1E),
            modifier = Modifier
                .width(100.dp)
                .padding(end = 10.dp)
                .height(60.dp)
                .clickable {
                    backOnClick()
                },
        )
        ConfirmButton(
            text = nextText,
            textColor = Color.White,
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