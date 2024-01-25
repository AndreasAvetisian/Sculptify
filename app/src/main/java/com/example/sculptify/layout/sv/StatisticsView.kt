package com.example.sculptify.layout.sv

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sculptify.data.statistics.MyStatisticsTabItem
import com.example.sculptify.layout.general.topBars.TopBarWithTabRow
import com.example.sculptify.main.MAIN_ROUTE
import com.example.sculptify.main.STATISTICS_ROUTE
import com.example.sculptify.pages.MyBMIView
import com.example.sculptify.pages.MyStatisticsView

val MyStatistics_TabItems = listOf(
    MyStatisticsTabItem(
        title = "Statistics"
    ),
    MyStatisticsTabItem(
        title = "BMI"
    )
)

var selectedTabIndexForMyStatistics by mutableIntStateOf(0)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StatisticsView(
    navController: NavHostController
) {
    val deviceWidthDp = LocalConfiguration.current.screenWidthDp
    val deviceHeightDp = LocalConfiguration.current.screenHeightDp

    val myStatisticsPagerState = rememberPagerState { MyStatistics_TabItems.size }

    LaunchedEffect(selectedTabIndexForMyStatistics) {
        myStatisticsPagerState.animateScrollToPage(selectedTabIndexForMyStatistics)
    }

    LaunchedEffect(myStatisticsPagerState.currentPage, myStatisticsPagerState.isScrollInProgress) {
        if (!myStatisticsPagerState.isScrollInProgress) {
            selectedTabIndexForMyStatistics = myStatisticsPagerState.currentPage
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
            selectedTabIndex = selectedTabIndexForMyStatistics,
            onBackButtonClick = {
                navController.navigate(MAIN_ROUTE)
            },
            onTabSelected = {
                selectedTabIndexForMyStatistics = it
            },
            route = STATISTICS_ROUTE
        )
        HorizontalPager(
            state = myStatisticsPagerState,
            modifier = Modifier
                .fillMaxWidth()
        ) {index ->
            when (index) {
                0 -> {
                    MyStatisticsView()
                }
                1 -> {
                    MyBMIView()
                }
            }
        }
    }
}