package com.example.sculptify.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.layout.dayStreakActiveDaysView.adv.customCPI.CustomCPI
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.mbs.MBS
import com.example.sculptify.viewModels.UserViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActiveDaysView(navController: NavHostController) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val userData by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value
    )

    val weeklyGoalValue = userData["weeklyGoal"]?.toString() ?: 0

    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    // Callback function to be passed to MeMBS
    val onBottomSheetDismiss: () -> Unit = {
        showBottomSheet = false
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        CustomCPI(
            modifier = Modifier
                .size(250.dp),
            currentValue = 4,
            primaryColor = Color(0xff0060FE),
            secondaryColor = Color(0xff1C1C1E),
            circleRadius = 320f,
            maxValue = weeklyGoalValue.toString().toInt()
        )
        Row (
            modifier = Modifier
                .clickable {
                    showBottomSheet = true
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CustomText(
                text = "Weekly Goal",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
            Icon(
                Icons.Rounded.Edit,
                contentDescription = "",
                tint = Color(0xff909090),
                modifier = Modifier
                    .size(16.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            val startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
            val daysOfWeek = (0 until 7).map { startOfWeek.plusDays(it.toLong()) }

            daysOfWeek.forEach { date ->
                val isCurrentDate = date == LocalDate.now()

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        colors = CardDefaults.cardColors(Color(0xff3D3D3D)),
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier
                            .size(35.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CustomText(
                                text = date.dayOfMonth.toString(),
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "",
                        tint = if (isCurrentDate) Color(0xff0060FE) else Color.Transparent,
                        modifier = Modifier
                            .scale(scaleX = 1f, scaleY = -1f)
                            .padding(end = 2.dp)
                    )
                }
            }
        }
        if (showBottomSheet) {
            MBS(
                sheetState = sheetState,
                scope = scope,
                onDismiss = onBottomSheetDismiss,
                navController = navController
            )
        }
    }
}