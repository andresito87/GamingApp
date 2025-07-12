package dev.andrescoder.gamingapp.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.model.User
import dev.andrescoder.gamingapp.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await
import  javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthRepository {

    override val currentUser: FirebaseUser? get() = firebaseAuth.currentUser

    // we use suspend functions to do suspended tasks with coroutines
    override suspend fun login(email: String, password: String): Response<FirebaseUser> {

        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            Response.Success(result.user!!)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Response.Failure(ex)
        }
    }

    override suspend fun signUp(user: User): Response<FirebaseUser> {
        return try {
            if (user.email.isBlank() || user.password.isBlank()) {
                return Response.Failure(IllegalArgumentException("El email y la contraseña no pueden estar vacíos"))
            }

            val result = firebaseAuth
                .createUserWithEmailAndPassword(user.email, user.password)
                .await()

            Response.Success(result.user!!)
        } catch (ex: Exception) {
            ex.printStackTrace()
            Response.Failure(ex)
        }
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }
}