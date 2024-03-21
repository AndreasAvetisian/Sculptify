package com.example.sculptify.layout.dayStreakActiveDaysView

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sculptify.data.dayStreakActiveDayV.DayStreakActiveDaysTabItem
import com.example.sculptify.layout.general.topBars.TopBarWithTabRow
import com.example.sculptify.layout.mv.buttons.selectedTabIndexForDSAD
import com.example.sculptify.pages.ActiveDaysView
import com.example.sculptify.pages.DayStreakView
import com.example.sculptify.screens.Screen

val DayStreak_ActiveDays_TabItems = listOf(
    DayStreakActiveDaysTabItem(
        title = "Streak",
        defaultDescription = "Work out every day to create a winning streak!",
        updatedDescription = "Great job today! \n Remember to continue tomorrow \n or"
    ),
    DayStreakActiveDaysTabItem(
        title = "Active Days",
        defaultDescription = "Get on with your weekly goals!",
        updatedDescription = "Great job today! \n Remember to continue tomorrow \n or"
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DayStreak_ActiveDaysView(
    navController: NavHostController
) {

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
            .padding(15.675.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarWithTabRow(
            indicatorWidth = (deviceWidthDp / 2).dp,
            indicatorHeight = (deviceHeightDp * 0.059).dp,
            selectedTabIndex = selectedTabIndexForDSAD,
            onTabSelected = {
                selectedTabIndexForDSAD = it
            },
            route = Screen.DSAD.route,
            navController = navController
        )

        HorizontalPager(
            state = dayStreakActiveDayPagerState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {index ->
            when (index) {
                0 -> { DayStreakView() }
                1 -> { ActiveDaysView(navController) }
            }
        }

        DSADBottomBar(navController)
    }
}