package com.example.sculptify.layout.mv.swipeMenu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText
import java.util.Locale
@Composable
fun MV_RecentSwipeTab() {
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        CustomText(
            text = MV_SwipeMenuTabItems[0].title.uppercase(Locale.ROOT),
            fontSize = 26.sp
        )
        CustomText(
            text = MV_SwipeMenuTabItems[0].description,
            fontSize = 14.sp,
            color = Color(0xff909090)
        )
    }
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = MV_SwipeMenuTabItems[0].swipeMenuContent[0].contentIconId),
                    contentDescription = "",
                    tint = Color.Red
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.675.dp)
            ) {
                CustomText(
                    text = MV_SwipeMenuTabItems[0].swipeMenuContent[0].contentTitle,
                )
                CustomText(
                    text = MV_SwipeMenuTabItems[0].swipeMenuContent[0].contentDate,
                    fontSize = 14.sp,
                    color = Color(0xff909090)
                )
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier
                    .size(70.dp)
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = MV_SwipeMenuTabItems[0].swipeMenuContent[1].contentIconId),
                    contentDescription = "",
                    tint = Color.Red
                )
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.675.dp)
            ) {
                CustomText(
                    text = MV_SwipeMenuTabItems[0].swipeMenuContent[1].contentTitle
                )
                CustomText(
                    text = MV_SwipeMenuTabItems[0].swipeMenuContent[1].contentDate,
                    fontSize = 14.sp,
                    color = Color(0xff909090)
                )
            }
        }
    }
}