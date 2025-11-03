package com.example.ingswproyect.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors: ColorScheme = lightColorScheme(
    primary        = VerdePrincipal,
    onPrimary      = Blanco,
    secondary      = VerdeSecundario,
    onSecondary    = Blanco,
    tertiary       = VerdeClaro,
    onTertiary     = Color.White,

    background     = Blanco,
    onBackground   = Negro,
    surface        = Blanco,
    onSurface      = Negro,

    surfaceVariant = VerdeContenedor,
    outline        = VerdeBorde,

    error          = RojoClaro,
    onError        = Blanco
)

private val DarkColors: ColorScheme = darkColorScheme(
    primary        = VerdePrincipal,
    onPrimary      = Blanco,
    secondary      = VerdeSecundario,
    onSecondary    = Blanco,
    tertiary       = VerdeClaro,
    onTertiary     = Color.Black,

    background     = FondoOscuro,
    onBackground   = Blanco,
    surface        = Color(0xFF151A19),
    onSurface      = Blanco,

    surfaceVariant = Color(0xFF1E2422),
    outline        = GrisMedio,

    error          = RojoClaro,
    onError        = Color.Black
)

@Composable
fun AppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = MaterialTheme.typography,
        shapes = MaterialTheme.shapes,
        content = content
    )
}
