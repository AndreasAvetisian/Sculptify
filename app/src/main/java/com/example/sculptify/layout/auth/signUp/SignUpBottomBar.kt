package com.example.sculptify.layout.auth.signUp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sculptify.layout.general.buttons.ConfirmButton
import com.example.sculptify.pages.auth.isEditClicked
import com.example.sculptify.ui.theme.Blue
import com.example.sculptify.ui.theme.Light_Gray

@Composable
fun SignUpBottomBar(
    backText: String,
    backOnClick: () -> Unit,
    nextText: String,
    nextBgColor: Color,
    nextOnClick: () -> Unit,
    currentPage: Int
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.675.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(if (isEditClicked) 0f else 1f)
                .padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                repeat(5) {pageIndex ->
                    Card (
                        colors = CardDefaults.cardColors(if (pageIndex == currentPage) Blue else Light_Gray),
                        shape = MaterialTheme.shapes.extraLarge
                    ) {
                        Spacer(
                            modifier = Modifier
                                .width(if (pageIndex == currentPage) 30.dp else 8.dp)
                                .height(8.dp)
                        )
                    }
                }
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ConfirmButton(
                text = backText,
                textColor = Color.White,
                bgColor = Color(0xff1C1C1E),
                onClick = {
                    backOnClick()
                },
                modifier = Modifier
                    .width(if (isEditClicked) 0.dp else 100.dp)
                    .padding(end = 10.dp)
                    .height(60.dp)
            )
            ConfirmButton(
                text = nextText,
                textColor = Color.White,
                bgColor = nextBgColor,
                onClick = {
                    nextOnClick()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            )
        }
    }

}