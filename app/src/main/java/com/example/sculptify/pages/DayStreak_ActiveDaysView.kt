package com.example.sculptify.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sculptify.data.dayStreakActiveDayV.DayStreakActiveDaysTabItem
import com.example.sculptify.layout.TopBarWithTabRow
import com.example.sculptify.main.MAIN_ROUTE

val DayStreak_ActiveDays_TabItems = listOf(
    DayStreakActiveDaysTabItem(
        title = "Day Streak"
    ),
    DayStreakActiveDaysTabItem(
        title = "Active Days"
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DayStreak_ActiveDaysView(navController: NavHostController) {

    val deviceWidthDp = LocalConfiguration.current.screenWidthDp
    val deviceHeightDp = LocalConfiguration.current.screenHeightDp

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val dayStreakActiveDayPagerState = rememberPagerState { DayStreak_ActiveDays_TabItems.size }

    LaunchedEffect(selectedTabIndex) {
        dayStreakActiveDayPagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(dayStreakActiveDayPagerState.currentPage, dayStreakActiveDayPagerState.isScrollInProgress) {
        if (!dayStreakActiveDayPagerState.isScrollInProgress) {
            selectedTabIndex = dayStreakActiveDayPagerState.currentPage
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp)
    ) {
        TopBarWithTabRow(
            indicatorWidth = (deviceWidthDp / 2).dp,
            indicatorHeight = (deviceHeightDp * 0.059).dp,
            selectedTabIndex = selectedTabIndex,
            onBackButtonClick = {
                navController.navigate(MAIN_ROUTE)
            },
            onTabSelected = {
                selectedTabIndex = it
            }
        )

        HorizontalPager(
            state = dayStreakActiveDayPagerState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Red)
        ) {index ->
            when (index) {
                0 -> { Text(text = "a", color = Color.White) }
                1 -> { Text(text = "b", color = Color.White) }
            }
        }
    }
}