package com.example.sculptify.pages

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sculptify.viewModels.UserViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

@Composable
fun MainView() {
    val userVM: UserViewModel = viewModel()

    val fireStore = Firebase.firestore
    val fAuth = Firebase.auth
    var currentUserRoute by remember { mutableStateOf("") }

    fireStore
        .collection("users")
        .document(fAuth.currentUser?.uid.toString())
        .get()
        .addOnSuccessListener {
            currentUserRoute = it.get("root").toString()
        }

    userVM.getUserData()

    Column {
        Text(
            text = userVM.userdata.value["firstName"].toString(),
            fontSize = 18.sp
        )
        Text(text = userVM.isAuthorized.value.toString())
    }
}