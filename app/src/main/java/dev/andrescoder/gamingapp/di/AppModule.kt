package dev.andrescoder.gamingapp.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.andrescoder.gamingapp.core.Constants.USERS
import dev.andrescoder.gamingapp.data.repository.AuthRepositoryImpl
import dev.andrescoder.gamingapp.data.repository.UsersRepositoryImpl
import dev.andrescoder.gamingapp.domain.repository.AuthRepository
import dev.andrescoder.gamingapp.domain.repository.UsersRepository
import dev.andrescoder.gamingapp.domain.use_cases.auth.AuthUseCases
import dev.andrescoder.gamingapp.domain.use_cases.auth.GetCurrentUser
import dev.andrescoder.gamingapp.domain.use_cases.auth.Login
import dev.andrescoder.gamingapp.domain.use_cases.auth.Logout
import dev.andrescoder.gamingapp.domain.use_cases.auth.Signup
import dev.andrescoder.gamingapp.domain.use_cases.users.Create
import dev.andrescoder.gamingapp.domain.use_cases.users.GetUserById
import dev.andrescoder.gamingapp.domain.use_cases.users.UsersUseCases

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore // connect to firestore

    @Provides
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference =
        db.collection(USERS) // create a collection of users in firestore

    @Provides
    fun providerFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

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
        getUserById = GetUserById(repository)
    )
}