package com.example.ingswproyect.di


import com.example.ingswproyect.data.repository.AuthRepositoryImpl
import com.example.ingswproyect.domain.repository.AuthRepository
import com.example.ingswproyect.domain.usecase.auth.GoogleSignInUseCase
import com.example.ingswproyect.domain.usecase.auth.LoginWithEmailUseCase


object AuthModule {
    fun provideAuthRepository(): AuthRepository = AuthRepositoryImpl()
    fun provideLoginWithEmailUseCase(repo: AuthRepository) = LoginWithEmailUseCase(repo)
    fun provideGoogleSignInUseCase(repo: AuthRepository) = GoogleSignInUseCase(repo)
}