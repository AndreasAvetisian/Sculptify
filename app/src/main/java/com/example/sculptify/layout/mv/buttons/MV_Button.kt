package com.example.sculptify.layout.mv.buttons

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Light_Gray
import kotlinx.coroutines.launch

@Composable
fun MV_Button(
    onClick: () -> Unit,
    isLoading: Boolean,
    data: String,
    iconId: Int,
    iconColor: Color,
    title: String,
    stat: String,
    width: Float,
    paddingStart: Dp,
    paddingEnd: Dp,
    animationDuration: Int = 50,
    scaleDown: Float = 0.9f
) {
    val interactionSource = MutableInteractionSource()

    val coroutineScope = rememberCoroutineScope()

    val scale = remember {
        Animatable(1f)
    }

    Card (
        colors = CardDefaults.cardColors(Dark_Gray),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = Modifier
            .scale(scale = scale.value)
            .fillMaxWidth(width)
            .height(140.dp)
            .padding(start = paddingStart, end = paddingEnd)
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
                .fillMaxSize()
                .padding(15.675.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.Start
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (isLoading) {
                    Box (
                        modifier = Modifier
                            .size(80.dp, 40.dp)
                    ) {}
                } else {
                    CustomText(
                        text = data,
                        fontSize = if (data == "Done!") 35.sp else 40.sp
                    )
                }
                Icon(
                    painter = painterResource(id = iconId),
                    contentDescription = "",
                    tint = iconColor,
                    modifier = Modifier
                        .size(40.dp)
                )
            }
            Column {
                CustomText(
                    text = title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                CustomText(
                    text = stat,
                    fontWeight = FontWeight.SemiBold,
                    color = Light_Gray,
                    fontSize = 16.sp
                )
            }
        }
    }
}