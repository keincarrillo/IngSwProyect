package com.example.ingswproyect.presentation.components


import androidx.compose.material3.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun EmailField(
    value: String,
    isValid: Boolean,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Correo electrónico") },
        modifier = modifier,
        singleLine = true,
        shape = RoundedCornerShape(6.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = if (isValid || value.isEmpty()) MaterialTheme.colorScheme.primary else Color.Red,
            unfocusedBorderColor = if (isValid || value.isEmpty()) MaterialTheme.colorScheme.outline else Color.Red,
            focusedLabelColor = if (isValid || value.isEmpty()) MaterialTheme.colorScheme.primary else Color.Red,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            errorLabelColor = Color.Red
        ),
        supportingText = {
            Text(
                text = "Solo servicios de correo electrónico permitidos.",
                color = MaterialTheme.colorScheme.outline,
                fontSize = 12.sp
            )
        }
    )
}