package dev.andrescoder.gamingapp.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.andrescoder.gamingapp.core.Constants.POSTS
import dev.andrescoder.gamingapp.core.Constants.USERS
import dev.andrescoder.gamingapp.data.repository.AuthRepositoryImpl
import dev.andrescoder.gamingapp.data.repository.PostsRepositoryImpl
import dev.andrescoder.gamingapp.data.repository.UsersRepositoryImpl
import dev.andrescoder.gamingapp.domain.repository.AuthRepository
import dev.andrescoder.gamingapp.domain.repository.PostsRepository
import dev.andrescoder.gamingapp.domain.repository.UsersRepository
import dev.andrescoder.gamingapp.domain.use_cases.auth.AuthUseCases
import dev.andrescoder.gamingapp.domain.use_cases.auth.GetCurrentUser
import dev.andrescoder.gamingapp.domain.use_cases.auth.Login
import dev.andrescoder.gamingapp.domain.use_cases.auth.Logout
import dev.andrescoder.gamingapp.domain.use_cases.auth.Signup
import dev.andrescoder.gamingapp.domain.use_cases.posts.CreatePost
import dev.andrescoder.gamingapp.domain.use_cases.posts.DeletePost
import dev.andrescoder.gamingapp.domain.use_cases.posts.GetPosts
import dev.andrescoder.gamingapp.domain.use_cases.posts.GetPostsByIdUser
import dev.andrescoder.gamingapp.domain.use_cases.posts.PostsUseCases
import dev.andrescoder.gamingapp.domain.use_cases.posts.UpdatePost
import dev.andrescoder.gamingapp.domain.use_cases.users.Create
import dev.andrescoder.gamingapp.domain.use_cases.users.GetUserById
import dev.andrescoder.gamingapp.domain.use_cases.users.SaveImage
import dev.andrescoder.gamingapp.domain.use_cases.users.Update
import dev.andrescoder.gamingapp.domain.use_cases.users.UsersUseCases
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore // connect to firestore

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS)
    fun provideStorageUsersRef(storage: FirebaseStorage) =
        storage.reference.child(USERS) // create a folder of users in storage

    @Provides
    @Named(USERS)
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference =
        db.collection(USERS) // create a collection of users in firestore

    @Provides
    @Named(POSTS)
    fun provideStoragePostsRef(storage: FirebaseStorage) =
        storage.reference.child(POSTS) // create a folder of posts in storage

    @Provides
    @Named(POSTS)
    fun providePostsRef(db: FirebaseFirestore): CollectionReference =
        db.collection(POSTS) // create a collection of posts in firestore

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun providePostsRepository(impl: PostsRepositoryImpl): PostsRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signup = Signup(repository)
    )

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository),
        update = Update(repository),
        saveImage = SaveImage(repository)
    )

    @Provides
    fun providePostsUseCases(repository: PostsRepository) = PostsUseCases(
        create = CreatePost(repository),
        getPosts = GetPosts(repository),
        getPostsByIdUser = GetPostsByIdUser(repository),
        deletePost = DeletePost(repository),
        updatePost = UpdatePost(repository)
    )
}