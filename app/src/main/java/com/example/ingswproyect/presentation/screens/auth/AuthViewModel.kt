package com.example.ingswproyect.presentation.screens.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ingswproyect.domain.usecase.auth.GoogleSignInUseCase
import com.example.ingswproyect.domain.usecase.auth.LoginWithEmailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

sealed interface AuthEvent {
    data class EmailChanged(val value: String) : AuthEvent
    data class PasswordChanged(val value: String) : AuthEvent
    data object TogglePasswordVisibility : AuthEvent
    data object LoginClicked : AuthEvent
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginWithEmailUseCase: LoginWithEmailUseCase,
    private val googleSignInUseCase: GoogleSignInUseCase
) : ViewModel() {

    private val _ui = MutableStateFlow(LoginUiState())
    val ui: StateFlow<LoginUiState> = _ui

    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.EmailChanged ->
                _ui.value = _ui.value.copy(email = event.value, error = null)

            is AuthEvent.PasswordChanged ->
                _ui.value = _ui.value.copy(password = event.value, error = null)

            AuthEvent.TogglePasswordVisibility ->
                _ui.value = _ui.value.copy(passwordVisible = !_ui.value.passwordVisible)

            AuthEvent.LoginClicked ->
                loginWithEmail()
        }
    }

    private fun loginWithEmail() {
        viewModelScope.launch {
            _ui.value = _ui.value.copy(isLoading = true, error = null)

            val result = loginWithEmailUseCase(_ui.value.email, _ui.value.password)

            _ui.value = _ui.value.copy(isLoading = false)

            result.onFailure { e ->
                _ui.value = _ui.value.copy(error = e.message ?: "Error al iniciar sesión")
            }
        }
    }

    // 👇 ESTA es la función que MainActivity está llamando
    fun signInWithGoogle(idToken: String, onSuccess: () -> Unit) {
        if (_ui.value.isLoading) return

        viewModelScope.launch {
            _ui.value = _ui.value.copy(isLoading = true, error = null)

            val result = googleSignInUseCase(idToken)

            _ui.value = _ui.value.copy(isLoading = false)

            result
                .onSuccess {
                    onSuccess()
                }
                .onFailure { e ->
                    _ui.value = _ui.value.copy(
                        error = e.message ?: "Error al iniciar sesión con Google"
                    )
                }
        }
    }
}
