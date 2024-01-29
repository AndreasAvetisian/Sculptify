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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun AchievementItem(
    title: String,
    description: String,
    isActivated: Boolean
) {
    Card (
        colors = CardDefaults.cardColors(Color(0xff1C1C1E)),
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
                        if (isActivated) Color(0xff0060FE) else Color(0xff0060FE).copy(0.2f)
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
                            tint = Color.Black,
                            modifier = Modifier.size(55.dp)
                        )
                    }
                }
                Column (
                    modifier = Modifier
                        .fillMaxHeight(),
                ) {
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = description,
                        color = Color(0xff909090),
                        fontSize = 14.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold
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
                        Color(0xff00ff00)
                    } else {
                        Color(0xff787878)
                    },
                    modifier = Modifier.size(35.dp)
                )
            }
        }
    }
}