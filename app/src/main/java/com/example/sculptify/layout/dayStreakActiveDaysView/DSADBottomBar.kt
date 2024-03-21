package com.example.sculptify.layout.dayStreakActiveDaysView

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.mv.buttons.selectedTabIndexForDSAD
import com.example.sculptify.screens.Screen
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.White
import com.example.sculptify.viewModels.UserViewModel
import java.time.LocalDate

@Composable
fun DSADBottomBar(
    navController: NavHostController
) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val userData by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value
    )

    val lastIncrementedDate = userData["lastIncrementedDate"] ?: LocalDate.now().toString()

    val today = LocalDate.now().toString()

    Log.d("AAAAAAAAAAAAAAAAAAAAAA", "$lastIncrementedDate == $today}")

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        CustomText(
            text = if (selectedTabIndexForDSAD == 0) {
                if (lastIncrementedDate == today) {
                    DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].updatedDescription
                } else {
                    DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].defaultDescription
                }
            } else {
                if (lastIncrementedDate == today) {
                    DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].updatedDescription
                } else {
                    DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].defaultDescription
                }
            },
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .padding(bottom = 10.dp),
        )
        Card (
            colors = CardDefaults.cardColors(Blue),
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .clickable {
                    navController.navigate(Screen.Main.route)
                }
        ) {
            Row (
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = if (lastIncrementedDate != today) {
                    Arrangement.SpaceBetween
                } else {
                    Arrangement.Center
                }
            ) {
                if (lastIncrementedDate != today) {
                    Icon(
                        Icons.Rounded.Add,
                        contentDescription = "",
                        tint = White,
                        modifier = Modifier
                            .size(
                                if (!DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].isActivatedButton) {
                                    50.dp
                                } else {
                                    0.dp
                                }
                            )
                    )
                }
                CustomText(
                    text = if (selectedTabIndexForDSAD == 0) {
                        if (lastIncrementedDate == today) {
                            DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].activatedButtonText
                        } else {
                            DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].notActivatedButtonText
                        }
                    } else {
                        if (lastIncrementedDate == today) {
                            DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].activatedButtonText
                        } else {
                            DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].notActivatedButtonText
                        }
                    }
                )
                if (lastIncrementedDate != today) {
                    Spacer(modifier = Modifier.size(
                        if (!DayStreak_ActiveDays_TabItems[selectedTabIndexForDSAD].isActivatedButton) {
                            50.dp
                        } else {
                            0.dp
                        }
                    ))
                }
            }
        }
    }
}