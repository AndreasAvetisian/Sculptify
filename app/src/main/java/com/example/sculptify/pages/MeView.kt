package com.example.sculptify.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.sculptify.data.meMBS.MeMBSButton
import com.example.sculptify.layout.mbs.MBSButton
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeView(
    navController: NavHostController,
    scope: CoroutineScope,
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    val buttons = listOf(
        MeMBSButton.MyProfile,
        MeMBSButton.MyFavorite,
        MeMBSButton.MyHistory,
        MeMBSButton.WorkoutSettings,
        MeMBSButton.GeneralSettings,
    )

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xff1C1C1E))
            .height(380.dp)
            .padding(15.675.dp, 0.dp, 15.675.dp, 15.675.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        buttons.forEach { button ->
            MBSButton(
                button = button,
                navController = navController,
                scope = scope,
                sheetState = sheetState,
                onDismiss = onDismiss
            )
        }
    }
}