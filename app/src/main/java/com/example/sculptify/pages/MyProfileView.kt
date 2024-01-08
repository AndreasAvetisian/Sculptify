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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.sculptify.R
import com.example.sculptify.layout.DeleteUserButton
import com.example.sculptify.layout.SignOutButton
import com.example.sculptify.main.MAIN_ROUTE
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.viewModels.AuthenticationViewModel
import com.example.sculptify.viewModels.UserViewModel

@Composable
fun MyProfileView(navController: NavHostController) {
    val authVM: AuthenticationViewModel = viewModel()
    val userVM: UserViewModel = viewModel()

    var user_email = remember { mutableStateOf("") }
    var user_name = remember { mutableStateOf("") }
    var user_gender = remember { mutableStateOf("") }
    var user_yob = remember { mutableStateOf("") }
    var user_height = remember { mutableStateOf("") }
    var user_weight = remember { mutableStateOf("") }

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
//            Spacer(modifier = Modifier.width(15.675.dp))
            Column (
                modifier = Modifier
                    .padding(15.675.dp, 0.dp, 0.dp, 0.dp)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                UserInput(
                    title = "Email",
                    value = user_email,
                    onClick = {}
                )
                UserInput(
                    title = "Name",
                    value = user_name,
                    onClick = {}
                )
                UserInput(
                    title = "Gender",
                    value = user_gender,
                    onClick = {}
                )
                UserInput(
                    title = "Year of Birth",
                    value = user_yob,
                    onClick = {}
                )
                UserInput(
                    title = "Height",
                    value = user_height,
                    onClick = {}
                )
                UserInput(
                    title = "Weight",
                    value = user_weight,
                    onClick = {}
                )
            }
//            Spacer(modifier = Modifier.width(15.675.dp))
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
    title: String,
    value: MutableState<String>,
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
        Row (
            modifier = Modifier
                .fillMaxWidth(0.45f)
        ) {
            Text(
                text = title,
                fontSize = 20.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xffffffff)
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                value = value.value,
                onValueChange = { value.value = it },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    cursorColor = Color.White,
                    focusedContainerColor = Color(0xFF0B0BE9),
                    unfocusedContainerColor = Color(0xFF0D0DD5),
                    disabledContainerColor = Color(0xFF0808E2),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    disabledLabelColor = Color.White,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(26.79.dp),
                trailingIcon = {
                    if (title == "Email") {
                        Spacer(modifier = Modifier.width(0.dp))
                    } else {
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
            )
        }
    }
}