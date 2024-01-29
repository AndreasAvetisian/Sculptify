package com.example.sculptify.layout.auth

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.example.sculptify.layout.general.customText.CustomText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation,
    trailingIcon: @Composable (() -> Unit)?,
    textStyle: TextStyle,
    modifier: Modifier,
    containerColor: Color
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        label = {
            CustomText(
                text = label,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        singleLine = true,
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            cursorColor = Color.White,
            focusedContainerColor = containerColor,
            unfocusedContainerColor = containerColor,
            disabledContainerColor = containerColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            disabledLabelColor = Color.White,
        ),
        textStyle = textStyle,
        shape = MaterialTheme.shapes.large,
        trailingIcon = trailingIcon
    )
}