package com.example.ingswproyect.domain.repository


import com.example.ingswproyect.domain.model.Usuario


interface AuthRepository {
    suspend fun loginWithEmail(email: String, password: String): Result<Usuario>
    suspend fun signInWithGoogle(): Result<Usuario>
    suspend fun isEmailVerified(email: String): Boolean
}
