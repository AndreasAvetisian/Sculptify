package com.example.sculptify.layout.dayStreakActiveDaysView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun DayStreakView() {
    Column (
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.fire_flame),
                    contentDescription = "",
                    tint = Color(0xffFF4E28)
                )
                Text(
                    text = "0",
                    fontSize = 60.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xffffffff),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 80.dp),
                )
            }
            Text(
                text = "Day Streak",
                fontSize = 36.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xffFCFCFC),
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )
        }

        DSADBottomBar()
    }
}