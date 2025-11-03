package com.example.ingswproyect.domain.model


data class Usuario(
    val userId: String,
    val email: String,
    val username: String? = null,
    val imagenBase64: String? = null,
    val nombre: String? = null,
    val apellido: String? = null,
    val nacimiento: String? = null,
    val genero: String? = null,
    val peso: Float? = null,
    val altura: Int? = null,
)