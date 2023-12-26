package com.example.sculptify.layout

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.sculptify.pages.MeView
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeMBS(
    sheetState: SheetState,
    scope: CoroutineScope,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = sheetState,
        containerColor = Color(0xff1C1C1E)
    ) {
        MeView(
            scope = scope,
            sheetState = sheetState,
            onDismiss = onDismiss
        )
    }
}