package com.example.ingswproyect.presentation.components


import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size
import com.example.ingswproyect.R


@Composable
fun LoginLogo(modifier: Modifier = Modifier) {
    val isDark = isSystemInDarkTheme()
    val tintColor = if (isDark) Color.White else Color.Black

    Image(
        painter = painterResource(id = R.drawable.login),
        contentDescription = "Logo",
        modifier = modifier.size(250.dp),
        colorFilter = ColorFilter.tint(tintColor)
    )
}