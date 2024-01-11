package com.example.sculptify.pages

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.R
import com.example.sculptify.data.MyFavMyHisTabItem
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

    val pagerState = rememberPagerState { myFavMyHisTabItems.size }

    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .padding(15.675.dp, 22.8.dp, 15.675.dp, 22.8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .clickable {
                        navController.popBackStack()
                        navController.navigate(MAIN_ROUTE)
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
            Text(
                text = "My",
                fontSize = 20.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xffFCFCFC)
            )
            Card(
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {}
        } // Top Bar ending
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
                state = pagerState,
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