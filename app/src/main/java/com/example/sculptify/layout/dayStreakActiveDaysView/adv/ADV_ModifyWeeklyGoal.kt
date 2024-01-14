package com.example.sculptify.layout.dayStreakActiveDaysView.adv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ADV_ModifyWeeklyGoal(
    navController: NavHostController,
    scope: CoroutineScope,
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xff1C1C1E))
            .height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "hello", color = Color.White)
    }
}