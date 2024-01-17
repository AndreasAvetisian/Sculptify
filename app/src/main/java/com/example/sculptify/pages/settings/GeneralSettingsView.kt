package com.example.sculptify.pages.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.R
import com.example.sculptify.layout.general.buttons.OpenableLineButton
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.main.MAIN_ROUTE
import com.example.sculptify.main.REMINDER_ROUTE
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun GeneralSettingsView(navController: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            TopBarView(
                title = "General Settings",
                onClick = {
                    navController.navigate(MAIN_ROUTE)
                }
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xff1C1C1E))
                    .height(56.dp)
                    .clickable {
                        navController.navigate(REMINDER_ROUTE)
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.675.dp, 10.dp, 17.675.dp, 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Reminder",
                        fontSize = 20.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Icon(
                        modifier = Modifier
                            .scale(scaleX = -1f, scaleY = 1f)
                            .padding(0.dp, 3.dp, 0.dp, 0.dp),
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = "arrow",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            OpenableLineButton(
                text = "Read Me",
                isProfileView = false,
                isTimerSettingsView = false,
                isReadMeView = true
            )
        }
    }
}