package com.example.ingswproyect.presentation.components


import androidx.compose.material3.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.draw.clip
import com.example.ingswproyect.R


@Composable
fun PasswordField(
    value: String,
    passwordVisible: Boolean,
    isValidPassword: Boolean,
    onPasswordChange: (String) -> Unit,
    onVisibilityToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onPasswordChange,
        label = { Text("Contraseña") },
        singleLine = true,
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp)),
        shape = RoundedCornerShape(6.dp),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val icon = if (passwordVisible)
                painterResource(id = R.drawable.ic_eye)
            else painterResource(id = R.drawable.ic_eye_closed)
            IconButton(onClick = onVisibilityToggle) {
                Icon(painter = icon, contentDescription = null)
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = if (isValidPassword || value.isEmpty()) MaterialTheme.colorScheme.primary else Color.Red,
            unfocusedBorderColor = if (isValidPassword || value.isEmpty()) MaterialTheme.colorScheme.outline else Color.Red,
            focusedLabelColor = if (isValidPassword || value.isEmpty()) MaterialTheme.colorScheme.primary else Color.Red,
            unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
            errorLabelColor = Color.Red
        ),
        supportingText = {
            Text(
                text = "La contraseña debe tener 8+ caracteres, minúscula, mayúscula, número y carácter especial.",
                color = MaterialTheme.colorScheme.outline,
                fontSize = 12.sp
            )
        }
    )
}