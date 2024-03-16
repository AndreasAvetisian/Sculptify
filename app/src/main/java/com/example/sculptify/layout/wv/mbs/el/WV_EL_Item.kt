package com.example.sculptify.layout.wv.mbs.el

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sculptify.layout.general.customText.CustomText
import java.util.Locale

@Composable
fun WV_EL_Item(
    title: String,
    exerciseValue: String,
) {
    Card (
        colors = CardDefaults.cardColors(Color(0xff3c3c3e)),
        shape = MaterialTheme.shapes.large,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.675.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Card (
                modifier = Modifier
                    .size(60.dp)
            ) {}
            Column {
                CustomText(text = title.uppercase(Locale.ROOT))
                CustomText(
                    text = exerciseValue,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}