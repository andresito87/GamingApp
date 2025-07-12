package dev.andrescoder.gamingapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.andrescoder.gamingapp.domain.use_cases.auth.AuthUseCases
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val authUseCases: AuthUseCases) : ViewModel() {
    suspend fun logout() = authUseCases.logout()


}