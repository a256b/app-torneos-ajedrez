package com.example.apptorneosajedrez.data

import com.example.apptorneosajedrez.model.AppUser
import com.example.apptorneosajedrez.data.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) {

    fun registerUser(
        fullName: String,
        email: String,
        password: String,
        userType: String,
        callback: (Result<AppUser>) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    callback(Result.Error(task.exception ?: Exception("Error creando usuario")))
                    return@addOnCompleteListener
                }

                val firebaseUser = auth.currentUser
                val uid = firebaseUser?.uid

                if (uid == null) {
                    callback(Result.Error(Exception("No se pudo obtener el UID del usuario")))
                    return@addOnCompleteListener
                }

                val appUser = AppUser(
                    uid = uid,
                    fullName = fullName,
                    email = email,
                    userType = userType
                )

                // Guardar en colección "users" (documento con id = uid)
                db.collection("usuarios")
                    .document(uid)
                    .set(appUser)
                    .addOnSuccessListener {
                        callback(Result.Success(appUser))
                    }
                    .addOnFailureListener { e ->
                        callback(Result.Error(e))
                    }
            }
    }
}
