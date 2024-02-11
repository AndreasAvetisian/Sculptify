package com.example.sculptify.layout.wdv

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sculptify.R
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.White

@Composable
fun WDV_TopBar(navController: NavHostController) {
    Row (
        modifier = Modifier
            .padding(15.675.dp, 22.8.dp, 15.675.dp, 22.8.dp)
            .fillMaxWidth()
            .height(31.35.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Card (
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .clickable {
                    navController.popBackStack()
                },
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(Dark_Gray)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp),
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "arrow",
                    tint = White
                )
            }
        }
        Card (
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .clickable {
                    navController.popBackStack()
                },
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(Dark_Gray)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier
                        .width(16.dp)
                        .height(16.dp),
                    painter = painterResource(id = R.drawable.favorite),
                    contentDescription = "arrow",
                    tint = White
                )
            }
        }
    }
}