package com.example.sculptify.layout.general.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.R
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.layout.mpv.MPV_BodyParameters
import com.example.sculptify.layout.settings.general.GS_ReadMe
import com.example.sculptify.layout.settings.workout.WS_TimerSettings
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun OpenableLineButton(
    text: String,
    isProfileView: Boolean,
    isTimerSettingsView: Boolean,
    isReadMeView: Boolean
) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    var isOpen by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xff1C1C1E))
                .height(56.dp)
                .clickable {
                    isOpen = !isOpen
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.675.dp, 10.dp, 17.675.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomText(text = text)
                Icon(
                    modifier = Modifier
                        .scale(scaleX = -1f, scaleY = 1f)
                        .rotate(if (isOpen) -90f else 0f)
                        .padding(0.dp, 3.dp, 0.dp, 0.dp),
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "arrow",
                    tint = Color.White
                )
            }
        }
        if (isOpen) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xff1C1C1E)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (isProfileView) {
                    MPV_BodyParameters()
                }
                if (isTimerSettingsView) {
                    WS_TimerSettings()
                }
                if (isReadMeView) {
                    GS_ReadMe()
                }
            }
        }
    }
}