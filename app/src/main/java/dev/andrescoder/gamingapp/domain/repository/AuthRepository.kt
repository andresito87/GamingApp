package dev.andrescoder.gamingapp.domain.repository

import com.google.firebase.auth.FirebaseUser
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.model.User

interface AuthRepository {

    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    suspend fun signUp(user: User): Response<FirebaseUser>
    suspend fun logout()
}