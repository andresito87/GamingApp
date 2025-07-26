package dev.andrescoder.gamingapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.use_cases.auth.AuthUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
) : ViewModel() {

    // State form
    var state by mutableStateOf(LoginState())
        private set

    // Email
    var isEmailValid: Boolean by mutableStateOf(false)
        private set
    var emailErrMsg: String by mutableStateOf("")
        private set

    // Password
    var isPasswordValid: Boolean by mutableStateOf(false)
        private set
    var passwordErrMsg: String by mutableStateOf("")
        private set

    // Enable Button
    var isEnabledLoginButton = false;

    // Login response
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow

    val currentUser = authUseCases.getCurrentUser()

    init {
        if (currentUser != null) { // already logged in
            loginResponse = Response.Success(currentUser)
        }
    }

    fun onEmailChanged(value: String) {
        state = state.copy(email = value)
    }

    fun onPasswordChanged(value: String) {
        state = state.copy(password = value)
    }

    // launch allows to use coroutines
    fun login() = viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCases.login(state.email, state.password)
        loginResponse = result
    }

    fun enableLoginButton() {
        isEnabledLoginButton = isEmailValid && isPasswordValid
    }

    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrMsg = ""
        } else {
            isEmailValid = false
            emailErrMsg = "El email no es válido"
        }

        enableLoginButton()
    }

    fun validatePassword() {
        if (state.password.length >= 6) {
            isPasswordValid = true
            passwordErrMsg = ""
        } else {
            isPasswordValid = false
            passwordErrMsg = "Al menos 6 caracteres"
        }

        enableLoginButton()
    }
}