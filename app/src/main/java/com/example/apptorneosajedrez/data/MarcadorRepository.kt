package com.example.apptorneosajedrez.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.example.apptorneosajedrez.model.Marcador
import com.google.firebase.firestore.ListenerRegistration

class MarcadorRepository {

    private val db = FirebaseFirestore.getInstance()

    /**
     * Escucha los marcadores en la base de datos en tiempo real.
     * Llama a [cuandoCambia] cada vez que cambia el listado de marcadores.
     */
    fun escucharMarcadores(cuandoCambia: (List<Marcador>) -> Unit): ListenerRegistration {
        return db.collection("marcadores").addSnapshotListener { snapshot, error ->
                /**
                 * Si hay un error o no se recibió snapshot, se llama a [cuandoCambia] con
                 * una lista vacía.
                 */
                if (error != null || snapshot == null) {
                    cuandoCambia(emptyList())
                    return@addSnapshotListener
                }

                /**
                 * Se mapea cada documento de la snapshot a un [Marcador] y se agrega el id
                 * del documento a la propiedad [Marcador.id].
                 */
                val lista = snapshot.documents.mapNotNull {
                    it.toObject<Marcador>()?.copy(id = it.id)
                }

                /**
                 * Se llama a [cuandoCambia] con la lista de marcadores.
                 */
                cuandoCambia(lista)
            }
    }

    fun agregarMarcador(marcador: Marcador, onComplete: (Boolean) -> Unit) {
        val docRef = db.collection("marcadores").document()
        val marcadorConId = marcador.copy(id = docRef.id)

        docRef.set(marcadorConId).addOnSuccessListener { onComplete(true) }
            .addOnFailureListener { onComplete(false) }
    }


}