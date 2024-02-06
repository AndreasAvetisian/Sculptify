package com.example.sculptify.layout.mpv

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Blue
import kotlinx.coroutines.launch

@Composable
fun MPV_ModifyGenderButton(
    text: String,
    onClick: () -> Unit,
    selected: Boolean,
    animationDuration: Int = 50,
    scaleDown: Float = 0.9f
) {
    val buttonColor = if (selected) Blue else Blue.copy(alpha = 0.2f)

    val interactionSource = MutableInteractionSource()

    val coroutineScope = rememberCoroutineScope()

    val scale = remember {
        Animatable(1f)
    }

    Card (
        colors = CardDefaults.cardColors(buttonColor),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .scale(scale = scale.value)
            .width(90.dp)
            .height(30.dp)
            .clickable(
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
                }
            }
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomText(text = text)
        }
    }
}