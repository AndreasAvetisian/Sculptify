package com.example.sculptify.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sculptify.data.AuthRepository
import com.example.sculptify.data.SignInState
import com.example.sculptify.data.SignUpState
import com.example.sculptify.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
): ViewModel() {

    val _signUpState = Channel<SignUpState>()
    val signUpState = _signUpState.receiveAsFlow()

    fun registerUser(email: String, pw: String) = viewModelScope.launch {
        repository.logInUser(email, pw).collect { result ->
            when (result) {
                is Resource.Success -> {
                    _signUpState.send(SignUpState(isSuccess = "Sign in Success"))
                }
                is Resource.Loading -> {
                    _signUpState.send(SignUpState(isLoading = true))
                }
                is Resource.Error -> {
                    _signUpState.send(SignUpState(isError = result.message))
                }
            }
        }
    }

}