package com.example.sculptify.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import com.example.sculptify.main.MainScaffoldView
import com.example.sculptify.ui.theme.SculptifyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SculptifyTheme {
                Surface() {
                    MainScaffoldView()
                }
            }
        }
    }
}