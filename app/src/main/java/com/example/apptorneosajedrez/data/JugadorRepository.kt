package com.example.apptorneosajedrez.data

import com.example.apptorneosajedrez.model.Jugador
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.toObject

class JugadorRepository {
    private val db = FirebaseFirestore.getInstance()

    fun escucharJugadores(onChange: (List<Jugador>) -> Unit): ListenerRegistration {
        return db.collection("jugadores")
            .addSnapshotListener { snapshot, error ->
                if (error != null || snapshot == null) {
                    onChange(emptyList())
                    return@addSnapshotListener
                }

                val jugadores = snapshot.documents.mapNotNull {
                    it.toObject<Jugador>()?.copy(id = it.id)
                }
                onChange(jugadores)
            }
    }


    fun actualizarEstadoJugador(id: String, nuevoEstado: String) {
        db.collection("jugadores").document(id)
            .update("estado", nuevoEstado)
    }

    fun eliminarJugador(id: String) {
        db.collection("jugadores").document(id).delete()
    }
}
