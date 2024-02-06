package com.example.sculptify.layout.dayStreakActiveDaysView.adv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.ADV_Gray
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Transparent
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters

@Composable
fun ADV_CurrentWeekIndicator() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
        val daysOfWeek = (0 until 7).map { startOfWeek.plusDays(it.toLong()) }

        daysOfWeek.forEach { date ->
            val isCurrentDate = date == LocalDate.now()

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    colors = CardDefaults.cardColors(ADV_Gray),
                    shape = MaterialTheme.shapes.extraLarge,
                    modifier = Modifier
                        .size(35.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CustomText(
                            text = date.dayOfMonth.toString(),
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Icon(
                    Icons.Default.ArrowDropDown,
                    contentDescription = "",
                    tint = if (isCurrentDate) Blue else Transparent,
                    modifier = Modifier
                        .scale(scaleX = 1f, scaleY = -1f)
                        .padding(end = 2.dp)
                )
            }
        }
    }
}