package com.example.sculptify.layout.wdv

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.FocusArea_Gray

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun WDV_EI_FocusAreaItems(
    list: List<Map<String, String>>,
    indicatorColor: Color
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(list) {focusArea ->
            val title = focusArea["title"].toString()
            val dos = focusArea["dos"].toString().toInt() // degree of strength

            Card (
                colors = CardDefaults.cardColors(FocusArea_Gray),
                shape = MaterialTheme.shapes.extraLarge,
            ) {
                Row (
                    modifier = Modifier
                        .padding(10.dp, 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Card (
                        colors = CardDefaults.cardColors(
                            when(dos) {
                                0 -> indicatorColor.copy(0.3f)
                                else -> indicatorColor
                            }
                        ),
                        shape = CircleShape,
                        modifier = Modifier
                            .size(14.dp)
                    ) {}
                    CustomText(text = title, fontSize = 18.sp)
                }
            }
        }
    }
}