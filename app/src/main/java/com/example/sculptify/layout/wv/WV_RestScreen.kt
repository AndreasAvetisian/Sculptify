package com.example.sculptify.layout.wv

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.White

@Composable
fun WV_RestScreen(
    onAddMoreTimeClick: () -> Unit,
    onSkipClick: () -> Unit,
    onExerciseDescriptionClick: () -> Unit,
    nextExerciseIndex: Int,
    exerciseAmount: Int,
    exerciseTitle: String,
    exerciseValue: String
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Blue),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 110.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomText(
                text = "Rest",
                fontSize = 30.sp
            )
            CustomText(
                text = "00:10",
                fontSize = 70.sp
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ConfirmButton(
                    text = "+20s",
                    bgColor = Color(0xff3483FE),
                    modifier = Modifier
                        .size(110.dp, 40.dp),
                    onClick = { onAddMoreTimeClick() }
                )
                Spacer(modifier = Modifier.width(30.dp))
                ConfirmButton(
                    text = "SKIP",
                    bgColor = White,
                    textColor = Blue,
                    modifier = Modifier
                        .size(110.dp, 40.dp),
                    onClick = {
                        onSkipClick()
                    }
                )
            }
        }
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
                        CustomText(text = exerciseTitle)
                        Icon(
                            painter = painterResource(id = R.drawable.question_circle),
                            contentDescription = "",
                            tint = White,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    CustomText(
                        text = exerciseValue
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
}