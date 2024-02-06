package com.example.sculptify.layout.mpv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Light_Gray
import com.example.sculptify.ui.theme.Red
import com.example.sculptify.ui.theme.Transparent
import com.example.sculptify.ui.theme.White
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun MPV_ModifyInput(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    readOnly: Boolean,
    keyboardType: KeyboardType
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(0.6f)
        ) {
            CustomText(text = title)
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Red),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    CustomText(
                        text = placeholder,
                        fontSize = 16.sp,
                        color = Light_Gray
                    )
                },
                singleLine = true,
                readOnly = readOnly,
                shape = RoundedCornerShape(0.dp),
                colors = TextFieldDefaults.colors(
                    cursorColor = White,
                    focusedTextColor = Light_Gray,
                    unfocusedTextColor = Light_Gray,
                    disabledTextColor = Light_Gray,
                    focusedPlaceholderColor = Light_Gray,
                    unfocusedPlaceholderColor = Light_Gray,
                    disabledPlaceholderColor = Light_Gray,
                    focusedContainerColor = Dark_Gray,
                    unfocusedContainerColor = Dark_Gray,
                    disabledContainerColor = Dark_Gray,
                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    disabledIndicatorColor = Transparent,
                    focusedLabelColor = White,
                    unfocusedLabelColor = White,
                    disabledLabelColor = White,
                ),
                modifier = Modifier
                    .height(56.dp),
                trailingIcon = {
                    if (title != "Email") {
                        Icon(
                            modifier = Modifier
                                .scale(scaleX = -1f, scaleY = 1f)
                                .padding(0.dp, 3.dp, 40.dp, 0.dp),
                            painter = painterResource(id = R.drawable.arrow),
                            contentDescription = "arrow",
                            tint = White
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}