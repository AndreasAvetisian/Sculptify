package com.example.sculptify.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.data.bmi.BodyMassIndexItem
import com.example.sculptify.layout.mbs.MBS
import com.example.sculptify.layout.msv.bmi.BMI_Description
import com.example.sculptify.layout.msv.bmi.BMI_EditRow
import com.example.sculptify.layout.msv.bmi.slider.BMI_Slider
import com.example.sculptify.ui.theme.BMI_Blue
import com.example.sculptify.ui.theme.BMI_Cyan
import com.example.sculptify.ui.theme.BMI_DarkBlue
import com.example.sculptify.ui.theme.BMI_Orange
import com.example.sculptify.ui.theme.BMI_Red
import com.example.sculptify.ui.theme.BMI_Yellow
import com.example.sculptify.ui.theme.Dark_Gray
import com.example.sculptify.ui.theme.Gray
import com.example.sculptify.viewModels.UserViewModel

val BodyMassIndex_Items = listOf(
    BodyMassIndexItem(
        index = 15f,
        indicatorColor = BMI_DarkBlue,
        description = "Severely underweight",
        width = 0.04f,
        range = 0f..16f
    ),
    BodyMassIndexItem(
        index = 16f,
        indicatorColor = BMI_Blue,
        description = "Underweight",
        width = 0.104f,
        range = 16f..18.5f
    ),
    BodyMassIndexItem(
        index = 18.5f,
        indicatorColor = BMI_Cyan,
        description = "Healthy weight",
        width = 0.3023f,
        range = 18.5f..25f
    ),
    BodyMassIndexItem(
        index = 25f,
        indicatorColor = BMI_Yellow,
        description = "Overweight",
        width = 0.33f,
        range = 25f..30f
    ),
    BodyMassIndexItem(
        index = 30f,
        indicatorColor = BMI_Orange,
        description = "Moderately obese",
        width = 0.5f,
        range = 30f..35f
    ),
    BodyMassIndexItem(
        index = 40f,
        indicatorColor = BMI_Red,
        description = "Severely obese",
        width = 1f,
        range = 35f..250f
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBMIView(navController: NavHostController) {
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    val userData by rememberUpdatedState(
        newValue = userVM.userdata.collectAsState().value
    )

    val userHeight = userData["height"]?.toString()?.toInt() ?: 0
    val userWeight = userData["weight"]?.toString()?.toFloat() ?: 0f

    fun calculateBMI(userHeight: Int, userWeight: Float): Float {
        val heightInMeters = userHeight / 100.0f

        return userWeight / (heightInMeters * heightInMeters)
    }

    val calculateBMI: Float = calculateBMI(userHeight, userWeight)
    var bmi by remember { mutableFloatStateOf(calculateBMI) }


    val selectedBMIItem = BodyMassIndex_Items.firstOrNull { bmi in it.range }
    val indicatorColor = selectedBMIItem?.indicatorColor ?: Gray
    val description = selectedBMIItem?.description ?: "Unknown"

    var showBottomSheet by remember { mutableStateOf(false) }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val onBottomSheetDismiss: () -> Unit = {
        showBottomSheet = false
    }

    LaunchedEffect(showBottomSheet) {
        if (!showBottomSheet) {
            bmi = calculateBMI(userHeight, userWeight)
        }
    }

    Column (
        modifier = Modifier.fillMaxSize(),
    ) {
        Card (
            colors = CardDefaults.cardColors(Dark_Gray),
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
                .padding(horizontal = 10.dp)
                .clickable {
                    showBottomSheet = true
                }
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BMI_EditRow()
                BMI_Slider(indexValue = bmi)
                BMI_Description(
                    indicatorColor = indicatorColor,
                    description = description
                )
            }
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