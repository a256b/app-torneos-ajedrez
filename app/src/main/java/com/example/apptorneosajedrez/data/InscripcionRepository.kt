package com.example.apptorneosajedrez.data

import com.example.apptorneosajedrez.model.Inscripcion
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class InscripcionRepository {
    private val db = FirebaseFirestore.getInstance()
    private val inscripcionesRef = db.collection("inscripciones")

    fun escucharInscripciones(onChange: (List<Inscripcion>) -> Unit) {
        inscripcionesRef.addSnapshotListener { snapshot, error ->
            if (error != null || snapshot == null) {
                onChange(emptyList())
                return@addSnapshotListener
            }

            val lista = snapshot.documents.mapNotNull {
                it.toObject<Inscripcion>()?.copy(id = it.id)
            }
            onChange(lista)
        }
    }

    fun agregarInscripcion(idJugador: String, idTorneo: String) {
        val nueva = hashMapOf("idJugador" to idJugador, "idTorneo" to idTorneo)
        inscripcionesRef.add(nueva)
    }

    fun eliminarInscripcion(id: String) {
        inscripcionesRef.document(id).delete()
    }
}
