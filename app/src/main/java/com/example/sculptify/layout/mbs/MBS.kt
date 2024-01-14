package com.example.sculptify.layout.mbs

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sculptify.layout.dayStreakActiveDaysView.adv.ADV_ModifyWeeklyGoal
import com.example.sculptify.main.DAY_STREAK_ACTIVE_DAYS_ROUTE
import com.example.sculptify.main.MAIN_ROUTE
import com.example.sculptify.pages.MeView
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MBS(
    navController: NavHostController,
    sheetState: SheetState,
    scope: CoroutineScope,
    onDismiss: () -> Unit
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState,
        containerColor = Color(0xFF1C1C1E)
    ) {
        if (currentRoute == MAIN_ROUTE) {
            MeView(
                scope = scope,
                sheetState = sheetState,
                onDismiss = onDismiss,
                navController = navController
            )
        } else if (currentRoute == DAY_STREAK_ACTIVE_DAYS_ROUTE){
            ADV_ModifyWeeklyGoal(
                scope = scope,
                sheetState = sheetState,
                onDismiss = onDismiss,
                navController = navController
            )
        }
    }
}