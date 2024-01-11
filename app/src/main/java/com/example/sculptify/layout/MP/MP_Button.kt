package com.example.sculptify.layout.MP

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.ConfirmButton
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun MP_Button(
    onClick: () -> Unit,
    deleteOnClick: () -> Unit,
    text: String,
    isOpenable: Boolean
) {
    var isDeleteOpen by remember { mutableStateOf(false) }
    val checkedState = remember { mutableStateOf(false) }

    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .background(Color(0xff1C1C1E))
                .height(56.dp)
                .clickable {
                    isDeleteOpen = !isDeleteOpen
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.675.dp, 10.dp, 17.675.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = text,
                    fontSize = 20.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Icon(
                    modifier = Modifier
                        .scale(scaleX = -1f, scaleY = 1f)
                        .rotate(if (isDeleteOpen) -90f else 0f)
                        .padding(0.dp, 3.dp, 0.dp, 0.dp),
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "arrow",
                    tint = Color.White
                )
            }
        }
        if (isOpenable) {
            if (isDeleteOpen) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xff1C1C1E)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.675.dp, 0.dp, 15.675.dp, 10.dp)
                            .height(56.dp)
                            .background(Color(0xff1C1C1E)),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Confirm account deletion",
                            fontSize = 20.sp,
                            fontFamily = balooFontFamily,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                        Checkbox(
                            checked = checkedState.value,
                            onCheckedChange = { checkedState.value = it },
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xff0000ff),
                                uncheckedColor = Color.White,
                                checkmarkColor = Color.White
                            )
                        )
                        ConfirmButton(
                            text = "Delete",
                            bgColor = if (checkedState.value) Color.Red else Color.Red.copy(0.2f),
                            textColor = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp)
                                .clickable {
                                    deleteOnClick()
                                }
                        )
                    }
                }
            }
        }
    }
}