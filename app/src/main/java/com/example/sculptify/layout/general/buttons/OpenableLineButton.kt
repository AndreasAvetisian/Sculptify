package com.example.sculptify.layout.general.buttons

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.R
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.mpv.MPV_BodyParameters
import com.example.sculptify.layout.mpv.MPV_ModifyPassword
import com.example.sculptify.layout.settings.general.GS_ReadMe
import com.example.sculptify.layout.settings.workout.WS_TimerSettings
import com.example.sculptify.screens.Screen
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Light_Gray
import com.example.sculptify.ui.theme.White
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun OpenableLineButton(
    text: String,
    route: String,
    index: Int = 0,
    isOpen: Boolean,
    onClick: () -> Unit,
    pwValue: String = "",
    pwOnValueChange: (String) -> Unit = {},
    confirmPwValue: String = "",
    confirmPwOnValueChange: (String) -> Unit = {},
    onPasswordModified: () -> Unit = {}
) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Dark_Gray)
                .height(56.dp)
                .padding(horizontal = 15.675.dp)
                .clickable {
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            ) {
                CustomText(text = text)
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.675.dp, 0.dp, 0.dp, 0.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement =
                if (index == 0) {
                    Arrangement.SpaceBetween
                } else Arrangement.End
            ) {
                if (index == 0) {
                    CustomText(
                        text = "********",
                        modifier = Modifier
                            .padding(top = 7.dp),
                        color = Light_Gray
                    )
                }
                Icon(
                    modifier = Modifier
                        .scale(scaleX = -1f, scaleY = 1f)
                        .rotate(if (isOpen) -90f else 0f)
                        .padding(0.dp, 3.dp, 0.dp, 0.dp),
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "arrow",
                    tint = White
                )
            }
        }
        AnimatedVisibility(
            visible = isOpen,
            enter = fadeIn(
                initialAlpha = 0.4f
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 250)
            )
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                when(route) {
                    Screen.MyProfile.route -> {
                        when(index) {
                            0 -> {
                                MPV_ModifyPassword(
                                    pwValue = pwValue,
                                    pwOnValueChange = pwOnValueChange,
                                    confirmPwValue = confirmPwValue,
                                    confirmPwOnValueChange = confirmPwOnValueChange,
                                    onPasswordModified = onPasswordModified
                                )
                            }
                            1 -> { MPV_BodyParameters()  }
                        }
                    }
                    Screen.WorkoutSettings.route -> { WS_TimerSettings() }
                    Screen.GeneralSettings.route -> { GS_ReadMe() }
                }
            }
        }
    }
}