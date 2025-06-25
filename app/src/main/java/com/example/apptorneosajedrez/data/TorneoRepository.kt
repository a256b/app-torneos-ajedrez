package com.example.apptorneosajedrez.data

import com.example.apptorneosajedrez.model.Torneo
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ListenerRegistration

class TorneoRepository {

    private val db = FirebaseFirestore.getInstance()

    fun escucharTorneos(cuandoCambia: (List<Torneo>) -> Unit): ListenerRegistration {
        return db.collection("torneos").addSnapshotListener { snapshot, error ->
            if (error != null || snapshot == null) {
                cuandoCambia(emptyList())
                return@addSnapshotListener
            }
            val torneos = snapshot.documents.mapNotNull { it.toObject<Torneo>() }
            cuandoCambia(torneos)
        }
    }

    fun agregarTorneo(torneo: Torneo, onComplete: (Boolean) -> Unit) {
        db.collection("torneos")
            .add(torneo)
            .addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }
}
