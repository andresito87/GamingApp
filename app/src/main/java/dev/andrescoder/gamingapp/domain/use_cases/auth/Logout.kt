package dev.andrescoder.gamingapp.domain.use_cases.auth

import dev.andrescoder.gamingapp.domain.repository.AuthRepository
import javax.inject.Inject

class Logout @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke() = repository.logout()
}