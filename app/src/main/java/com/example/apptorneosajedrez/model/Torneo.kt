package com.example.apptorneosajedrez.model

data class Torneo(
    val idTorneo: Int,
    val nombre: String,
    val imagen: String,
    val fechaInicio: String ,
    val fechaFin: String,
    val descripcion: String,
    val ubicacion: String,
    val telefonoContacto: String,
    val web: String,
//    var partidas: MutableList<Partida> = mutableListOf(),
//    var inscriptos: MutableList<Jugador> = mutableListOf()
)
