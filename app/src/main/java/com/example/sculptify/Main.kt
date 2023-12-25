package com.example.sculptify

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


const val FIRST_ROUTE = "one"
const val SECOND_ROUTE = "two"

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScaffoldView() {
    val navController = rememberNavController()
//    val scState = rememberScaffoldState( rememberDrawerState(DrawerValue.Closed) )

    Scaffold(
//        scaffoldState = scState,
        content = { MainContentView(navController) },
        bottomBar = { BottomBar(navController) },
    )
}

@Composable
fun MainContentView(navController: NavHostController) {

    NavHost(navController = navController, startDestination = FIRST_ROUTE ){
        composable( route = FIRST_ROUTE ){ FirstView() }
        composable( route = SECOND_ROUTE ){ SecondView() }
    }
}

@Composable
fun FirstView() {
    Column {
        Text(text = "PAGE #1")
    }
}

@Composable
fun SecondView() {
    Column {
        Text(text = "PAGE #2")
    }
}


@Composable
fun BottomBar(navController: NavHostController) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFFB41818))
        .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier.clickable { navController.navigate(FIRST_ROUTE) }
        ) {
            Text(text = "1")
        }

        Box(
            modifier = Modifier.clickable { navController.navigate(SECOND_ROUTE) }
        ) {
            Text(text = "2")
        }
    }
}


