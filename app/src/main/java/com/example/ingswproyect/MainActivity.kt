package com.example.ingswproyect

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ingswproyect.presentation.screens.auth.AuthEvent
import com.example.ingswproyect.presentation.screens.auth.AuthViewModel
import com.example.ingswproyect.presentation.screens.auth.LoginScreen
import com.example.ingswproyect.presentation.theme.AppTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import androidx.compose.runtime.collectAsState

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val context = LocalContext.current

            // ViewModel inyectado por Hilt
            val viewModel: AuthViewModel = hiltViewModel()
            val state by viewModel.ui.collectAsState()   // <- usamos el StateFlow del VM

            // Configuración Google Sign-In
            val gso = remember {
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(context.getString(R.string.default_web_client_id))
                    .requestEmail()
                    .build()
            }
            val googleSignInClient = remember { GoogleSignIn.getClient(context, gso) }

            // Launcher para el intent de Google
            val googleLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartActivityForResult()
            ) { result ->
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    val idToken = account.idToken
                    if (idToken != null) {
                        // Pasamos el token al ViewModel
                        viewModel.signInWithGoogle(idToken) {
                            Toast.makeText(
                                context,
                                "Inicio de sesión con Google exitoso",
                                Toast.LENGTH_SHORT
                            ).show()
                            // Aquí luego navegas a Home con NavHost
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "No se pudo obtener el token de Google.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(
                        context,
                        "Error al iniciar con Google.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            // Mostrar errores que vengan del ViewModel
            LaunchedEffect(state.error) {
                state.error?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

            AppTheme {
                LoginScreen(
                    state = state,
                    onEmailChange = { viewModel.onEvent(AuthEvent.EmailChanged(it)) },
                    onPasswordChange = { viewModel.onEvent(AuthEvent.PasswordChanged(it)) },
                    onTogglePasswordVisibility = {
                        viewModel.onEvent(AuthEvent.TogglePasswordVisibility)
                    },
                    onLoginClick = {
                        viewModel.onEvent(AuthEvent.LoginClicked)
                    },
                    onGoogleSignInClick = {
                        // Lanzamos el flujo de Google
                        googleLauncher.launch(googleSignInClient.signInIntent)
                    },
                    onForgotPassword = {
                        Toast.makeText(context, "Ir a Recuperar contraseña", Toast.LENGTH_SHORT).show()
                    },
                    onGoToRegister = {
                        Toast.makeText(context, "Ir a Registro", Toast.LENGTH_SHORT).show()
                    },
                )
            }
        }
    }
}
