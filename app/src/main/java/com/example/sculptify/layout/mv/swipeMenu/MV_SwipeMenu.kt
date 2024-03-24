package com.example.sculptify.layout.mv.swipeMenu

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sculptify.data.mv.MVSwipeMenuTabItem
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.mv.swipeMenu.recentTab.MV_RecentSwipeTab
import com.example.sculptify.layout.mv.swipeMenu.updateTab.MV_UpdatesSwipeTab
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Transparent


val MV_SwipeMenuTabItems = listOf(
    MVSwipeMenuTabItem(
        title = "Recent",
        description = "Lately enjoyed. Keep on!"

    ),
    MVSwipeMenuTabItem(
        title = "Updates",
        description = "More updates soon!"
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MV_SwipeMenu(navController: NavHostController) {

    val deviceWidthDp = LocalConfiguration.current.screenWidthDp
    val deviceHeightDp = LocalConfiguration.current.screenHeightDp

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val mvSwipeMenuPagerState = rememberPagerState { MV_SwipeMenuTabItems.size }

    LaunchedEffect(selectedTabIndex) {
        mvSwipeMenuPagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(mvSwipeMenuPagerState.currentPage, mvSwipeMenuPagerState.isScrollInProgress) {
        if (!mvSwipeMenuPagerState.isScrollInProgress) {
            selectedTabIndex = mvSwipeMenuPagerState.currentPage
        }
    }

    TabRow(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(bottom = 20.dp),
        selectedTabIndex = selectedTabIndex,
        containerColor = Transparent,
        divider = {},
        indicator = { tabPositions ->

            val indicatorOffset by animateDpAsState(
                targetValue = tabPositions[selectedTabIndex].left,
                animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing), label = "a"
            )
            val selectedTabPosition = tabPositions[selectedTabIndex]

            TabRowDefaults.Indicator(
                color = Blue,
                modifier = Modifier
                    .padding(
                        start = 25.dp,
                        end = (deviceWidthDp / 3).dp,
                        top = (deviceHeightDp * 0.059).dp
                    )
                    .width(selectedTabPosition.width) // Adjust width as needed
                    .offset(x = indicatorOffset)
            )
        },
    ) {
        MV_SwipeMenuTabItems.forEachIndexed { index, item ->
            Tab(
                selected = index == selectedTabIndex,
                onClick = {
                    selectedTabIndex = index
                },
                text = {
                    CustomText(
                        text = item.title,
                        color = if (index == selectedTabIndex) {
                            item.selectedItem
                        } else item.unselectedItem
                    )
                }
            )
        }
    }
    HorizontalPager(
        state = mvSwipeMenuPagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {index ->
        Card (
            colors = CardDefaults.cardColors(Dark_Gray),
            shape = MaterialTheme.shapes.large,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(horizontal = 10.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.675.dp)
            ) {
                when (index) {
                    0 -> { MV_RecentSwipeTab(navController = navController) }
                    1 -> { MV_UpdatesSwipeTab() }
                }
            }
        }
    }
}