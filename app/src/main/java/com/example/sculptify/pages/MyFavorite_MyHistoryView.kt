package com.example.sculptify.pages

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.data.MyFavMyHisTabItem
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.main.MAIN_ROUTE
import com.example.sculptify.ui.theme.balooFontFamily

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyFavorite_MyHistoryView(navController: NavHostController) {

    val deviceWidthDp = LocalConfiguration.current.screenWidthDp
    val deviceHeightDp = LocalConfiguration.current.screenHeightDp

    val myFavMyHisTabItems = listOf(
        MyFavMyHisTabItem(title = "History"),
        MyFavMyHisTabItem(title = "Favorite")
    )

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val myFavMyHisPagerState = rememberPagerState { myFavMyHisTabItems.size }

    LaunchedEffect(selectedTabIndex) {
        myFavMyHisPagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(myFavMyHisPagerState.currentPage, myFavMyHisPagerState.isScrollInProgress) {
        if (!myFavMyHisPagerState.isScrollInProgress) {
            selectedTabIndex = myFavMyHisPagerState.currentPage
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        TopBarView(
            title = "My",
            onClick = {
                navController.popBackStack()
                navController.navigate(MAIN_ROUTE)
            }
        )
        Column (
            modifier = Modifier
                .fillMaxSize()
//                .background(Color.Cyan)
        ) {
            TabRow(
                modifier = Modifier
                    .fillMaxWidth(),
                selectedTabIndex = selectedTabIndex,
                containerColor = Color.Transparent,
                divider = {},
                indicator = { tabPositions ->

                    val indicatorOffset by animateDpAsState(
                        targetValue = tabPositions[selectedTabIndex].left,
                        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing), label = "a"
                    )
                    val selectedTabPosition = tabPositions[selectedTabIndex]

                    TabRowDefaults.Indicator(
                        color = Color(0xff0060FE),
                        modifier = Modifier
                            .padding(start = 0.dp, end = (deviceWidthDp/2).dp, top = (deviceHeightDp * 0.059).dp) // Adjust padding as needed
                            .width(selectedTabPosition.width) // Adjust width as needed
                            .offset(x = indicatorOffset)
                    )
                },
            ) {
                myFavMyHisTabItems.forEachIndexed { index, item ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
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
            HorizontalPager(
                state = myFavMyHisPagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {index ->
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = myFavMyHisTabItems[index].title,
                        fontSize = 20.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xffFCFCFC)
                    )
                }
            }
        }
    }
}