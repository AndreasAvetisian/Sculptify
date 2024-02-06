package com.example.sculptify.layout.mbs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.sculptify.R
import com.example.sculptify.data.meMBS.MeMBSButton
import com.example.sculptify.layout.general.customText.CustomText
import com.example.sculptify.ui.theme.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MBSButton(
    button: MeMBSButton,
    navController: NavHostController,
    scope: CoroutineScope,
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    Card (
        colors = CardDefaults.cardColors(button.bgColor),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable {
                scope
                    .launch {
                        sheetState.hide()
                    }
                    .invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onDismiss()
                        }
                    }
                navController.navigate(button.route) {
                    popUpTo(navController.graph.findStartDestination().id)
                    launchSingleTop = true
                }
            }
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(15.675.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Icon(
                    painter = painterResource(id = button.icon),
                    tint = White,
                    contentDescription = ""
                )
                CustomText(
                    text = button.text,
                    fontSize = 17.sp
                )
            }
            Icon(
                modifier = Modifier
                    .scale(scaleX = -1f, scaleY = 1f),
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "arrow",
                tint = White
            )
        }
    }
}