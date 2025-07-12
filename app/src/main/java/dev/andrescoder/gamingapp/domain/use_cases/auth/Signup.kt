package dev.andrescoder.gamingapp.domain.use_cases.auth

import dev.andrescoder.gamingapp.domain.model.User
import dev.andrescoder.gamingapp.domain.repository.AuthRepository
import javax.inject.Inject

class Signup @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(user: User) =
        repository.signUp(user)
}