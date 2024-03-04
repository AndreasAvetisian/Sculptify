package com.example.sculptify.layout.wv.rs

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sculptify.R
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.White

@Composable
fun WV_RS_BottomSheet(
    nextExerciseIndex: Int,
    exerciseAmount: Int,
    onExerciseDescriptionClick: () -> Unit,
    nextExerciseTitle: String,
    nextExerciseValue: String,
) {
    Column {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.675.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            CustomText(
                text = "NEXT $nextExerciseIndex/$exerciseAmount",
                fontWeight = FontWeight.SemiBold
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .clickable { onExerciseDescriptionClick() },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    CustomText(text = nextExerciseTitle)
                    Icon(
                        painter = painterResource(id = R.drawable.question_circle),
                        contentDescription = "",
                        tint = White,
                        modifier = Modifier.size(22.dp)
                    )
                }
                CustomText(
                    text = nextExerciseValue
                )
            }
        }
        Card (
            colors = CardDefaults.cardColors(White),
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
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card (
                    modifier = Modifier
                        .size(200.dp)
                ) {
                    // Image here
                }
            }
        }
    }
}