package com.example.sculptify.layout.msv.statistics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.data.statistics.StatSelectionItem
import com.example.sculptify.enumClasses.StatSelectionButton
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun StatSelection(selectedButton: StatSelectionButton, onButtonSelected: (StatSelectionButton) -> Unit) {
    val statSelectionItems = listOf(
        StatSelectionItem(
            title = "Today"
        ),
        StatSelectionItem(
            title = "All Time"
        )
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        repeat(statSelectionItems.size) { item ->
            val itemData = statSelectionItems[item]

            Column(
                modifier = Modifier.width(80.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = itemData.title,
                    color = if (selectedButton == StatSelectionButton.Today && itemData.title == "Today") {
                        itemData.selectedItem
                    } else if (selectedButton == StatSelectionButton.AllTime && itemData.title == "All Time") {
                        itemData.selectedItem
                    } else {
                        itemData.unselectedItem
                    },
                    fontSize = 16.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onButtonSelected(
                            when (itemData.title) {
                                "Today" -> StatSelectionButton.Today
                                "All Time" -> StatSelectionButton.AllTime
                                else -> selectedButton
                            }
                        )
                    }
                )
                Divider(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    thickness = 3.dp,
                    color = if (selectedButton == StatSelectionButton.Today && itemData.title == "Today") {
                        itemData.selectedIndicator
                    } else if (selectedButton == StatSelectionButton.AllTime && itemData.title == "All Time") {
                        itemData.selectedIndicator
                    } else {
                        itemData.unselectedIndicator
                    }
                )
            }
        }
    }
}