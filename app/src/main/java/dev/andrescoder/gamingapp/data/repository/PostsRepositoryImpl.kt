package dev.andrescoder.gamingapp.data.repository

import android.net.Uri
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.storage.StorageReference
import dev.andrescoder.gamingapp.core.Constants.POSTS
import dev.andrescoder.gamingapp.core.Constants.USERS
import dev.andrescoder.gamingapp.domain.model.Post
import dev.andrescoder.gamingapp.domain.model.Response
import dev.andrescoder.gamingapp.domain.model.User
import dev.andrescoder.gamingapp.domain.repository.PostsRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostsRepositoryImpl @Inject constructor(
    @Named(POSTS) private val postsRef: CollectionReference,
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(POSTS) private val storagePostsRef: StorageReference,
) : PostsRepository {

    @OptIn(DelicateCoroutinesApi::class)
    override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow {
        val snapshotListener = postsRef.addSnapshotListener { snapshot, e ->

            GlobalScope.launch(Dispatchers.IO) {
                val postResponse = if (snapshot != null) {
                    val posts = snapshot.toObjects(Post::class.java)

                    // Set id for each post
                    snapshot.documents.forEachIndexed { index, document ->
                        posts[index].id = document.id
                    }

                    val idUserArray = ArrayList<String>()
                    posts.forEach { post ->
                        idUserArray.add(post.idUser)
                    }

                    // Id users list without repeated ids
                    val idUserList = idUserArray.toSet().toList()

                    // Get posts for each user asynchronously
                    idUserList.map { id ->
                        async {
                            val user = usersRef.document(id).get().await()
                                .toObject(User::class.java)!!

                            posts.forEach { post ->
                                if (post.idUser == id) {
                                    post.user = user
                                }
                            }
                        }
                    }.forEach {
                        it.await()
                    }

                    Response.Success(posts)
                } else {
                    Response.Failure(e)
                }
                trySend(postResponse)
            }
        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>> = callbackFlow {
        val snapshotListener =
            postsRef.whereEqualTo("idUser", idUser).addSnapshotListener { snapshot, e ->

                val postResponse = if (snapshot != null) {
                    val posts = snapshot.toObjects(Post::class.java)
                    snapshot.documents.forEachIndexed { index, document ->
                        posts[index].id = document.id
                    }

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

    override suspend fun update(
        post: Post,
        file: File?,
    ): Response<Boolean> {
        return try {
            if (file != null) {
                // Save image to Firestore
                val fromFile = Uri.fromFile(file)
                val imageRef = storagePostsRef.child(file.name)
                val uploadTask = imageRef.putFile(fromFile).await()
                val url = imageRef.downloadUrl.await()
                post.image = url.toString()
            }

            val map: MutableMap<String, Any> = HashMap()
            map["name"] = post.name
            map["description"] = post.description
            map["image"] = post.image
            map["category"] = post.category

            // Save data post to Firestore
            postsRef.document(post.id).update(map).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun delete(idPost: String): Response<Boolean> {
        return try {
            postsRef.document(idPost).delete().await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun like(
        idPost: String,
        idUSer: String,
    ): Response<Boolean> {
        return try {
            postsRef.document(idPost)
                .update("likes", FieldValue.arrayUnion(idUSer)).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun deleteLike(
        idPost: String,
        idUSer: String,
    ): Response<Boolean> {
        return try {
            postsRef.document(idPost)
                .update("likes", FieldValue.arrayRemove(idUSer)).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

}