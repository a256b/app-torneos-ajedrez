package com.example.apptorneosajedrez.model

data class RegisterFormState(
    val fullNameError: Int? = null,
    val emailError: Int? = null,
    val passwordError: Int? = null,
    val confirmPasswordError: Int? = null,
    val isDataValid: Boolean = false
)


