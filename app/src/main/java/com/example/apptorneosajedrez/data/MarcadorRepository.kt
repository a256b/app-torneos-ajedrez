package com.example.apptorneosajedrez.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.example.apptorneosajedrez.model.Marcador
import kotlinx.coroutines.tasks.await

class MarcadorRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun obtenerMarcadores(): List<Marcador> {
        return try {
            val snapshot = db.collection("marcadores").get().await()
            snapshot.documents.mapNotNull { it.toObject<Marcador>()?.copy(id = it.id) }
        } catch (e: Exception) {
            emptyList()
        }
    }
}