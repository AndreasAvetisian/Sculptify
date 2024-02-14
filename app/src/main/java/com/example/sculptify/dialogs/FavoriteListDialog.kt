package com.example.sculptify.dialogs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun FavoriteListDialog(userVM: UserViewModel) {
    Dialog(
        onDismissRequest = {
            userVM.onDismissDialog()
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Card (
            colors = CardDefaults.cardColors(Dark_Gray),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(horizontal = 15.675.dp)
                .border(
                    width = 2.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(25.dp)
                )
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.675.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CustomText(text = "Hello")
            }
        }
    }
}