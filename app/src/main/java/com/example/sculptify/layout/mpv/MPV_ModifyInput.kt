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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
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
            Text(
                text = title,
                fontSize = 20.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xffffffff)
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Red),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = placeholder,
                        fontFamily = balooFontFamily,
                        fontWeight = FontWeight.Bold
                    )
                },
                singleLine = true,
                readOnly = readOnly,
                shape = RoundedCornerShape(0.dp),
                colors = TextFieldDefaults.colors(
                    cursorColor = Color.White,
                    focusedTextColor = Color(0xFF909090),
                    unfocusedTextColor = Color(0xFF909090),
                    disabledTextColor = Color(0xFF909090),
                    focusedPlaceholderColor = Color(0xFF909090),
                    unfocusedPlaceholderColor = Color(0xFF909090),
                    disabledPlaceholderColor = Color(0xFF909090),
                    focusedContainerColor = Color(0xFF1C1C1C),
                    unfocusedContainerColor = Color(0xFF1C1C1C),
                    disabledContainerColor = Color(0xFF1C1C1C),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    disabledLabelColor = Color.White,
                ),
                modifier = Modifier
                    .height(56.dp),
                trailingIcon = {
                    if (title != "Email") {
                        Icon(
                            modifier = Modifier
                                .scale(scaleX = -1f, scaleY = 1f)
                                .padding(0.dp, 3.dp, 0.dp, 0.dp),
                            painter = painterResource(id = R.drawable.arrow),
                            contentDescription = "arrow",
                            tint = Color.White
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