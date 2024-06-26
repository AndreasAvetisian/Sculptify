package com.example.sculptify.layout.auth.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.example.sculptify.layout.general.customText.CustomText

@Composable
fun GenderSelectionButton(
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
            CustomText(
                modifier = Modifier
                    .fillMaxWidth(),
                text = text,
                fontSize = textSize,
                textAlign = TextAlign.Center
            )
        }
    }
}