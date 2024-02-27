package com.example.sculptify.layout.wv

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.White
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun WV_CancelMenu(
    onResumeClick: () -> Unit,
    onQuitClick: () -> Unit,
    percentage: Int,
    exercisesLeft: Int
) {
    val spanStyle = SpanStyle(
        fontSize = 20.sp,
        fontFamily = balooFontFamily,
        fontWeight = FontWeight.Bold,
        color = Black,
        textDecoration = TextDecoration.None
    )

    val percentageText = buildAnnotatedString {
        withStyle(style = spanStyle) {
            append("You have finished ")
        }
        withStyle(style = spanStyle.copy(color = Blue)) {
            append("${percentage}%")
        }
    }

    val exercisesLeftText = buildAnnotatedString {
        withStyle(style = spanStyle) {
            append("Only ")
        }
        withStyle(style = spanStyle.copy(color = Blue)) {
            append("$exercisesLeft ${if (exercisesLeft == 1) "exercise" else "exercises"} ")
        }
        withStyle(style = spanStyle) {
            append("left")
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(White.copy(0.8f))
            .padding(15.675.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomText(
                    text = "Hold on!",
                    color = Black,
                    fontSize = 40.sp
                )
                CustomText(
                    text = "You can do it!",
                    color = Black,
                    fontSize = 40.sp
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = percentageText
                )
                Text(
                    text = exercisesLeftText
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ConfirmButton(
                    text = "Resume",
                    bgColor = Blue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    onClick = {
                        onResumeClick()
                    }
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable {
                            onQuitClick()
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CustomText(
                        text = "Quit",
                        color = Dark_Gray
                    )
                }
            }
        }
    }
}