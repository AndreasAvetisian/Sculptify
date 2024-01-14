package com.example.sculptify.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.R
import com.example.sculptify.layout.mbs.MBSButton
import com.example.sculptify.main.MY_FAVORITE_MY_HISTORY_ROUTE
import com.example.sculptify.main.MY_PROFILE_ROUTE
import com.example.sculptify.main.VERSION
import com.example.sculptify.ui.theme.balooFontFamily
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeView(
    navController: NavHostController,
    scope: CoroutineScope,
    sheetState: SheetState,
    onDismiss: () -> Unit
) {
    // Modal Bottom Sheet
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xff1C1C1E))
            .height(400.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // My Profile/Favorite
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(142.5.dp)
                .background(Color(0xff202020))
        ) {
            Column {
                // My Profile
                MBSButton(
                    iconId = painterResource(id = R.drawable.smile),
                    iconDescription = "my profile icon",
                    text = "My Profile",
                    cardColor = CardDefaults.cardColors(Color(0xff0060FE)),
                    onClick = {
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onDismiss()
                            }
                        }
                        navController.navigate(MY_PROFILE_ROUTE)
                    }
                ) // My Profile ending
                Row (
                    modifier = Modifier
                        .padding(55.575.dp, 0.dp, 0.dp, 0.dp)
                ) {
                    Divider( thickness = 1.dp, color = Color(0xff909090))
                }
                // My Favorite
                MBSButton(
                    iconId = painterResource(id = R.drawable.favorite),
                    iconDescription = "my favorite icon",
                    text = "My Favorite",
                    cardColor = CardDefaults.cardColors(Color(0xff0060FE)),
                    onClick = {
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onDismiss()
                            }
                        }
                        navController.navigate(MY_FAVORITE_MY_HISTORY_ROUTE)
                    }
                ) // My Favorite ending
            }
        } // My Profile/Favorite ending

        // Settings
        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier
                .padding(15.675.dp, 0.dp, 0.dp, 0.dp)
                .fillMaxWidth()
                .height(47.025.dp)
        ) {
            Text(
                text = "Settings",
                fontSize = 22.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xffFCFCFC)
            )
        }

        // Workout Settings/General Settings
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(142.5.dp)
                .background(Color(0xff202020))
        ) {
            Column {
                // Workout Settings
                MBSButton(
                    iconId = painterResource(id = R.drawable.workout_settings),
                    iconDescription = "workout settings icon",
                    text = "Workout Settings",
                    cardColor = CardDefaults.cardColors(Color(0xffFF8E00)),
                    onClick = {
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onDismiss()
                            }
                        }
                    }
                ) // Workout Settings ending
                Row (
                    modifier = Modifier
                        .padding(55.575.dp, 0.dp, 0.dp, 0.dp)
                ) {
                    Divider( thickness = 1.dp, color = Color(0xff909090))
                }
                // General Settings
                MBSButton(
                    iconId = painterResource(id = R.drawable.general_settings),
                    iconDescription = "general settings icon",
                    text = "General Settings",
                    cardColor = CardDefaults.cardColors(Color(0xffFF8E00)),
                    onClick = {
                        scope.launch {
                            sheetState.hide()
                        }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                onDismiss()
                            }
                        }
                    }
                )
                // General Settings ending
            }
        } // Workout Settings/General Settings ending

        // Version
        Row (
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = "Version $VERSION",
                fontSize = 15.5.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xffFCFCFC)
            )
        } // Version ending
    } // Modal Bottom Sheet ending
}