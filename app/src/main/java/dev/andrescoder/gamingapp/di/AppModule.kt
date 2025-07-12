package dev.andrescoder.gamingapp.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.andrescoder.gamingapp.data.repository.AuthRepositoryImpl
import dev.andrescoder.gamingapp.domain.repository.AuthRepository
import dev.andrescoder.gamingapp.domain.use_cases.auth.AuthUseCases
import dev.andrescoder.gamingapp.domain.use_cases.auth.GetCurrentUser
import dev.andrescoder.gamingapp.domain.use_cases.auth.Login
import dev.andrescoder.gamingapp.domain.use_cases.auth.Logout
import dev.andrescoder.gamingapp.domain.use_cases.auth.Signup

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun providerFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        signup = Signup(repository)
    )
}