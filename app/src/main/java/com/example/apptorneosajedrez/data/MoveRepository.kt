package com.example.apptorneosajedrez.data

import com.example.apptorneosajedrez.model.Move
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MoveRepository {
    private val db = FirebaseFirestore.getInstance()
    private val movesRef = db.collection("moves")

    fun sendMove(notation: String) {
        val user = FirebaseAuth.getInstance().currentUser ?: return
        val move = Move(
            notation = notation,
            timestamp = System.currentTimeMillis(),
            userId = user.uid
        )
        movesRef.add(move)
    }

    fun listenMoves(onUpdate: (List<Move>) -> Unit) {
        movesRef
            .orderBy("timestamp", Query.Direction.ASCENDING)
            .addSnapshotListener { snaps, e ->
                if (e != null) return@addSnapshotListener
                val list = snaps
                    ?.documents
                    ?.mapNotNull { it.toObject(Move::class.java) }
                    ?: emptyList()
                onUpdate(list)
            }
    }
}
