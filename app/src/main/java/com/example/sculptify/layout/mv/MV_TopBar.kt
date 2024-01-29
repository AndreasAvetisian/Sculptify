package com.example.sculptify.layout.mv

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.ui.theme.balooFontFamily
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
        Text(
            text = currentDateAndTime.uppercase(Locale.ROOT),
            fontSize = 18.sp,
            fontFamily = balooFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color(0xff909090)
        )
        Text(
            text = "SCULPTIFY - HOME WORKOUT",
            fontSize = 24.sp,
            fontFamily = balooFontFamily,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}