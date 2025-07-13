package dev.andrescoder.gamingapp.domain.use_cases.users

import dev.andrescoder.gamingapp.domain.model.User
import dev.andrescoder.gamingapp.domain.repository.UsersRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(user: User) = repository.create(user)

}