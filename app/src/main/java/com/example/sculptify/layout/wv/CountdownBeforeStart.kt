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
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.White
import java.util.Locale

@Composable
fun CountdownBeforeStart(
    count: String,
    list: List<MutableMap<String, String>>,
    onCancelClick: () -> Unit,
    onStartClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Dark_Gray.copy(0.8f))
            .padding(horizontal = 15.675.dp, vertical = 70.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Card (
                colors = CardDefaults.cardColors(Dark_Gray.copy(0.8f)),
                shape = MaterialTheme.shapes.extraLarge,
                modifier = Modifier
                    .size(40.dp)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            onCancelClick()
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        Icons.Rounded.Close,
                        contentDescription = "",
                        tint = White
                    )
                }
            }
        }
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomText(
                text = "READY TO G0",
                fontSize = 50.sp
            )
            CustomText(
                text = count,
                fontSize = 150.sp
            )
            CustomText(
                text = "Exercise 1/${list.size}",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            CustomText(
                text = list[0]["title"].toString().uppercase(Locale.ROOT),
                fontSize = 24.sp
            )
        }
        ConfirmButton(
            text = "Start!",
            bgColor = White,
            textColor = Black,
            fontSize = 26.sp,
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp),
            onClick = {
                onStartClick()
            }
        )
    }
}