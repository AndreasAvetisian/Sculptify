package com.example.sculptify.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sculptify.R
import com.example.sculptify.layout.mpv.ConfirmDeletion
import com.example.sculptify.layout.settings.workout.WS_OpenedMenu
import com.example.sculptify.main.MY_PROFILE_ROUTE
import com.example.sculptify.ui.theme.balooFontFamily

@Composable
fun OpenableLineButton(
    onClick: () -> Unit,
    openOnClick: () -> Unit,
    text: String,
    textColor: Color,
    isOpenable: Boolean,
    isDeleteView: Boolean,
    route: String
) {
    var isOpen by remember { mutableStateOf(false) }
    val checkedState = remember { mutableStateOf(false) }

    
//    val navController = rememberNavController()
//    val currentBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = currentBackStackEntry?.destination?.route

    Column (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row (
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .background(Color(0xff1C1C1E))
                .height(56.dp)
                .clickable {
                    isOpen = !isOpen
                    if (!isOpen) checkedState.value = false
                    onClick()
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.675.dp, 10.dp, 17.675.dp, 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = text,
                    fontSize = 20.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Icon(
                    modifier = Modifier
                        .scale(scaleX = -1f, scaleY = 1f)
                        .rotate(if (isOpen) -90f else 0f)
                        .padding(0.dp, 3.dp, 0.dp, 0.dp),
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "arrow",
                    tint = Color.White
                )
            }
        }
        if (isOpenable) {
            if (isOpen) {
                if (isDeleteView) {
                    ConfirmDeletion(
                        onClick = {
                            openOnClick()
                        },
                        text = when (route) {
                            MY_PROFILE_ROUTE -> "Confirm account deletion"
                            else -> "Confirm deletion"
                        }
                    )
                } else {
                    WS_OpenedMenu()
                }
            }
        }
    }
}