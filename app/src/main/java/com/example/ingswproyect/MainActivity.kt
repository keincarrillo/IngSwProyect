package com.example.ingswproyect

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.example.ingswproyect.presentation.screens.auth.*
import com.example.ingswproyect.presentation.theme.AppTheme   // 👈 importa tu tema

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val ctx = LocalContext.current
            var ui by remember { mutableStateOf(LoginUiState()) }

            AppTheme {                                // 👈 usa tu tema (con tus colores)
                LoginScreen(
                    state = ui,
                    onEmailChange = { ui = ui.copy(email = it) },
                    onPasswordChange = { ui = ui.copy(password = it) },
                    onTogglePasswordVisibility = {
                        ui = ui.copy(passwordVisible = !ui.passwordVisible)
                    },
                    onLoginClick = {
                        Toast.makeText(ctx, "Login: ${ui.email}", Toast.LENGTH_SHORT).show()
                    },
                    onGoogleSignInClick = {
                        Toast.makeText(ctx, "Google Sign-In", Toast.LENGTH_SHORT).show()
                    },
                    onForgotPassword = {
                        Toast.makeText(ctx, "Ir a Recuperar", Toast.LENGTH_SHORT).show()
                    },
                    onGoToRegister = {
                        Toast.makeText(ctx, "Ir a Registro", Toast.LENGTH_SHORT).show()
                    },
                )
            }
        }
    }
}
