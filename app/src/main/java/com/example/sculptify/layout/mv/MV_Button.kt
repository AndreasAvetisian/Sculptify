package com.example.sculptify.layout.mv

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun MV_Button(
    data: String,
    iconId: Int,
    iconColor: Color,
    title: String,
    stat: String,
    onClick: () -> Unit,
    width: Float,
    paddingStart: Dp,
    paddingEnd: Dp,
) {
    Card (
        colors = CardDefaults.cardColors(Color(0xff1C1C1E)),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .fillMaxWidth(width)
            .height(140.dp)
            .padding(start = paddingStart, end = paddingEnd)
            .clickable {
                onClick()
            }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(15.675.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = data,
                    fontSize = 40.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = "",
                    tint = iconColor,
                    modifier = Modifier
                        .size(40.dp)
                )
            }
            Column {
                Text(
                    text = title,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    fontSize = 16.sp
                )
                Text(
                    text = stat,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xff909090),
                    fontSize = 16.sp
                )
            }
        }
    }
}