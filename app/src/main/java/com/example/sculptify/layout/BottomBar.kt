package com.example.sculptify.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.ACHIEVEMENTS_ROUTE
import com.example.sculptify.MAIN_ROUTE
import com.example.sculptify.R
import com.example.sculptify.STATISTICS_ROUTE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(navController: NavHostController) {

    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    // Callback function to be passed to MeMBS
    val onBottomSheetDismiss: () -> Unit = {
        showBottomSheet = false
    }

    Column {
        Divider( thickness = 2.dp, color = Color(0xff909090))
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(88.35.dp)
            .background(Color(0xFF000000))
        ){
            Row (
                modifier = Modifier
                    .padding(15.675.dp, 0.dp, 15.675.dp, 0.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(
                    iconId = painterResource(id = R.drawable.main_page_icon),
                    text = "Main",
                    iconDescription = "main page icon",
                    Modifier.clickable { navController.navigate(MAIN_ROUTE) }
                )
                Button(
                    iconId = painterResource(id = R.drawable.main_page_icon),
                    text = "Statistics",
                    iconDescription = "statistics page icon",
                    Modifier.clickable { navController.navigate(STATISTICS_ROUTE) }
                )
                Button(
                    iconId = painterResource(id = R.drawable.main_page_icon),
                    text = "Achievements",
                    iconDescription = "achievements page icon",
                    Modifier.clickable { navController.navigate(ACHIEVEMENTS_ROUTE) }
                )
                Button(
                    iconId = painterResource(id = R.drawable.main_page_icon),
                    text = "Me",
                    iconDescription = "me page icon",
                    Modifier.clickable { showBottomSheet = true }
                )

                if (showBottomSheet) {
                    MeMBS(
                        sheetState = sheetState,
                        scope = scope,
                        onDismiss = onBottomSheetDismiss
                    )
                }
            }
        }
    }
}

@Composable
fun Button(
    iconId: Painter,
    text: String,
    iconDescription: String,
    modifier: Modifier
) {
    Card(
        shape = RoundedCornerShape(14.25.dp),
        colors = CardDefaults.cardColors(Color(0xFF1C1C1E)),
        modifier = modifier
            .width(71.25.dp)
            .height(57.dp)

    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = iconId,
                tint = Color(0xff909090),
                contentDescription = iconDescription
            )
            Text(
                color = Color(0xff909090),
                fontSize = 9.975.sp,
                text = text
            )
        }
    }
}