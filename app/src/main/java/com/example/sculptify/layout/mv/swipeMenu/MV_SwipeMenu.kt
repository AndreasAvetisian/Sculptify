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
import com.example.sculptify.R
import com.example.sculptify.data.DataItem
import com.example.sculptify.data.MVTabItem
import com.example.sculptify.ui.theme.balooFontFamily


val MV_TabItems = listOf(
    MVTabItem(
        title = "Recent",
        description = "Lately enjoyed. Keep on!",
        data = listOf(
            DataItem(
                iconId = R.drawable.day_streak_main_icon,
                dataTitle = "Abs Intermediate",
                dataDate = "Nov 18 - First time"
            ),
            DataItem(
                iconId = R.drawable.password_hidden,
                dataTitle = "Abs Beginner",
                dataDate = "Nov 15 - First time"
            )
        )

    ),
    MVTabItem(
        title = "My Plan",
        description = "Lately enjoyed. Keep on!",
        data = listOf(
            DataItem(
                iconId = R.drawable.day_streak_main_icon,
                dataTitle = "Abs Intermediate",
                dataDate = "Nov 17 - First time"
            )
        )
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MV_SwipeMenu() {

    val deviceWidthDp = LocalConfiguration.current.screenWidthDp
    val deviceHeightDp = LocalConfiguration.current.screenHeightDp

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val pagerState = rememberPagerState { MV_TabItems.size }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    TabRow(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(bottom = 20.dp),
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
        MV_TabItems.forEachIndexed { index, item ->
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
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {index ->
        Card (
            colors = CardDefaults.cardColors(Color(0xff1C1C1E)),
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
                    0 -> { MV_RecentSwipeTab() }
                    1 -> { MV_MyPlanSwipeTab() }
                }
            }
        }
    }
}