package com.example.apptorneosajedrez.model

data class AppUser(
    val uid: String = "",
    val fullName: String = "",
    val email: String = "",
    val userType: String = "",   // por ejemplo: "AFICIONADO", "JUGADOR", "ORGANIZADOR"
    val provider: String // "google", "password", etc.
)
