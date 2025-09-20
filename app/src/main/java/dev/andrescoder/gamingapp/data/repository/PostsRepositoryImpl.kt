package dev.andrescoder.gamingapp.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import dev.andrescoder.gamingapp.core.Constants.POSTS
import dev.andrescoder.gamingapp.domain.model.Post
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.repository.PostsRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostsRepositoryImpl @Inject constructor(
    @Named(POSTS) private val postsRef: CollectionReference,
    @Named(POSTS) private val storagePostsRef: StorageReference,
) : PostsRepository {

    override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow {
        val snapshotListener = postsRef.addSnapshotListener { snapshot, e ->
            val postResponse = if (snapshot != null) {
                val posts = snapshot.toObjects(Post::class.java)
                Response.Success(posts)
            } else {
                Response.Failure(e)
            }
            trySend(postResponse)
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun create(post: Post, file: File): Response<Boolean> {
        return try {
            // Save image to Firestore
            val fromFile = Uri.fromFile(file)
            val imageRef = storagePostsRef.child(file.name)
            val uploadTask = imageRef.putFile(fromFile).await()
            val url = imageRef.downloadUrl.await().toString()

            // Save data post to Firestore
            post.image = url.toString()
            postsRef.add(post).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }

    }

}