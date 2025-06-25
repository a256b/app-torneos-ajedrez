package com.example.apptorneosajedrez.model

import java.io.Serializable

data class Inscripcion(
    val id: String = "",
    val idJugador: String = "",
    val idTorneo: String = ""
) : Serializable
