package dev.andrescoder.gamingapp.presentation.screens.profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.andrescoder.gamingapp.domain.model.User
import dev.andrescoder.gamingapp.domain.use_cases.auth.AuthUseCases
import dev.andrescoder.gamingapp.domain.use_cases.users.UsersUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authUseCases: AuthUseCases,
    private val usersUseCases: UsersUseCases,
) : ViewModel() {

    var userData by mutableStateOf(User())
        private set // Expose only the getter, not the setter, to prevent unwanted modifications
    val currentUser = authUseCases.getCurrentUser()

    init {
        getUserById() // Fetch user data when the ViewModel is initialized
    }

    private fun getUserById() = viewModelScope.launch {
        usersUseCases.getUserById(currentUser!!.uid).collect { userdata: User ->
            userData = userdata
        }
    }

    suspend fun logout() = authUseCases.logout()


}