package com.example.apptorneosajedrez.model

import java.io.Serializable

data class Jugador(
    val id: String = "",
    val nombre: String = "",
    val estado: String = "pendiente" // puede ser "pendiente", "aceptado", "rechazado"
) : Serializable
