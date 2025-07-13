package dev.andrescoder.gamingapp.domain.use_cases.users

import dev.andrescoder.gamingapp.domain.repository.UsersRepository
import javax.inject.Inject

class GetUserById @Inject constructor(private val repository: UsersRepository) {
    operator fun invoke(id: String) = repository.getUserById(id)

}