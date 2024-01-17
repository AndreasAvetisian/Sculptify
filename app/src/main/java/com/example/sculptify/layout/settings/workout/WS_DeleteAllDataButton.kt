package com.example.sculptify.layout.settings.workout

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.buttons.ConfirmDeletion
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun WS_DeleteAllDataButton() {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    var isOpen by remember { mutableStateOf(false) }

    var isPageRefreshed by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.675.dp, 10.dp, 15.675.dp, 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ConfirmButton(
            text = "Delete all data",
            bgColor = Color(0xff1C1C1E),
            textColor = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp),
            onClick = {
                isOpen = !isOpen
            }
        )
    }
    if (isOpen) {
        ConfirmDeletion(
            onClick = {
                userVM.deleteUserData()

                isPageRefreshed = true

                isOpen = false

                Toast
                    .makeText(
                        context,
                        "Data was deleted",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            },
            text = "Confirm deletion"
        )
    }

    if (isPageRefreshed) {
        LaunchedEffect(true) {
            userVM.getUserData()
        }
        isPageRefreshed = false
    }
}