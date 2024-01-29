package com.example.sculptify.layout.mv.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sculptify.R
import com.example.sculptify.layout.mbs.MBS

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MV_FloatingButton(navController: NavHostController) {
    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val onBottomSheetDismiss: () -> Unit = {
        showBottomSheet = false
    }

    Row (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp, 0.dp, 15.675.dp, 75.675.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    ) {
        Card (
            colors = CardDefaults.cardColors(Color(0xff0060FE)),
            shape = MaterialTheme.shapes.extraLarge,
            modifier = Modifier
                .size(50.dp, 50.dp)
                .border(
                    width = 4.dp,
                    color = Color.Black,
                    shape = MaterialTheme.shapes.extraLarge,
                )
                .clickable {
                    showBottomSheet = true
                }
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.profile_icon),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
        if (showBottomSheet) {
            MBS(
                sheetState = sheetState,
                scope = scope,
                onDismiss = onBottomSheetDismiss,
                navController = navController
            )
        }
    }
}