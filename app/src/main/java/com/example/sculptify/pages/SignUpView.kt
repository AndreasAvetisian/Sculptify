package com.example.sculptify.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sculptify.AUTHENTICATION_ROUTE
import com.example.sculptify.R
import com.example.sculptify.layout.InputField
import com.example.sculptify.layout.RegConfirmButton
import com.example.sculptify.ui.theme.balooFontFamily
import com.example.sculptify.viewModels.AuthenticationViewModel
import kotlin.math.roundToInt

var pageCounter by mutableIntStateOf(1)

var regEmail by mutableStateOf("")
var regPw by mutableStateOf("")
var isHiddenPw by mutableStateOf(true)
var regFirstName by mutableStateOf("")
var regIsAdmin by mutableStateOf(false)
var regCbs by mutableIntStateOf(15)
var regRbe by mutableIntStateOf(30)
var regDayStreak by mutableIntStateOf(0)
var regWeeklyGoal by mutableFloatStateOf(3f)
var regGender by mutableStateOf("")
var regHeight by mutableStateOf("")
var regWeight by mutableStateOf("")
var regYearOfBirth by mutableStateOf("")

@Composable
fun SignUpView(
    navController: NavHostController,
    authVM: AuthenticationViewModel
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        when (pageCounter) {
            1 -> { RegistrationBeginning(authVM, navController) }
            2 -> { EmailAndPassword() }
            3 -> { NameAndAge() }
            4 -> { HeightAndWeight() }
            5 -> { Gender() }
            6 -> { WeeklyGoal() }
            7 -> { RegistrationEnding(authVM, navController) }
        }
    }
}

@Composable
fun RegistrationBeginning(
    authVM: AuthenticationViewModel,
    navController: NavHostController
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
                .padding(15.675.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hello!",
                color = Color.White,
                fontSize = 60.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Here are some questions to set up an account for you!",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
        Row {
            RegConfirmButton(
                text = "LOG IN",
                bgColor = Color(0xff1C1C1E),
                modifier = Modifier
                    .width(100.dp)
                    .padding(end = 10.dp)
                    .height(60.dp)
                    .clickable {
                        authVM.errorMessage.value = ""
                        navController.navigate(AUTHENTICATION_ROUTE)
                    },
            )
            RegConfirmButton(
                text = "I'M READY",
                bgColor = Color(0xff0060FE),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                        pageCounter += 1
                    }
            )
        }
    }
}
// --------------------------Email and Password----------------------------
@Composable
fun EmailAndPassword() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
                .padding(15.675.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputField(
                value = regEmail,
                onValueChange = { regEmail = it},
                label = "Email",
                keyboardType = KeyboardType.Email,
                visualTransformation = VisualTransformation.None,
                trailingIcon = null,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .padding(40.dp, 10.dp, 40.dp, 10.dp)
                    .fillMaxWidth()
            )
            InputField(
                value = regPw,
                onValueChange = { regPw = it},
                label = "Password",
                keyboardType = KeyboardType.Password,
                visualTransformation = if (isHiddenPw) PasswordVisualTransformation() else  VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        painter = painterResource(
                            id = if (isHiddenPw) R.drawable.password_hidden else R.drawable.password_revealed
                        ),
                        contentDescription = "password icon",
                        tint = Color.White,
                        modifier = Modifier.clickable {
                            isHiddenPw = !isHiddenPw
                        },
                    )
                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .padding(40.dp, 10.dp, 40.dp, 10.dp)
                    .fillMaxWidth()
            )
        }
        Row {
            RegConfirmButton(
                text = "LOG IN",
                bgColor = Color(0xff1C1C1E),
                modifier = Modifier
                    .width(100.dp)
                    .padding(end = 10.dp)
                    .height(60.dp)
                    .clickable {
                        pageCounter -= 1
                    },
            )
            RegConfirmButton(
                text = "NEXT",
                bgColor = if (regEmail.isNotEmpty() && regPw.isNotEmpty()) {
                    Color(0xff0060FE)
                } else Color(0xff0060FE).copy(alpha = 0.2f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                        if (regEmail.isNotEmpty() && regPw.isNotEmpty()) {
                            pageCounter += 1
                        }
                    }
            )
        }
    }
}

// -------------------------------Name and Age-------------------------

@Composable
fun NameAndAge() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
                .padding(15.675.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your name is",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold
            )
            InputField(
                value = regFirstName,
                onValueChange = { regFirstName = it },
                label = "",
                keyboardType = KeyboardType.Text,
                visualTransformation = VisualTransformation.None,
                trailingIcon = null,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(40.dp, 10.dp, 40.dp, 10.dp)
                    .width(200.dp)
            )
            Text(
                text = "and you were born in",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold
            )
            InputField(
                value = regYearOfBirth,
                onValueChange = { regYearOfBirth = it },
                label = "",
                keyboardType = KeyboardType.Number,
                visualTransformation = VisualTransformation.None,
                trailingIcon = null,
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(40.dp, 10.dp, 40.dp, 10.dp)
                    .width(200.dp)
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            RegConfirmButton(
                text = "BACK",
                bgColor = Color(0xff1C1C1E),
                modifier = Modifier
                    .width(100.dp)
                    .padding(end = 10.dp)
                    .height(60.dp)
                    .clickable {
                        pageCounter -= 1
                    },
            )
            RegConfirmButton(
                text = "NEXT",
                bgColor = if (regFirstName.isNotEmpty() && regYearOfBirth.isNotEmpty()) {
                    Color(0xff0060FE)
                } else Color(0xff0060FE).copy(alpha = 0.2f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                        if (regFirstName.isNotEmpty() && regYearOfBirth.isNotEmpty()) {
                            pageCounter += 1
                        }
                    }
            )
        }
    }
}

// ---------------------------Height and Weight--------------------------------

@Composable
fun HeightAndWeight() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
                .padding(15.675.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Your height is",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold
            )
            InputField(
                value = regHeight,
                onValueChange = { regHeight = it },
                label = "",
                keyboardType = KeyboardType.Number,
                visualTransformation = VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.height_icon
                        ),
                        contentDescription = "password icon",
                        tint = Color.White,
                        modifier = Modifier
                            .size(50.dp)
                    )
                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(40.dp, 10.dp, 40.dp, 10.dp)
                    .width(200.dp)
            )
            Text(
                text = "and weight is",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold
            )
            InputField(
                value = regWeight,
                onValueChange = { regWeight = it },
                label = "",
                keyboardType = KeyboardType.Number,
                visualTransformation = VisualTransformation.None,
                trailingIcon = {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.weight_icon
                        ),
                        contentDescription = "password icon",
                        tint = Color.White,
                        modifier = Modifier
                            .size(30.dp)
                    )
                },
                textStyle = TextStyle(
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(40.dp, 10.dp, 40.dp, 10.dp)
                    .width(200.dp)
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            RegConfirmButton(
                text = "BACK",
                bgColor = Color(0xff1C1C1E),
                modifier = Modifier
                    .width(100.dp)
                    .padding(end = 10.dp)
                    .height(60.dp)
                    .clickable {
                        pageCounter -= 1
                    },
            )
            RegConfirmButton(
                text = "NEXT",
                bgColor = Color(0xff0060FE),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                        if (regHeight.isNotEmpty() && regWeight.isNotEmpty()) {
                            pageCounter += 1
                        }
                    }
            )
        }
    }
}

// ---------------------------Gender Selection------------------------------

@Composable
fun Gender() {
    var selectedButton by remember { mutableStateOf(GenderButton.Male) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.90f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 20.dp),
                text = "What's your gender?",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GenderButton(
                    text = "Male",
                    textSize = 30.sp,
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                        .clickable {
                            selectedButton = GenderButton.Male
                            regGender = "Male"
                        },
                    iconId = R.drawable.male,
                    tint = if (selectedButton == GenderButton.Male) Color.White else Color(0xff0000ff),
                    iconModifier = Modifier.size(50.dp),
                    selected = selectedButton == GenderButton.Male
                )
                GenderButton(
                    text = "Female",
                    textSize = 30.sp,
                    modifier = Modifier
                        .width(120.dp)
                        .height(120.dp)
                        .clickable {
                            selectedButton = GenderButton.Female
                            regGender = "Female"
                        },
                    iconId = R.drawable.female,
                    tint = if (selectedButton == GenderButton.Female) Color.White else Color(0xffFFC0CB),
                    iconModifier = Modifier.size(50.dp),
                    selected = selectedButton == GenderButton.Female
                )
            }
            GenderButton(
                text = "Others / I'd rather not say",
                textSize = 20.sp,
                modifier = Modifier
                    .padding(start = 45.dp, top = 20.dp, end = 45.dp)
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                        selectedButton = GenderButton.Others
                        regGender = "Others"
                    },
                iconId = R.drawable.female,
                tint = Color(0xffFFC0CB),
                iconModifier = Modifier.size(0.dp),
                selected = selectedButton == GenderButton.Others
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            RegConfirmButton(
                text = "BACK",
                bgColor = Color(0xff1C1C1E),
                modifier = Modifier
                    .width(100.dp)
                    .padding(end = 10.dp)
                    .height(60.dp)
                    .clickable {
                        pageCounter -= 1
                    },
            )
            RegConfirmButton(
                text = "NEXT",
                bgColor =  if (regGender.isNotEmpty()) {
                    Color(0xff0060FE)
                } else Color(0xff0060FE).copy(alpha = 0.2f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                        if (regGender.isNotEmpty()) {
                            pageCounter += 1
                        }
                    }
            )
        }
    }
}

enum class GenderButton {
    Male,
    Female,
    Others
}

@Composable
fun GenderButton(
    text: String,
    textSize: TextUnit,
    modifier: Modifier,
    iconId: Int,
    iconModifier: Modifier,
    tint: Color,
    selected: Boolean
    ) {

    val buttonColor = if (selected) Color(0xff0000ff) else Color(0xff1C1C1E)

    Card (
        colors = CardDefaults.cardColors(buttonColor),
        shape = MaterialTheme.shapes.extraLarge,
        modifier = modifier
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = iconModifier,
                painter = painterResource(id = iconId),
                contentDescription = "",
                tint = tint
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = text,
                color = Color.White,
                fontSize = textSize,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

// ---------------------------Weekly Goal--------------------------------

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeeklyGoal() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
                .padding(15.675.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "How often would you like to work out?",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Icon(
                painter = painterResource(
                    id = when (regWeeklyGoal) {
                        1f -> R.drawable.weekly_goal_1
                        2f -> R.drawable.weekly_goal_2
                        3f -> R.drawable.weekly_goal_3
                        4f -> R.drawable.weekly_goal_4
                        else -> R.drawable.weekly_goal_1
                    }
                ),
                contentDescription = "",
                tint = when (regWeeklyGoal) {
                    1f -> Color(0xff0000FF)
                    2f -> Color(0xffFFFF00)
                    3f -> Color(0xffFFA500)
                    4f -> Color(0xffFF0000 )
                    else -> Color.Blue
                },
                modifier = Modifier
                    .size(100.dp)
            )
            Text(
                text = if (regWeeklyGoal == 1f) "time" else "times" + " / week",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Slider(
                value = regWeeklyGoal,
                onValueChange = { newValue ->
                    regWeeklyGoal = newValue
                },
                modifier = Modifier
                    .padding(top = 20.dp),
                valueRange = 1f..4f,
                steps = 2,
                track = {
                    Card (
                        colors = CardDefaults.cardColors(Color(0xff0060FE).copy(alpha = 0.2f)),
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                    ) {}
                },
                thumb = {
                    Card (
                        colors = CardDefaults.cardColors(
                            when (regWeeklyGoal) {
                                1f -> Color(0xff0000FF)
                                2f -> Color(0xffFFFF00)
                                3f -> Color(0xffFFA500)
                                4f -> Color(0xffFF0000 )
                                else -> Color.Blue
                            }
                        ),
                        shape = MaterialTheme.shapes.extraLarge,
                        modifier = Modifier
                            .size(40.dp)
                    ) {}
                },
            )
            Row (
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Less",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "More",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = balooFontFamily,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            RegConfirmButton(
                text = "BACK",
                bgColor = Color(0xff1C1C1E),
                modifier = Modifier
                    .width(100.dp)
                    .padding(end = 10.dp)
                    .height(60.dp)
                    .clickable {
                        pageCounter -= 1
                    },
            )
            RegConfirmButton(
                text = "NEXT",
                bgColor = Color(0xff0060FE),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                        pageCounter += 1
                    }
            )
        }
    }
}

// ---------------------------Registration Ending-----------------------------
@Composable
fun RegistrationEnding(
    authVM: AuthenticationViewModel,
    navController: NavHostController
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(15.675.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92f)
                .padding(15.675.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Done!",
                color = Color.White,
                fontSize = 60.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Let's get started!",
                color = Color.White,
                fontSize = 30.sp,
                fontFamily = balooFontFamily,
                fontWeight = FontWeight.Bold
            )

        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            RegConfirmButton(
                text = "BACK",
                bgColor = Color(0xff1C1C1E),
                modifier = Modifier
                    .width(100.dp)
                    .padding(end = 10.dp)
                    .height(60.dp)
                    .clickable {
                        pageCounter -= 1
                    },
            )
            RegConfirmButton(
                text = "CREATE AN ACCOUNT",
                bgColor = if (regEmail.isNotEmpty() && regPw.isNotEmpty()) {
                    Color(0xff0060FE)
                } else Color(0xff0060FE).copy(alpha = 0.2f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .clickable {
                        if (regEmail.isNotEmpty() && regPw.isNotEmpty()) {
                            authVM.signUpUser(
                                regEmail,
                                regPw,
                                regFirstName,
                                regIsAdmin,
                                regCbs,
                                regRbe,
                                regDayStreak,
                                regWeeklyGoal.roundToInt(),
                                regGender,
                                regHeight.toInt(),
                                regWeight.toInt(),
                                regYearOfBirth.toInt(),
                                navController
                            )
                            regEmail = ""
                            regPw = ""
                            regFirstName = ""
                            regGender = ""
                            regHeight = ""
                            regWeight = ""
                            regYearOfBirth = ""
                            pageCounter = 1
                        }
                    }
            )
        }
    }
}


