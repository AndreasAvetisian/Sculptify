package com.example.sculptify.layout.settings.general.reminder

import android.util.Log
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Light_Gray
import com.example.sculptify.ui.theme.Pale_Gray
import com.example.sculptify.ui.theme.Red
import com.example.sculptify.ui.theme.Transparent
import com.example.sculptify.ui.theme.White
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun ReminderItem(
    time: String,
    days: List<String>,
    isSwitchActive: Boolean,
    onSwitchChanged: ((Boolean) -> Unit),
    onEditClicked: () -> Unit,
    onDeletedClicked: () -> Unit
) {
    val edit = SwipeAction(
        onSwipe = {
            onEditClicked()
            Log.d("AAAAAAAAAAAAAAAAAAAAa", "editing")
        },
        icon = {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Blue),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.End
            ) {
                Row (
                    modifier = Modifier
                        .size(70.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = null,
                        tint = White
                    )
                }
            }
        },
        background = Blue
    )

    val delete = SwipeAction(
        onSwipe = {
            onDeletedClicked()
            Log.d("AAAAAAAAAAAAAAAAAAAAa", "delete")
        },
        icon = {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Red),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Row (
                    modifier = Modifier
                        .size(70.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp),
                        imageVector = Icons.Rounded.Delete,
                        contentDescription = null,
                        tint = White
                    )
                }
            }
        },
        background = Red
    )

    SwipeableActionsBox(
        modifier = Modifier
            .padding(bottom = 15.675.dp),
        backgroundUntilSwipeThreshold = Transparent,
        swipeThreshold = 100.dp,
        startActions = listOf(edit),
        endActions = listOf(delete)
    ) {
        Card (
            colors = CardDefaults.cardColors(Dark_Gray),
            shape = RoundedCornerShape(
                topStart = 25.dp,
                topEnd = 25.dp,
                bottomEnd = 25.dp,
                bottomStart = 25.dp),
            modifier = Modifier
                .height(160.dp)
                .clickable {
                    onEditClicked()
                }
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.675.dp),
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CustomText(
                        text = time,
                        fontSize = 40.sp
                    )
                    Switch(
                        checked = isSwitchActive,
                        onCheckedChange = onSwitchChanged,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = White,
                            checkedTrackColor = Blue,
                            checkedBorderColor = Blue,
                            uncheckedThumbColor = Light_Gray,
                            uncheckedTrackColor = Pale_Gray,
                            uncheckedBorderColor = Pale_Gray
                        )
                    )
                }
                Divider(
                    thickness = 2.dp,
                    color = Light_Gray,
                    modifier = Modifier
                        .fillMaxWidth()
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
    }
}