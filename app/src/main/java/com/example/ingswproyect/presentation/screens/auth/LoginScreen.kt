package com.example.ingswproyect.presentation.screens.auth

import androidx.compose.runtime.Composable
import androidx.compose.material3.*          // Scaffold, TopAppBar, Button, Text, etc.
import androidx.compose.foundation.layout.* // Column, Spacer, padding, size, fillMaxWidth/Size, height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.*
import androidx.compose.ui.unit.dp
import com.example.ingswproyect.R
import androidx.compose.ui.graphics.Color

import com.example.ingswproyect.presentation.components.*   // EmailField, PasswordField, LoginLogo, LoginFooterText
import com.example.ingswproyect.util.validators.EmailValidator
import com.example.ingswproyect.util.validators.PasswordValidator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    state: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onLoginClick: () -> Unit,
    onGoogleSignInClick: () -> Unit,
    onForgotPassword: () -> Unit,
    onGoToRegister: () -> Unit,
) {
    val isValidEmail = EmailValidator.isValid(state.email)
    val isValidPass = PasswordValidator.isValid(state.password)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Iniciar sesión",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LoginLogo()

            EmailField(
                value = state.email,
                isValid = isValidEmail,
                onValueChange = onEmailChange,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            PasswordField(
                value = state.password,
                passwordVisible = state.passwordVisible,
                isValidPassword = isValidPass,
                onPasswordChange = onPasswordChange,
                onVisibilityToggle = onTogglePasswordVisibility,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onLoginClick,
                enabled = isValidEmail && isValidPass && !state.isLoading,
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(18.dp))
                } else {
                    Text(
                        text = "Iniciar sesión",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = onGoogleSignInClick,
                enabled = !state.isLoading,
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Google",
                        modifier = Modifier.size(18.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Iniciar con Google")
                }
            }


            Spacer(modifier = Modifier.height(24.dp))

            LoginFooterText(
                onForgotPassword = onForgotPassword,
                onGoToRegister = onGoToRegister
            )
        }
    }
}
