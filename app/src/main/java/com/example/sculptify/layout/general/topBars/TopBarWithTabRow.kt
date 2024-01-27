package com.example.sculptify.layout.general.topBars

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.dayStreakActiveDaysView.DayStreak_ActiveDays_TabItems
import com.example.sculptify.main.DAY_STREAK_ACTIVE_DAYS_ROUTE
import com.example.sculptify.layout.msv.MyStatistics_TabItems
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun TopBarWithTabRow(
    indicatorWidth: Dp,
    indicatorHeight: Dp,
    selectedTabIndex: Int,
    onBackButtonClick: () -> Unit,
    onTabSelected: (Int) -> Unit,
    route: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.675.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Card(
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .clickable {
                    onBackButtonClick()
                },
            shape = RoundedCornerShape(15.dp),
            colors = CardDefaults.cardColors(Color(0xff1C1C1E))
        ) {
            Row(
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
                    tint = Color.White
                )
            }
        }

        TabRow(
            modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.Transparent,
            divider = {},
            indicator = { tabPositions ->

                val indicatorOffset by animateDpAsState(
                    targetValue = tabPositions[selectedTabIndex].left,
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = FastOutSlowInEasing
                    ), label = "a"
                )
                val selectedTabPosition = tabPositions[selectedTabIndex]

                TabRowDefaults.Indicator(
                    color = Color(0xff0060FE),
                    modifier = Modifier
                        .padding(
                            start = 30.dp,
                            end = indicatorWidth,
                            top = indicatorHeight
                        )
                        .width(selectedTabPosition.width) // Adjust width as needed
                        .offset(x = indicatorOffset)
                )
            },
        ) {
            when (route) {
                DAY_STREAK_ACTIVE_DAYS_ROUTE -> {
                    DayStreak_ActiveDays_TabItems.forEachIndexed { index, item ->
                        Tab(
                            selected = index == selectedTabIndex,
                            onClick = {
                                onTabSelected(index)
                            },
                            text = {
                                Text(
                                    text = item.title,
                                    fontSize = 20.sp,
                                    fontFamily = balooFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = if (index == selectedTabIndex) {
                                        item.selectedItem
                                    } else item.unselectedItem
                                )
                            }
                        )
                    }
                }
                else -> {
                    MyStatistics_TabItems.forEachIndexed { index, item ->
                        Tab(
                            selected = index == selectedTabIndex,
                            onClick = {
                                onTabSelected(index)
                            },
                            text = {
                                Text(
                                    text = item.title,
                                    fontSize = 20.sp,
                                    fontFamily = balooFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    color = if (index == selectedTabIndex) {
                                        item.selectedItem
                                    } else item.unselectedItem
                                )
                            }
                        )
                    }
                }
            }

        }
    }
}