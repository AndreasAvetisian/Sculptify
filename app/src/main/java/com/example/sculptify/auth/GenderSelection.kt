package com.example.sculptify.auth

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.enumClasses.GenderButton
import com.example.sculptify.layout.auth.GenderSelectionButton
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun GenderSelection() {
    var selectedButton by remember { mutableStateOf(GenderButton.Male) }

    Column (
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(15.675.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.90f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 0.dp, bottom = 20.dp),
                    text = "What's your gender?",
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    GenderSelectionButton(
                        text = "Male",
                        textSize = 30.sp,
                        modifier = Modifier
                            .width(120.dp)
                            .height(120.dp)
                            .clickable {
                                selectedButton = GenderButton.Male
                                regGender = "Male"
                            },
                        iconId = R.drawable.male,
                        tint = if (selectedButton == GenderButton.Male) Color.White else Color(0xff0000ff),
                        iconModifier = Modifier.size(50.dp),
                        selected = selectedButton == GenderButton.Male
                    )
                    GenderSelectionButton(
                        text = "Female",
                        textSize = 30.sp,
                        modifier = Modifier
                            .width(120.dp)
                            .height(120.dp)
                            .clickable {
                                selectedButton = GenderButton.Female
                                regGender = "Female"
                            },
                        iconId = R.drawable.female,
                        tint = if (selectedButton == GenderButton.Female) Color.White else Color(0xffFFC0CB),
                        iconModifier = Modifier.size(50.dp),
                        selected = selectedButton == GenderButton.Female
                    )
                }
                GenderSelectionButton(
                    text = "Others / I'd rather not say",
                    textSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 45.dp, top = 20.dp, end = 45.dp)
                        .fillMaxWidth()
                        .height(60.dp)
                        .clickable {
                            selectedButton = GenderButton.Others
                            regGender = "Others"
                        },
                    iconId = R.drawable.female,
                    tint = Color(0xffFFC0CB),
                    iconModifier = Modifier.size(0.dp),
                    selected = selectedButton == GenderButton.Others
                )
            }
        }
    }
}