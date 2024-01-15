package com.example.sculptify.layout.dayStreakActiveDaysView

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.layout.mv.buttons.selectedTabIndexForDSAD
import com.example.sculptify.main.MAIN_ROUTE
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun DSADBottomBar(
    navController: NavHostController
) {
    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (selectedTabIndexForDSAD == 0) {
                DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].defaultDescription
            } else {
                DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].defaultDescription
            },
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = balooFontFamily,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(bottom = 10.dp),
        )
        Card (
            colors = CardDefaults.cardColors(Color(0xff0060FE)),
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable {
                    navController.navigate(MAIN_ROUTE)
                }
        ) {
            Row (
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier
                        .size(
                            if (!DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].isActivatedButton) {
                                50.dp
                            } else {
                                0.dp
                            }
                        )
                )
                Text(
                    text = if (selectedTabIndexForDSAD == 0) {
                        DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].notActivatedButtonText
                    } else {
                        DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].notActivatedButtonText
                    },
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.size(
                    if (!DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].isActivatedButton) {
                        50.dp
                    } else {
                        0.dp
                    }
                ))
            }
        }
    }
}