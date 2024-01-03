package com.example.sculptify.pages

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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.ui.theme.balooFontFamily

var pageCounter by mutableIntStateOf(1)
var gender by mutableStateOf("")
@Composable
fun SignUpView() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        when (pageCounter) {
            1 -> {
                RegistrationBeginning()
            }
            2 -> {
                Gender()
            }
            3 -> {
                // Code to execute when value is 3
            }
            else -> {
                Text(text = "AAA")
            }
        }
    }
}

@Composable
fun ConfirmButton(
    text: String,
    onClick: () -> Unit
) {
    Card (
        colors = CardDefaults.cardColors(Color(0xff0060FE)),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                onClick()
            },
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 20.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun RegistrationBeginning() {
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
                .fillMaxHeight(0.92f)
                .padding(15.675.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello!",
                color = Color.White,
                fontSize = 60.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Here are some questions to set up an account for you!",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        ConfirmButton(
            text = "I'M READY",
            onClick = {
                pageCounter += 1
            }
        )
    }
}

@Composable
fun Gender() {
    var selectedButton by remember { mutableStateOf(GenderButton.Male) }

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
                GenderButton(
                    text = "Male",
                    textSize = 30.sp,
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                        .clickable {
                            gender = "Male"
                        },
                    iconId = R.drawable.male,
                    tint = Color(0xff0000ff),
                    iconModifier = Modifier.size(50.dp),
                    selected = selectedButton == GenderButton.Male
                )
                GenderButton(
                    text = "Female",
                    textSize = 30.sp,
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                        .clickable {
                            gender = "Female"
                        },
                    iconId = R.drawable.female,
                    tint = Color(0xffFFC0CB),
                    iconModifier = Modifier.size(50.dp),
                    selected = selectedButton == GenderButton.Female
                )
            }
            GenderButton(
                text = "Others / I'd rather not say",
                textSize = 20.sp,
                modifier = Modifier
                    .padding(start = 45.dp, top= 20.dp, end = 45.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                        gender = "Others"
                    },
                iconId = R.drawable.female,
                tint = Color(0xffFFC0CB),
                iconModifier = Modifier.size(0.dp),
                selected = selectedButton == GenderButton.Others
            )
        }
        ConfirmButton(
            text = "NEXT",
            onClick = {
                pageCounter += 1
            }
        )
    }
}

enum class GenderButton {
    Male,
    Female,
    Others
}

@Composable
fun GenderButton(
    text: String,
    textSize: TextUnit,
    modifier: Modifier,
    iconId: Int,
    iconModifier: Modifier,
    tint: Color,
    selected: Boolean
    ) {

    val buttonColor = if (selected) Color(0xff0000ff) else Color(0xff1C1C1E)

    Card (
        colors = CardDefaults.cardColors(buttonColor),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = modifier
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = iconModifier,
                painter = painterResource(id = iconId),
                contentDescription = "",
                tint = tint
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = text,
                color = Color.White,
                fontSize = textSize,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

