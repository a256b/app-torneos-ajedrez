package com.example.apptorneosajedrez.model

import com.example.apptorneosajedrez.model.AppUser

data class RegisterResult(
    val success: AppUser? = null,
    val error: String? = null,
    val loading: Boolean = false
)