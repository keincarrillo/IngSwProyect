package com.example.ingswproyect.domain.usecase.auth


import com.example.ingswproyect.domain.model.Usuario
import com.example.ingswproyect.domain.repository.AuthRepository


class LoginWithEmailUseCase(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Usuario> =
        repository.loginWithEmail(email, password)
}