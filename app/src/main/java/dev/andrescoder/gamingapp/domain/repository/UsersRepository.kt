package dev.andrescoder.gamingapp.domain.repository

import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UsersRepository {

    suspend fun create(user: User): Response<Boolean> // Create a new user in the database
    suspend fun update(user: User): Response<Boolean> // Update user information
    suspend fun saveImage(file: File): Response<String> // Save user profile image and return its URL
    fun getUserById(id: String): Flow<User> // Flow is a stream of data in real time

}