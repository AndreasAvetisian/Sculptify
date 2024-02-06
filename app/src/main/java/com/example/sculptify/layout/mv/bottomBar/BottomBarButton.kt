package com.example.sculptify.layout.mv.bottomBar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.sculptify.data.mv.BottomBarScreen
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.msv.selectedTabIndexForMyStatistics
import com.example.sculptify.main.MAIN_ROUTE
import com.example.sculptify.main.STATISTICS_ROUTE
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Bright_Green
import com.example.sculptify.ui.theme.Dark_Orange
import com.example.sculptify.ui.theme.Transparent
import com.example.sculptify.ui.theme.White

@Composable
fun BottomBarButton(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    val background =
        if (selected) {
            when(screen.route) {
                MAIN_ROUTE -> Blue
                STATISTICS_ROUTE -> Dark_Orange
                else -> Bright_Green
            }
        } else {
            Transparent
        }

    Row (
        modifier = Modifier
            .fillMaxWidth(screen.widthDistribution),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            colors = CardDefaults.cardColors(background),
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .height(40.dp)
                .clip(CircleShape)
                .background(background)
                .clickable(onClick = {
                    navController.navigate(screen.route) {

                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true

                        if (screen.route == STATISTICS_ROUTE) {
                            selectedTabIndexForMyStatistics = 0
                        }
                    }
                })
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = screen.icon),
                    contentDescription = "icon",
                    tint = White
                )
                AnimatedVisibility(visible = selected) {
                    CustomText(
                        text = screen.title,
                        fontSize = if (screen.title == "Achievements") 12.sp else 16.sp
                    )
                }
            }
        }
    }
}