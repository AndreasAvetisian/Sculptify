package com.example.sculptify.layout.general.buttons

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.ui.theme.balooFontFamily
import kotlinx.coroutines.launch

@Composable
fun ConfirmDeletion(
    onClick: () -> Unit,
    text: String,
    animationDuration: Int = 50,
    scaleDown: Float = 0.9f
) {
    val checkedState = remember { mutableStateOf(false) }

    val interactionSource = MutableInteractionSource()

    val coroutineScope = rememberCoroutineScope()

    val scale = remember {
        Animatable(1f)
    }

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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = text,
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
            }
            ConfirmButton(
                text = "Delete",
                bgColor = if (checkedState.value) Color.Red else Color.Red.copy(0.2f),
                textColor = Color.White,
                modifier = Modifier
                    .scale(scale = scale.value)
                    .fillMaxWidth()
                    .height(30.dp)
                    .clickable (
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        coroutineScope.launch {
                            scale.animateTo(
                                scaleDown,
                                animationSpec = tween(animationDuration),
                            )
                            scale.animateTo(
                                1f,
                                animationSpec = tween(animationDuration),
                            )
                            onClick()
                            checkedState.value = false
                        }
                    }
            )
        }
    }
}