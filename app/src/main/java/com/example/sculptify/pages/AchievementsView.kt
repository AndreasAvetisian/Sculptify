package com.example.sculptify.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.R
import com.example.sculptify.data.achievements.Achievement
import com.example.sculptify.layout.general.topBars.TopBarView
import com.example.sculptify.main.MAIN_ROUTE
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.viewModels.AchievementsViewModel
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun AchievementsView(navController: NavHostController) {

    val achievementsVM: AchievementsViewModel = viewModel()
    val achievementsState by achievementsVM.achievementsState.collectAsState()

    val userVM: UserViewModel = viewModel()
    val userData by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value
    )

    LaunchedEffect(true) {
        achievementsVM.getAchievements()
        userVM.getUserData()
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopBarView(
            title = "My Achievements",
            onClick = {
                navController.navigate(MAIN_ROUTE)
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.675.dp),
            verticalArrangement = Arrangement.spacedBy(15.675.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(achievementsState.toList()) { (_, achievement) ->
                val isActivated = false
                AchievementItem(
                    achievement = achievement,
                    isActivated = isActivated
                )
            }
        }
    }
}

@Composable
fun AchievementItem(
    achievement: Achievement,
    isActivated: Boolean
) {
    Card (
        colors = CardDefaults.cardColors(Color(0xff1C1C1E)),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.675.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.675.dp)
            ) {
                Card (
                    colors = CardDefaults.cardColors(
                        if (isActivated) Color(0xff0060FE) else Color(0xff0060FE).copy(0.2f)
                    ),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.size(70.dp)
                ) {
                    Column (
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (isActivated) {
                                    R.drawable.achievements_icon
                                } else {
                                    R.drawable.lock_closed
                                }
                            ),
                            contentDescription = "",
                            tint = Color.Black,
                            modifier = Modifier.size(55.dp)
                        )
                    }
                }
                Column (
                    modifier = Modifier
                        .fillMaxHeight(),
                ) {
                    Text(
                        text = achievement.title,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = achievement.conditionDescription,
                        color = Color(0xff909090),
                        fontSize = 14.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isActivated) {
                            R.drawable.check
                        } else {
                            R.drawable.lock_closed
                        }
                    ),
                    contentDescription = "",
                    tint =
                        if (isActivated) {
                            Color(0xff00ff00)
                        } else {
                            Color(0xff787878)
                        },
                    modifier = Modifier.size(35.dp)
                )
            }
        }
    }
}