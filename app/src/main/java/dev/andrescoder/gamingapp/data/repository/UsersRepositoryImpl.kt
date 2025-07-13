package dev.andrescoder.gamingapp.data.repository

import com.google.firebase.firestore.CollectionReference
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.model.User
import dev.andrescoder.gamingapp.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(val usersRef: CollectionReference) : UsersRepository {
    override suspend fun create(user: User): Response<Boolean> {
        return try {
            user.password = "" // Clear password to not save it in the database
            usersRef.document(user.id).set(user).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override fun getUserById(id: String): Flow<User> =
        callbackFlow { // CallbackFlow to emit the user when it changes
            val snapshotListener = usersRef.document(id).addSnapshotListener { snapshot, e ->
                val user = snapshot?.toObject(User::class.java) ?: User()
                trySend(user) // Send the user to the flow
            }
            awaitClose {
                snapshotListener.remove() // Remove the listener when the flow is closed
            }
        }
}