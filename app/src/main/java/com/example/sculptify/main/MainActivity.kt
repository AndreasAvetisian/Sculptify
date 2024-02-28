package com.example.sculptify.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.sculptify.ui.theme.SculptifyTheme
import com.example.sculptify.viewModels.UserViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        installSplashScreen().apply {
//            setKeepOnScreenCondition {
//                viewModel.isLoading.value
//            }
//        }
        installSplashScreen()
        setContent {
            SculptifyTheme {
                Surface {
                    MainScaffoldView()
                }
            }
        }
    }
}