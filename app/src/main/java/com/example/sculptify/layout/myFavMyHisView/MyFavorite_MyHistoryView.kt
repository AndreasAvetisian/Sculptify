package com.example.sculptify.layout.myFavMyHisView

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.sculptify.data.myFavoriteMyHistory.MyFavMyHisTabItem
import com.example.sculptify.layout.general.topBars.TopBarWithTabRow
import com.example.sculptify.pages.MyFavoriteView
import com.example.sculptify.pages.MyHistoryView
import com.example.sculptify.screens.Screen

val MFMH_TabItems = listOf(
    MyFavMyHisTabItem(title = "Favorite"),
    MyFavMyHisTabItem(title = "History")
)

var selectedTabIndexForMFMH by mutableIntStateOf(0)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyFavorite_MyHistoryView(navController: NavHostController) {

    val deviceWidthDp = LocalConfiguration.current.screenWidthDp
    val deviceHeightDp = LocalConfiguration.current.screenHeightDp

    val myFavMyHisPagerState = rememberPagerState { MFMH_TabItems.size }

    LaunchedEffect(selectedTabIndexForMFMH) {
        myFavMyHisPagerState.animateScrollToPage(selectedTabIndexForMFMH)
    }

    LaunchedEffect(myFavMyHisPagerState.currentPage, myFavMyHisPagerState.isScrollInProgress) {
        if (!myFavMyHisPagerState.isScrollInProgress) {
            selectedTabIndexForMFMH = myFavMyHisPagerState.currentPage
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
            selectedTabIndex = selectedTabIndexForMFMH,
            onTabSelected = {
                selectedTabIndexForMFMH = it
            },
            route = Screen.MFMH.route,
            navController = navController
        )
        HorizontalPager(
            state = myFavMyHisPagerState,
            modifier = Modifier
                .fillMaxSize()
        ) {index ->
            when (index) {
                0 -> { MyFavoriteView(navController) }
                1 -> { MyHistoryView() }
            }
        }
    }
}