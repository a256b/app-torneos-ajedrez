package com.example.apptorneosajedrez.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.example.apptorneosajedrez.model.Marcador
import com.google.firebase.firestore.ListenerRegistration

class MarcadorRepository {

    private val db = FirebaseFirestore.getInstance()

    fun escucharMarcadores(cuandoCambia: (List<Marcador>) -> Unit): ListenerRegistration {
        return db.collection("marcadores")
            .addSnapshotListener { snapshot, error ->
                if (error != null || snapshot == null) {
                    cuandoCambia(emptyList())
                    return@addSnapshotListener
                }

                val lista = snapshot.documents.mapNotNull {
                    it.toObject<Marcador>()?.copy(id = it.id)
                }

                cuandoCambia(lista)
            }
    }
}