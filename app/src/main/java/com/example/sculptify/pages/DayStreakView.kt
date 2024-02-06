package com.example.sculptify.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.R
import com.example.sculptify.layout.dayStreakActiveDaysView.dsv.DS_Slider
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Dark_Orange
import com.example.sculptify.viewModels.UserViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun DayStreakView() {
    val userVM: UserViewModel = viewModel()

    val userData by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value
    )

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val dayStreakValue = userData["dayStreak"].toString()

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.fire_flame),
                    contentDescription = "",
                    tint = Dark_Orange
                )
                CustomText(
                    text = dayStreakValue,
                    fontSize = 60.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 80.dp)
                )
            }
            CustomText(
                text = "Day Streak",
                fontSize = 36.sp,
                modifier = Modifier
                    .padding(vertical = 10.dp)
            )
            DS_Slider()
        }
    }
}