package com.example.ingswproyect.presentation.components


import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.foundation.clickable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun LoginFooterText(
    onForgotPassword: () -> Unit,
    onGoToRegister: () -> Unit,
) {
    Text(
        buildAnnotatedString {
            withStyle(SpanStyle()) { append("¿Olvidaste tu contraseña? ") }
            withStyle(SpanStyle(color = androidx.compose.material3.MaterialTheme.colorScheme.primary)) { append("Aquí") }
        },
        fontSize = 14.sp,
        modifier = Modifier.clickable { onForgotPassword() }
    )


    Spacer(modifier = Modifier.height(8.dp))


    Text(
        buildAnnotatedString {
            withStyle(SpanStyle()) { append("¿No tienes una cuenta? ") }
            withStyle(SpanStyle(color = androidx.compose.material3.MaterialTheme.colorScheme.primary)) { append("Regístrate") }
        },
        fontSize = 14.sp,
        modifier = Modifier.clickable { onGoToRegister() }
    )
}