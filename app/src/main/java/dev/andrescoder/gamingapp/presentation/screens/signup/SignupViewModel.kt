package dev.andrescoder.gamingapp.presentation.screens.signup

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.model.User
import dev.andrescoder.gamingapp.domain.use_cases.auth.AuthUseCases
import dev.andrescoder.gamingapp.domain.use_cases.users.UsersUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val usersUseCase: UsersUseCases,
) : ViewModel() {

    var state by mutableStateOf(SignupState())
        private set

    var isValidUsername by mutableStateOf(false)
        private set
    var usernameErrMsg by mutableStateOf("")
        private set

    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrMsg by mutableStateOf("")
        private set

    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrMsg by mutableStateOf("")
        private set

    var isValidConfirmPassword by mutableStateOf(false)
        private set
    var confirmPasswordErrMsg by mutableStateOf("")
        private set

    var isEnabledSignupButton = false;

    var signupResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    var user = User()

    fun onUsernameInput(username: String) {
        state = state.copy(username = username)
    }

    fun onEmailInput(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String) {
        state = state.copy(password = password)
    }

    fun onConfirmPasswordInput(confirmPassword: String) {
        state = state.copy(confirmPassword = confirmPassword)
    }

    fun onSignup() {
        user.username = state.username
        user.email = state.email
        user.password = state.password
        signup(user)
    }

    fun signup(user1: User) = viewModelScope.launch {
        println("Signup intentado con email=${state.email}, password=${state.password}")

        val user = User(email = state.email, password = state.password)
        signupResponse = Response.Loading
        val result = authUseCases.signup(user)
        signupResponse = result
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()!!.uid
        usersUseCase.create(user)
    }

    fun enableSignupButton() {
        isEnabledSignupButton = isValidUsername
                && isEmailValid
                && isPasswordValid
                && isValidConfirmPassword
    }

    fun validateUsername() {
        if (state.username.length > 4) {
            isValidUsername = true
            usernameErrMsg = ""
        } else {
            isValidUsername = false
            usernameErrMsg = "El nombre de usuario no es válido"
        }

        enableSignupButton()
    }

    fun validateEmail() {
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrMsg = ""
        } else {
            isEmailValid = false
            emailErrMsg = "El email no es válido"
        }

        enableSignupButton()
    }

    fun validatePassword() {
        if (state.password.length >= 6) {
            isPasswordValid = true
            confirmPasswordErrMsg = ""
        } else {
            isPasswordValid = false
            confirmPasswordErrMsg = "La contraseña y su confirmación no coinciden"
        }

        enableSignupButton()
    }

    fun validateConfirmPassword() {
        if (state.password == state.confirmPassword) {
            isValidConfirmPassword = true
            passwordErrMsg = ""
        } else {
            isPasswordValid = false
            passwordErrMsg = "Al menos 6 caracteres"
        }

        enableSignupButton()
    }
}