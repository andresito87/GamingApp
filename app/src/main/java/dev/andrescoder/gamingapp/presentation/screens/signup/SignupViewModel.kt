package dev.andrescoder.gamingapp.presentation.screens.signup

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.model.User
import dev.andrescoder.gamingapp.domain.use_cases.auth.AuthUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
) : ViewModel() {
    var username: MutableState<String> = mutableStateOf("")
    var isValidUsername: MutableState<Boolean> = mutableStateOf(false)
    var usernameErrMsg: MutableState<String> = mutableStateOf("")

    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    var confirmPassword: MutableState<String> = mutableStateOf("")
    var isValidConfirmPassword: MutableState<Boolean> = mutableStateOf(false)
    var confirmPasswordErrMsg: MutableState<String> = mutableStateOf("")

    var isEnabledSignupButton = false;

    private val _signupFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val signupFlow: StateFlow<Response<FirebaseUser>?> = _signupFlow

    fun onSignup() {
        val user = User(
            email = email.value,
            password = password.value
        )
        signup(user)
    }

    fun signup(user1: User) = viewModelScope.launch {
        println("Signup intentado con email=${email.value}, password=${password.value}")

        val user = User(email=email.value, password = password.value)
        _signupFlow.value = Response.Loading
        val result = authUseCases.signup(user)
        _signupFlow.value = result
    }


    fun enableSignupButton() {
        isEnabledSignupButton = isValidUsername.value
                && isEmailValid.value
                && isPasswordValid.value
                && isValidConfirmPassword.value
    }

    fun validateUsername() {
        if (username.value.length > 4) {
            isValidUsername.value = true
            usernameErrMsg.value = ""
        } else {
            isValidUsername.value = false
            usernameErrMsg.value = "El nombre de usuario no es válido"
        }

        enableSignupButton()
    }

    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            isEmailValid.value = true
            emailErrMsg.value = ""
        } else {
            isEmailValid.value = false
            emailErrMsg.value = "El email no es válido"
        }

        enableSignupButton()
    }

    fun validatePassword() {
        if (password.value.length >= 6) {
            isPasswordValid.value = true
            confirmPasswordErrMsg.value = ""
        } else {
            isPasswordValid.value = false
            confirmPasswordErrMsg.value = "La contraseña y su confirmación no coinciden"
        }

        enableSignupButton()
    }

    fun validateConfirmPassword() {
        if (password.value == confirmPassword.value) {
            isValidConfirmPassword.value = true
            passwordErrMsg.value = ""
        } else {
            isPasswordValid.value = false
            passwordErrMsg.value = "Al menos 6 caracteres"
        }

        enableSignupButton()
    }
}