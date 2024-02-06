package com.example.sculptify.layout.settings.general.reminder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sculptify.R
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Light_Gray
import com.example.sculptify.ui.theme.Yellow

@Composable
fun EmptyListIcon() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 220.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(Dark_Gray),
            shape = CircleShape,
            modifier = Modifier
                .size(185.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.reminder_icon),
                    contentDescription = "",
                    tint = Yellow,
                    modifier = Modifier
                )
            }
        }
        CustomText(
            text = "Please set your reminder",
            color = Light_Gray
        )
    }
}