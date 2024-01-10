package com.example.sculptify.layout.mainBottomBar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomBarButton(
    iconId: Painter,
    text: String,
    iconDescription: String,
    modifier: Modifier,
    selected: Boolean,
    onClick: () -> Unit
) {

    val textColor = if (selected) Color(0xFF0060FE) else Color(0xff909090)
    val iconColor = if (selected) Color(0xFF0060FE) else Color(0xff909090)

    Card(
        shape = RoundedCornerShape(14.25.dp),
        colors = CardDefaults.cardColors(Color(0xFF1C1C1E)),
        modifier = modifier
            .width(71.25.dp)
            .height(57.dp)
            .clickable { onClick() }

    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = iconId,
                tint = iconColor,
                contentDescription = iconDescription
            )
            Text(
                color = textColor,
                fontSize = 9.975.sp,
                text = text
            )
        }
    }
}