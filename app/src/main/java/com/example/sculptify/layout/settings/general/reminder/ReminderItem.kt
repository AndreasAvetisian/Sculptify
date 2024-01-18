package com.example.sculptify.layout.settings.general.reminder

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun ReminderItem(
    time: String,
    days: List<String>
) {
    var checked by remember { mutableStateOf(false) }

    Card (
        colors = CardDefaults.cardColors(Color(0xff1C1C1E)),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .padding(bottom = 15.675.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.675.dp)
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
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
                    checked = checked,
                    onCheckedChange = {
                        checked = it
                    },
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
                    .padding(bottom = 10.dp)
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
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