package com.example.sculptify.data

import com.example.sculptify.util.Resource
import com.google.firebase.auth.AuthResult

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun logInUser (email: String, pw: String): Flow<Resource<AuthResult>>
    fun registerUser (email: String, pw: String): Flow<Resource<AuthResult>>
}