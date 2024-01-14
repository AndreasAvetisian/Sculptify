package com.example.sculptify.layout.mbs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun MBSButton(
    iconId: Painter,
    iconDescription: String,
    text: String,
    cardColor: CardColors,
    onClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .padding(15.675.dp, 0.dp, 15.675.dp, 0.dp)
            .fillMaxWidth()
            .height(71.25.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card (
                colors = cardColor,
                modifier = Modifier
                    .width(39.9.dp)
                    .height(39.9.dp)
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = iconId,
                        tint = Color.White,
                        contentDescription = iconDescription
                    )
                }
            }
            Text(
                modifier = Modifier
                    .padding(15.675.dp, 0.dp, 0.dp, 0.dp),
                text = text,
                fontSize = 17.1.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xffFCFCFC)
            )
        }
        Row {
            Icon(
                modifier = Modifier
                    .scale(scaleX = -1f, scaleY = 1f),
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "arrow",
                tint = Color.White
            )
        }
    }
}