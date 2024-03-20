package com.example.sculptify.layout.dayStreakActiveDaysView.adv

import android.annotation.SuppressLint
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.ADV_Gray
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Transparent
import com.example.sculptify.viewModels.UserViewModel
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

@SuppressLint("SimpleDateFormat")
@Composable
fun ADV_CurrentWeekIndicator() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        val userVM: UserViewModel = viewModel()

        LaunchedEffect(true) {
            userVM.getUserData()
        }

        val userData by userVM.userdata.collectAsState()

        val historyOfWorkouts = userData["historyOfWorkouts"] as? List<Map<String, Any>> ?: emptyList()

        val monthMap = mapOf(
            "Jan" to "01", "Feb" to "02", "Mar" to "03", "Apr" to "04",
            "May" to "05", "Jun" to "06", "Jul" to "07", "Aug" to "08",
            "Sep" to "09", "Oct" to "10", "Nov" to "11", "Dec" to "12"
        )

        val listOfWorkoutDates = historyOfWorkouts.mapNotNull { workout ->
            val date = (workout["date"] as? String)?.split(";")?.getOrNull(1)
            date?.let { dateString ->
                val (monthAbbreviation, dayOfMonth) = dateString.split(' ')
                val month = monthMap[monthAbbreviation]
                "$month-$dayOfMonth"
            }
        }

        val startOfWeek = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY))
        val formatter = DateTimeFormatter.ofPattern("MM-dd")
        val daysOfWeek = (0 until 7)
            .map {
                startOfWeek.plusDays(it.toLong())
            }.map {
                it.format(formatter)
            }

        daysOfWeek.forEach { date ->
            val isCurrentDate = LocalDate.now().toString().contains(date)
            val dayOfMonth = date.split("-")[1].toInt()

            val bfColor = if (listOfWorkoutDates.contains(date)) Blue else ADV_Gray

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    colors = CardDefaults.cardColors(bfColor),
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
                            text = dayOfMonth.toString(),
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