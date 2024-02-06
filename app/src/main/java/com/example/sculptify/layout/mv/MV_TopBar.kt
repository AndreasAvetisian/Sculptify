package com.example.sculptify.layout.mv

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Light_Gray
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@SuppressLint("SimpleDateFormat")
@Composable
fun MV_TopBar() {
    val sdf = SimpleDateFormat("EEEE, MMMM dd")
    val currentDateAndTime = sdf.format(Date())

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp, top = 15.675.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        CustomText(
            text = currentDateAndTime.uppercase(Locale.ROOT),
            fontSize = 18.sp,
            color = Light_Gray
        )
        CustomText(
            text = "SCULPTIFY - HOME WORKOUT",
            fontSize = 24.sp
        )
    }
}