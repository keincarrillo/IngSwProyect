package com.example.ingswproyect.domain.usecase.auth

import com.example.ingswproyect.domain.repository.AuthRepository

class GoogleSignInUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(idToken: String) =
        repository.signInWithGoogle(idToken)
}
