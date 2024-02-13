package com.example.sculptify.layout.mpv

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.buttons.ConfirmDeletion
import com.example.sculptify.screens.Screen
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Red
import com.example.sculptify.ui.theme.White

@Composable
fun MPV_BottomButtonsLayout(
    onSignOutClick: () -> Unit,
    onDeleteAccountClick: () -> Unit
) {
    var isDeletionOpen by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.675.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ConfirmButton(
                text = "Sign out",
                bgColor = Dark_Gray,
                textColor = White,
                onClick = {
                    onSignOutClick()
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(end = 7.5.dp)
            )
            ConfirmButton(
                text = "Delete account",
                bgColor = Dark_Gray,
                textColor = Red,
                onClick = {
                    isDeletionOpen = !isDeletionOpen
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 7.5.dp)
            )
        }
        AnimatedVisibility(
            visible = isDeletionOpen,
            enter = fadeIn(
                initialAlpha = 0.4f
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 250)
            )
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Red)
            ) {
                ConfirmDeletion(
                    onClick = {
                        onDeleteAccountClick()
                        isDeletionOpen = false
                    },
                    text = "Confirm account deletion",
                    route = Screen.MyProfile.route
                )
            }
        }
    }
}