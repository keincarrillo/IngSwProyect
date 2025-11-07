package com.example.ingswproyect.di

import com.example.ingswproyect.data.repository.AuthRepositoryImpl
import com.example.ingswproyect.domain.repository.AuthRepository
import com.example.ingswproyect.domain.usecase.auth.GoogleSignInUseCase
import com.example.ingswproyect.domain.usecase.auth.LoginWithEmailUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): AuthRepository = AuthRepositoryImpl(auth, firestore)

    @Provides
    fun provideLoginWithEmailUseCase(
        repository: AuthRepository
    ) = LoginWithEmailUseCase(repository)

    @Provides
    fun provideGoogleSignInUseCase(
        repository: AuthRepository
    ) = GoogleSignInUseCase(repository)
}
