package com.example.apptorneosajedrez.model

data class Torneo(
    val idTorneo: Int = 0,
    val nombre: String = "",
    val descripcion: String = "",
    val fechaInicio: String = "",
    val fechaFin: String = "",
    val horaInicio: String = "",
    val ubicacion: String = ""
)
