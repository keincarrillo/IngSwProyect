package com.example.ingswproyect.presentation.screens.auth


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.ingswproyect.domain.usecase.auth.LoginWithEmailUseCase
import com.example.ingswproyect.domain.usecase.auth.GoogleSignInUseCase


// Estado UI


data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)


// Intents
sealed interface AuthEvent {
    data class EmailChanged(val value: String): AuthEvent
    data class PasswordChanged(val value: String): AuthEvent
    data object TogglePasswordVisibility: AuthEvent
    data object LoginClicked: AuthEvent
    data object GoogleSignInClicked: AuthEvent
}


class AuthViewModel(
    private val loginWithEmail: LoginWithEmailUseCase,
    private val googleSignIn: GoogleSignInUseCase,
) : ViewModel() {


    private val _ui = MutableStateFlow(LoginUiState())
    val ui: StateFlow<LoginUiState> = _ui


    fun onEvent(event: AuthEvent) {
        when (event) {
            is AuthEvent.EmailChanged -> _ui.value = _ui.value.copy(email = event.value, error = null)
            is AuthEvent.PasswordChanged -> _ui.value = _ui.value.copy(password = event.value, error = null)
            AuthEvent.TogglePasswordVisibility -> _ui.value = _ui.value.copy(passwordVisible = !_ui.value.passwordVisible)
            AuthEvent.LoginClicked -> login()
            AuthEvent.GoogleSignInClicked -> loginGoogle()
        }
    }


    private fun login() {
        viewModelScope.launch {
            _ui.value = _ui.value.copy(isLoading = true, error = null)
            val result = loginWithEmail(_ui.value.email, _ui.value.password)
            _ui.value = _ui.value.copy(isLoading = false)
            result.onFailure { e -> _ui.value = _ui.value.copy(error = e.message) }
        }
    }


    private fun loginGoogle() {
        viewModelScope.launch {
            _ui.value = _ui.value.copy(isLoading = true, error = null)
            val result = googleSignIn()
            _ui.value = _ui.value.copy(isLoading = false)
            result.onFailure { e -> _ui.value = _ui.value.copy(error = e.message) }
        }
    }
}