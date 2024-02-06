package com.example.sculptify.layout.av

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Bright_Green
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Light_Gray

@Composable
fun AchievementItem(
    title: String,
    description: String,
    isActivated: Boolean
) {
    Card (
        colors = CardDefaults.cardColors(Dark_Gray),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.675.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.675.dp)
            ) {
                Card (
                    colors = CardDefaults.cardColors(
                        if (isActivated) Blue else Blue.copy(0.2f)
                    ),
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier.size(70.dp)
                ) {
                    Column (
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(
                                id = if (isActivated) {
                                    R.drawable.achievements_icon
                                } else {
                                    R.drawable.lock_closed
                                }
                            ),
                            contentDescription = "",
                            tint = Black,
                            modifier = Modifier.size(55.dp)
                        )
                    }
                }
                Column (
                    modifier = Modifier
                        .fillMaxHeight(),
                ) {
                    CustomText(
                        text = title,
                    )
                    CustomText(
                        text = description,
                        color = Light_Gray,
                        fontSize = 14.sp
                    )
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isActivated) {
                            R.drawable.check
                        } else {
                            R.drawable.lock_closed
                        }
                    ),
                    contentDescription = "",
                    tint =
                    if (isActivated) {
                        Bright_Green
                    } else {
                        Light_Gray
                    },
                    modifier = Modifier.size(35.dp)
                )
            }
        }
    }
}