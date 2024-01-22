package com.example.sculptify.layout.settings.general.reminder

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.ui.theme.balooFontFamily
import kotlinx.coroutines.coroutineScope

@Composable
fun ReminderItem(
    time: String,
    days: List<String>,
    isSwitchActive: Boolean,
    onSwitchChanged: ((Boolean) -> Unit),
    isEditClicked: Boolean,
    onDeletedClicked: () -> Unit
) {
    var isDeleteClicked by remember { mutableStateOf(false) }
    val mainWidth = remember { Animatable(1f) }

    LaunchedEffect(isDeleteClicked) {
        if (isDeleteClicked) {
            coroutineScope {
                mainWidth.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(
                        durationMillis = 200,
                        easing = LinearEasing
                    )
                )
            }
        }
    }

    Card (
        colors = CardDefaults.cardColors(Color(0xff1C1C1E)),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .height(160.dp)
            .padding(bottom = 15.675.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (isEditClicked && !isDeleteClicked) {
                Column (
                    modifier = Modifier
                        .width(45.675.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.End
                ) {
                    Card (
                        colors = CardDefaults.cardColors(Color.Red),
                        shape = MaterialTheme.shapes.large,
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                isDeleteClicked = true
                                onDeletedClicked()
                            }
                    ) {
                        Column (
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.delete_icon),
                                contentDescription = "circle delete button",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }
                    }
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth(mainWidth.value)
                    .fillMaxHeight()
                    .padding(horizontal = 15.675.dp),
            ) {
                if (!isDeleteClicked) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = time,
                            fontSize = 40.sp,
                            fontFamily = balooFontFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Switch(
                            checked = isSwitchActive,
                            onCheckedChange = onSwitchChanged,
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = Color(0xff0060FE),
                                checkedBorderColor = Color(0xff0060FE),
                                uncheckedThumbColor = Color(0xff909090),
                                uncheckedTrackColor = Color(0xFFDFDFDF),
                                uncheckedBorderColor = Color(0xFFDFDFDF)
                            )
                        )
                    }
                    Divider(
                        thickness = 2.dp,
                        color = Color(0xff909090),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Yellow)
                    )
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(11.dp)
                    ) {
                        days.forEach { item ->
                            RemindersDaysOfWeek(
                                text = item
                            )
                        }
                    }
                }
            }
            if (isDeleteClicked) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(Color.Red),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Delete",
                        fontSize = 20.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }

    }
}