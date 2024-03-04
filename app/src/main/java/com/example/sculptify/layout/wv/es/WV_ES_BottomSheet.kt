package com.example.sculptify.layout.wv.es

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Black
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Workout_Timer_Gray
import java.util.Locale

@Composable
fun WV_ES_BottomSheet(
    onExerciseDescriptionClick: () -> Unit,
    exerciseTitle: String,
    exerciseValue: String,
    onCompleteClick: () -> Unit
) {
    Card (
        colors = CardDefaults.cardColors(Black),
        shape = RoundedCornerShape(
            topStart = 40.dp,
            topEnd = 40.dp,
            bottomEnd = 0.dp,
            bottomStart = 0.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(15.675.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onExerciseDescriptionClick() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CustomText(
                    text = exerciseTitle.uppercase(Locale.ROOT),
                    fontSize = if (exerciseTitle.length < 25) 30.sp else 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(end = 5.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.question_circle),
                    contentDescription = "",
                    tint = Workout_Timer_Gray,
                    modifier = Modifier.size(30.dp)
                )
            }
            CustomText(
                text = exerciseValue,
                fontSize = 70.sp
            )
            ConfirmButton(
                bgColor = Blue,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                onClick = { onCompleteClick() },
                withText = false
            )
        }
    }
}