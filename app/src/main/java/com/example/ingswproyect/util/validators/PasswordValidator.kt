package com.example.ingswproyect.util.validators

object PasswordValidator {
    // 8+ chars, 1 minúscula, 1 mayúscula, 1 dígito, 1 especial
    private val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_]).{8,}$")
    fun isValid(pass: String): Boolean = regex.matches(pass)
}
