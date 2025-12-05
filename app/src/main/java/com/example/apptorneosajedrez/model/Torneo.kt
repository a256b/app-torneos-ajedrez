package com.example.apptorneosajedrez.model
import java.io.Serializable

data class Torneo(
    val idTorneo: Int = 0,
    val nombre: String = "",
    val descripcion: String = "",
    val fechaInicio: String = "",
    val fechaFin: String = "",
    val horaInicio: String = "",
    val ubicacion: String = "",
    val estado: EstadoTorneo = EstadoTorneo.PROXIMO
    //TODO: Lista de 7 partidas
    // val partidas: List<Partidas> = emptyList()
) : Serializable
