package com.example.sculptify.layout.auth.signUp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.pages.auth.regGender
import com.example.sculptify.enumClasses.GenderButton
import com.example.sculptify.layout.auth.signUp.GenderSelectionButton
import com.example.sculptify.layout.general.customText.CustomText

@Composable
fun RegGenderSelection() {
    var selectedButton by remember { mutableStateOf(
        when(regGender) {
            "Male" -> GenderButton.Male
            "Female" -> GenderButton.Female
            "Others" -> GenderButton.Others
            else -> GenderButton.None
        }
    ) }

    Column (
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomText(text = "Gender:")
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            GenderSelectionButton(
                text = "Male",
                textSize = 20.sp,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clickable {
                        selectedButton = GenderButton.Male
                        regGender = "Male"
                    },
                iconId = R.drawable.male,
                tint = if (selectedButton == GenderButton.Male) Color.White else Color(0xff0000ff),
                iconModifier = Modifier.size(40.dp),
                selected = selectedButton == GenderButton.Male
            )
            GenderSelectionButton(
                text = "Female",
                textSize = 20.sp,
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .clickable {
                        selectedButton = GenderButton.Female
                        regGender = "Female"
                    },
                iconId = R.drawable.female,
                tint = if (selectedButton == GenderButton.Female) Color.White else Color(0xffFFC0CB),
                iconModifier = Modifier.size(40.dp),
                selected = selectedButton == GenderButton.Female
            )
        }
        GenderSelectionButton(
            text = "Others / I'd rather not say",
            textSize = 20.sp,
            modifier = Modifier
                .padding(start = 0.dp, top = 20.dp, end = 0.dp)
                .fillMaxWidth()
                .height(60.dp)
                .clickable {
                    selectedButton = GenderButton.Others
                    regGender = "Others"
                },
            iconId = R.drawable.female,
            tint = Color(0xffFFC0CB),
            iconModifier = Modifier.size(0.dp),
            selected = selectedButton == GenderButton.Others
        )
    }
}