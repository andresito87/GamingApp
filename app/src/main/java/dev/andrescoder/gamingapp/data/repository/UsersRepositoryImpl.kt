package dev.andrescoder.gamingapp.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import dev.andrescoder.gamingapp.core.Constants.USERS
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.model.User
import dev.andrescoder.gamingapp.domain.repository.UsersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class UsersRepositoryImpl @Inject constructor(
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(USERS) private val storageUsersRef: StorageReference,
) : UsersRepository {
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

    override suspend fun update(user: User): Response<Boolean> {
        return try {
            val mapUserData: MutableMap<String, Any> = HashMap()
            mapUserData["username"] = user.username
            mapUserData["image"] = user.image

            usersRef.document(user.id).update(mapUserData).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun saveImage(file: File): Response<String> {
        return try {
            val fromFile = Uri.fromFile(file)
            val imageRef = storageUsersRef.child(file.name)
            val uploadTask = imageRef.putFile(fromFile).await()
            val url = imageRef.downloadUrl.await().toString()
            return Response.Success(url)
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