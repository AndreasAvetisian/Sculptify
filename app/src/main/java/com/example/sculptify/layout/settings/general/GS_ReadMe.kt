package com.example.sculptify.layout.settings.general

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sculptify.layout.general.customText.CustomText

@Composable
fun GS_ReadMe() {
    LazyColumn (
        modifier = Modifier
            .padding(horizontal = 15.675.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Divider(
                modifier = Modifier
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = Color(0xFF909090)
            )
            CustomText(
                text = "Looking to build muscle and lose weight? Introducing Sculptify, your perfect companion for achieving these goals from the comfort of your home! No equipment needed, easy to follow at home. Follow the tailored plans and expert guidance to meet a fitter, stronger you! Specially designed for all fitness levels, from beginners to experts, Sculptify offers a wide range of workouts and personalized plans to help you sculpt your body the way you desire. \n" +
                        "\n" +
                        "Key features:  \n" +
                        "\n" +
                        "Personalized Fitness Plans: Sculptify will help you on your unique fitness journey. When registering, enter important information like your height, weight, and your physical level of training (beginner -> intermediate -> advanced -> expert). Set your preferred workout frequency and receive timely notifications to ensure you never miss your training. \n" +
                        "\n" +
                        "Targeted Muscle Group Workouts: Choose your focus with targeted exercises for specific muscle groups including arms, legs, chest, shoulders, back, and abs. Select your difficulty level, and Sculptify will recommend exercises and repetitions accordingly. \n" +
                        "\n" +
                        "Progress Tracking: Stay motivated by tracking your progress effortlessly. Sculptify allows you to record your body weight, workout duration, and calories burned. Easily monitor your achievements and celebrate your fitness milestones. ",
            )
        }
    }
}