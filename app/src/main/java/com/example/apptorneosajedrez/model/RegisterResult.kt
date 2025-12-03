package com.example.apptorneosajedrez.model

data class RegisterResult(
    val success: AppUser? = null,
    val error: String? = null,
    val loading: Boolean = false
)