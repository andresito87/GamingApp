package dev.andrescoder.gamingapp.domain.repository

import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun create(user: User): Response<Boolean> // Create a new user in the database
    suspend fun update(user: User): Response<Boolean> // Update user information
    fun getUserById(id: String): Flow<User> // Flow is a stream of data in real time

}