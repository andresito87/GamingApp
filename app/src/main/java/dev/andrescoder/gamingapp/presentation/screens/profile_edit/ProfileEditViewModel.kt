package dev.andrescoder.gamingapp.presentation.screens.profile_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.andrescoder.gamingapp.domain.model.User
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    var state by mutableStateOf(ProfileEditState())
        private set

    var usernameErrMsg by mutableStateOf("")
        private set

    val data = savedStateHandle.get<String>("user")
    val user = User.fromJson(data!!)

    init {
        state = state.copy(username = user.username)
    }

    fun onUsernameInput(username: String) {
        state = state.copy(username = username)
    }

    fun validateUsername() {
        if (state.username.length > 4) {
            usernameErrMsg = ""
        } else {
            usernameErrMsg = "El nombre de usuario no es válido"
        }
    }
}