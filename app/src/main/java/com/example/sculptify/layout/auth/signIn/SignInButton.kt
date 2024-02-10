package com.example.sculptify.layout.auth.signIn

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun SignInButton(
    bgColor: Color,
    onClick: () -> Unit,
    animationDuration: Int = 50,
    scaleDown: Float = 0.9f
) {
    val interactionSource = MutableInteractionSource()

    val coroutineScope = rememberCoroutineScope()

    val scale = remember {
        Animatable(1f)
    }

    Card (
        colors = CardDefaults.cardColors(bgColor),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .scale(scale = scale.value)
            .padding(0.dp, 10.dp)
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
                }
            },
    ) {
        Icon(
            Icons.Filled.ArrowForward,
            contentDescription = "log in",
            modifier = Modifier
                .padding(50.dp, 10.dp),
            tint = Color.White
        )
    }
}