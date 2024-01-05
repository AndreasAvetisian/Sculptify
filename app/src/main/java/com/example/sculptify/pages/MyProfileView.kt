package com.example.sculptify.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.sculptify.MAIN_ROUTE
import com.example.sculptify.R
import com.example.sculptify.layout.DeleteUserButton
import com.example.sculptify.layout.SignOutButton
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.viewModels.AuthenticationViewModel
import com.example.sculptify.viewModels.UserViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun MyProfileView(navController: NavHostController) {
    val authVM: AuthenticationViewModel = viewModel()
    val userVM: UserViewModel = viewModel()

    LaunchedEffect(true) {
        userVM.getUserData()
    }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        // Top Bar
        Row (
            modifier = Modifier
                .padding(15.675.dp, 22.8.dp, 15.675.dp, 22.8.dp)
                .fillMaxWidth()
                .height(31.35.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Card (
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
                    .clickable {
                        navController.popBackStack()
                        navController.navigate(MAIN_ROUTE)
                    },
                shape = RoundedCornerShape(15.dp),
                colors = CardDefaults.cardColors(Color(0xff1C1C1E))
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier
                            .width(16.dp)
                            .height(16.dp),
                        painter = painterResource(id = R.drawable.arrow),
                        contentDescription = "arrow",
                        tint = Color.White
                    )
                }
            }
            Text(
                text = "My profile",
                fontSize = 20.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xffFCFCFC)
            )
            Card (
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp),
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {}
        } // Top Bar ending
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .height(365.37.dp)
                .background(Color(0xff1C1C1E)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {}
            Column (
                modifier = Modifier
                    .fillMaxWidth(0.92361f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                UserInput(
                    text = "Email",
                    input = Firebase.auth.currentUser?.email.toString(),
                    onClick = {}
                )
                UserInput(
                    text = "Modify Password",
                    input = "********",
                    onClick = {}
                )
                UserInput(
                    text = "Name",
                    input = userVM.userdata.value["firstName"].toString(),
                    onClick = {}
                )
                UserInput(
                    text = "Gender",
                    input = userVM.userdata.value["gender"].toString(),
                    onClick = {}
                )
                UserInput(
                    text = "Year of Birth",
                    input = userVM.userdata.value["yearOfBirth"].toString(),
                    onClick = {}
                )
                UserInput(
                    text = "Height",
                    input = userVM.userdata.value["height"].toString(),
                    onClick = {}
                )
                UserInput(
                    text = "Weight",
                    input = userVM.userdata.value["weight"].toString(),
                    onClick = {}
                )
            }
            Column {}
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SignOutButton(
                onClick = {
                    authVM.signOut(navController)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            DeleteUserButton(
                onClick = {
                    authVM.deleteUser(navController)
                }
            )
        }
    }
}

@Composable
fun UserInput(
    text: String,
    input: String,
    onClick: () -> Unit
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(26.79.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Text(
                text = text,
                fontSize = 20.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xffffffff)
            )
        }
        Row (
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(0.dp, 0.dp, 15.675.dp, 0.dp),
                text = input,
                fontSize = 20.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xff909090)
            )
            Icon(
                modifier = Modifier
                    .scale(scaleX = -1f, scaleY = 1f)
                    .padding(0.dp, 3.dp, 0.dp, 0.dp),
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "arrow",
                tint = Color.White
            )
        }
    }
}