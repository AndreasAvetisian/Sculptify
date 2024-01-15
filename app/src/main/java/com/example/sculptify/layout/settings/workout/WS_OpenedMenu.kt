package com.example.sculptify.layout.settings.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.layout.ConfirmButton
import com.example.sculptify.layout.mpv.MPV_ModifyInput
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun WS_OpenedMenu() {
    val userVM: UserViewModel = viewModel()
    LaunchedEffect(userVM.userdata.value) {
        userVM.getUserData()
    }

    val userRBE = userVM.userdata.value["rbe"].toString() + " secs"
    val userCBS = userVM.userdata.value["cbs"].toString() + " secs"

    var rbeValue by remember { mutableStateOf("") }
    var cbsValue by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xff1C1C1E)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .padding(15.675.dp, 0.dp, 0.dp, 0.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            MPV_ModifyInput(
                title = "Rest Between Exercises",
                value = rbeValue,
                onValueChange = {
                    if (it.length <= 3) rbeValue = it
                },
                placeholder = userRBE,
                readOnly = false,
                keyboardType = KeyboardType.Number
            )
            MPV_ModifyInput(
                title = "Countdown Before Start",
                value = cbsValue,
                onValueChange = {
                    if (it.length <= 3) cbsValue = it
                },
                placeholder = userCBS,
                readOnly = false,
                keyboardType = KeyboardType.Number
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ConfirmButton(
                text = "Save",
                textColor = Color.White,
                bgColor = if (rbeValue.isNotEmpty() || cbsValue.isNotEmpty()) {
                    Color(0xff0000ff)
                } else {
                    Color(0xff0000ff).copy(0.2f)
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(30.dp)
                    .clickable {

                    }
            )
        }
    }
}