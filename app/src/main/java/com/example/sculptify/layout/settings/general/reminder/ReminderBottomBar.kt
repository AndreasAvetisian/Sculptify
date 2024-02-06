package com.example.sculptify.layout.settings.general.reminder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Light_Gray
import com.example.sculptify.ui.theme.White
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun ReminderBottomBar(
    onAddClick: () -> Unit,
    onEditClick: () -> Unit
) {
    val userVM: UserViewModel = viewModel()

    val userData by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value
    )

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val reminders = userData["reminders"] as? List<Map<String, Any>> ?: emptyList()

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.675.dp, 0.dp, 15.675.dp, 15.675.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(
            thickness = 2.dp,
            color = Light_Gray,
            modifier = Modifier
                .padding(bottom = 15.675.dp)
        )
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.675.dp)
        ) {
            ConfirmButton(
                text = "Add reminder",
                bgColor = Blue,
                textColor = White,
                modifier = Modifier
                    .fillMaxWidth(
                        if (reminders.isNotEmpty()) 0.7f else 1f
                    )
                    .height(60.dp),
                onClick = {
                    onAddClick()
                }
            )
            if (reminders.isNotEmpty()) {
                Card (
                    colors = CardDefaults.cardColors(Blue),
                    shape = MaterialTheme.shapes.extraLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEditClick()
                        }
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CustomText(text = "Edit")
                        Icon(
                            Icons.Rounded.Edit,
                            contentDescription = "",
                            tint = White
                        )
                    }
                }
            }
        }
    }
}