package dev.andrescoder.gamingapp.domain.use_cases.auth

import dev.andrescoder.gamingapp.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(private val repository: AuthRepository) {
    operator fun invoke() = repository.currentUser
}