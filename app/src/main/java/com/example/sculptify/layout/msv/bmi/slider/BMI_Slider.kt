package com.example.sculptify.layout.msv.bmi.slider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sculptify.pages.BodyMassIndex_Items

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMI_Slider(
    indexValue: Float
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Slider(
            value = indexValue,
            onValueChange = {},
            enabled = false,
            modifier = Modifier
                .padding(top = 20.dp),
            valueRange = 15f..40f,
            steps = 0,
            track = {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        repeat(BodyMassIndex_Items.size) { item ->

                            val width = BodyMassIndex_Items[item].width
                            val color = BodyMassIndex_Items[item].indicatorColor
                            val index = BodyMassIndex_Items[item].index

                            BMI_SliderItem(
                                width = width,
                                color = color,
                                index = index
                            )
                        }
                    }
                }
            },
            thumb = {
                BMI_SliderThumb(index = indexValue)
            },
        )
    }
}