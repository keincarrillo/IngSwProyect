package com.example.ingswproyect.data.repository


import com.example.ingswproyect.domain.model.Usuario
import com.example.ingswproyect.domain.repository.AuthRepository


class AuthRepositoryImpl : AuthRepository {
    override suspend fun loginWithEmail(email: String, password: String) =
        Result.failure<Usuario>(NotImplementedError("Implementar con Firebase/Backend"))


    override suspend fun signInWithGoogle() =
        Result.failure<Usuario>(NotImplementedError("Implementar con Firebase/Backend"))


    override suspend fun isEmailVerified(email: String): Boolean = false
}