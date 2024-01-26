package com.example.sculptify.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.data.bmi.BodyMassIndexItem
import com.example.sculptify.ui.theme.balooFontFamily

val BodyMassIndex_Items = listOf(
    BodyMassIndexItem(
        index = 15f,
        indicatorColor = Color(0xff2340D4),
        description = "Severely underweight"
    ),
    BodyMassIndexItem(
        index = 16f,
        indicatorColor = Color(0xff127FFF),
        description = "Underweight"
    ),
    BodyMassIndexItem(
        index = 18.5f,
        indicatorColor = Color(0xff03C8E0),
        description = "Healthy weight"
    ),
    BodyMassIndexItem(
        index = 25f,
        indicatorColor = Color(0xffFFCA06),
        description = "Overweight"
    ),
    BodyMassIndexItem(
        index = 30f,
        indicatorColor = Color(0xffFF9307),
        description = "Moderately obese"
    ),
    BodyMassIndexItem(
        index = 30f,
        indicatorColor = Color(0xffFF0232),
        description = "Severely obese"
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBMIView() {
    var bmi by remember { mutableFloatStateOf(18.5f) }

    Column (
        modifier = Modifier.fillMaxSize(),
    ) {
        Card (
            colors = CardDefaults.cardColors(Color(0xff1C1C1E)),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "BMI",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        Icons.Rounded.Edit,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )

                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Slider(
                        value = bmi,
                        onValueChange = { bmi = it },
                        enabled = true,
                        modifier = Modifier
                            .padding(top = 20.dp),
                        valueRange = 15f..40f,
                        steps = 0,
                        track = {
                            Row (
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column (
                                        modifier = Modifier
                                            .fillMaxWidth(0.04f)
                                            .fillMaxHeight(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Card (
                                            colors = CardDefaults.cardColors(Color(0xff2340D4)),
                                            shape = MaterialTheme.shapes.extraLarge,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight(0.2f)
                                        ) {
                                            Spacer(modifier = Modifier.fillMaxSize())
                                        }
                                        Text(
                                            text = "15",
                                            color = Color.White,
                                            fontSize = 10.sp,
                                            fontFamily = balooFontFamily,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Column (
                                        modifier = Modifier
                                            .fillMaxWidth(0.104f)
                                            .fillMaxHeight(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Card (
                                            colors = CardDefaults.cardColors(Color(0xff127FFF)),
                                            shape = MaterialTheme.shapes.extraLarge,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight(0.2f)
                                        ) {
                                            Spacer(modifier = Modifier.fillMaxSize())
                                        }
                                        Text(
                                            text = "16",
                                            color = Color.White,
                                            fontSize = 10.sp,
                                            fontFamily = balooFontFamily,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Column (
                                        modifier = Modifier
                                            .fillMaxWidth(0.3023f)
                                            .fillMaxHeight(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Card (
                                            colors = CardDefaults.cardColors(Color(0xff03C8E0)),
                                            shape = MaterialTheme.shapes.extraLarge,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight(0.2f)
                                        ) {
                                            Spacer(modifier = Modifier.fillMaxSize())
                                        }
                                        Text(
                                            text = "18.5",
                                            color = Color.White,
                                            fontSize = 10.sp,
                                            fontFamily = balooFontFamily,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Column (
                                        modifier = Modifier
                                            .fillMaxWidth(0.33f)
                                            .fillMaxHeight(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Card (
                                            colors = CardDefaults.cardColors(Color(0xffFFCA06)),
                                            shape = MaterialTheme.shapes.extraLarge,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight(0.2f)
                                        ) {
                                            Spacer(modifier = Modifier.fillMaxSize())
                                        }
                                        Text(
                                            text = "25",
                                            color = Color.White,
                                            fontSize = 10.sp,
                                            fontFamily = balooFontFamily,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Column (
                                        modifier = Modifier
                                            .fillMaxWidth(0.5f)
                                            .fillMaxHeight(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Card (
                                            colors = CardDefaults.cardColors(Color(0xffFF9307)),
                                            shape = MaterialTheme.shapes.extraLarge,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight(0.2f)
                                        ) {
                                            Spacer(modifier = Modifier.fillMaxSize())
                                        }
                                        Text(
                                            text = "30",
                                            color = Color.White,
                                            fontSize = 10.sp,
                                            fontFamily = balooFontFamily,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                    Column (
                                        modifier = Modifier
                                            .fillMaxWidth(1f)
                                            .fillMaxHeight(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.Start
                                    ) {
                                        Card (
                                            colors = CardDefaults.cardColors(Color(0xffFF0232)),
                                            shape = MaterialTheme.shapes.extraLarge,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .fillMaxHeight(0.2f)
                                        ) {
                                            Spacer(modifier = Modifier.fillMaxSize())
                                        }
                                        Row (
                                            modifier = Modifier
                                                .fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Text(
                                                text = "35",
                                                color = Color.White,
                                                fontSize = 10.sp,
                                                fontFamily = balooFontFamily,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = "40",
                                                color = Color.White,
                                                fontSize = 10.sp,
                                                fontFamily = balooFontFamily,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            }
                        },
                        thumb = {
                            Icon(
                                painter = painterResource(id = R.drawable.bmi_thumb),
                                contentDescription = "",
                                tint = Color(0xff58606B),
                                modifier = Modifier
                                    .width(50.dp)
                                    .padding(bottom = 60.dp)
                            )
                            Text(
                                text = "%.1f".format(bmi),
                                color = Color.White,
                                fontSize = 18.sp,
                                fontFamily = balooFontFamily,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.width(50.dp),
                                textAlign = TextAlign.Center
                            )
                        },
                    )
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Card(
                        colors = CardDefaults.cardColors(Color(0xFF0000FF)),
                        shape = CircleShape,
                        modifier = Modifier
                            .size(30.dp),
                        content = {}
                    )
                    Text(
                        text = "Healthy weight",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}