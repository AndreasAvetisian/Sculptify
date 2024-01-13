package com.example.sculptify.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sculptify.data.dayStreakActiveDayV.DayStreakActiveDaysTabItem
import com.example.sculptify.layout.TopBarWithTabRow
import com.example.sculptify.layout.dayStreakActiveDaysView.DayStreakView
import com.example.sculptify.layout.mv.buttons.selectedTabIndexForDSAD
import com.example.sculptify.main.MAIN_ROUTE

val DayStreak_ActiveDays_TabItems = listOf(
    DayStreakActiveDaysTabItem(
        title = "Streak"
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

    val dayStreakActiveDayPagerState = rememberPagerState { DayStreak_ActiveDays_TabItems.size }

    LaunchedEffect(selectedTabIndexForDSAD) {
        dayStreakActiveDayPagerState.animateScrollToPage(selectedTabIndexForDSAD)
    }

    LaunchedEffect(dayStreakActiveDayPagerState.currentPage, dayStreakActiveDayPagerState.isScrollInProgress) {
        if (!dayStreakActiveDayPagerState.isScrollInProgress) {
            selectedTabIndexForDSAD = dayStreakActiveDayPagerState.currentPage
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
            selectedTabIndex = selectedTabIndexForDSAD,
            onBackButtonClick = {
                navController.navigate(MAIN_ROUTE)
            },
            onTabSelected = {
                selectedTabIndexForDSAD = it
            }
        )

        HorizontalPager(
            state = dayStreakActiveDayPagerState,
            modifier = Modifier
                .fillMaxSize()
        ) {index ->
            when (index) {
                0 -> {
                    DayStreakView()
                }
                1 -> {
                    Text(text = "b", color = Color.White)
                }
            }
        }
    }
}