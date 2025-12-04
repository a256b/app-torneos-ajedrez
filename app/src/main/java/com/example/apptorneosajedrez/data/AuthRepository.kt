package com.example.apptorneosajedrez.data

import com.example.apptorneosajedrez.model.AppUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
//import kotlin.coroutines.resumeWithException
//
//import com.example.apptorneosajedrez.data.Result

class AuthRepository(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance(),
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance(),

) {


        fun registerUser(
            fullName: String,
            email: String,
            password: String,
            userType: String,
            callback: (Result<AppUser>) -> Unit
        ) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        callback(Result.Error(task.exception ?: Exception("Error creando usuario")))
                        return@addOnCompleteListener
                    }

                    val firebaseUser = firebaseAuth.currentUser
                    val uid = firebaseUser?.uid

                    if (uid == null) {
                        callback(Result.Error(Exception("No se pudo obtener el UID del usuario")))
                        return@addOnCompleteListener
                    }

                    val appUser = AppUser(
                        uid = uid,
                        fullName = fullName,
                        email = email,
                        userType = userType,
                        provider = "PASSWORD"
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
    // Login tradicional por email/contraseña
    suspend fun loginWithEmailAndPassword(email: String, password: String): Result<AppUser> =
        suspendCancellableCoroutine { cont ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener { authResult ->
                    val user = authResult.user
                    if (user != null) {
                        val appUser = AppUser(
                            uid = user.uid,
                            fullName = user.displayName,
                            email = user.email,
                            provider = "PASSWORD"
                        )
                        cont.resume(Result.Success(appUser))
                    } else {
                        cont.resume(Result.Error(Exception("Usuario nulo después de login con email.")))
                    }
                }
                .addOnFailureListener { e ->
                    if (cont.isActive) {
                        cont.resume(Result.Error(e))
                    }
                }
        }

    // Login con Google usando idToken
    suspend fun loginWithGoogleIdToken(idToken: String): Result<AppUser> =
        suspendCancellableCoroutine { cont ->
            val credential = GoogleAuthProvider.getCredential(idToken, null)

            firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener { authResult ->
                    val user = authResult.user
                    if (user != null) {
                        val appUser = AppUser(
                            uid = user.uid,
                            fullName = user.displayName,
                            email = user.email,
                            provider = "GOOGLE"
                        )
                        cont.resume(Result.Success(appUser))
                    } else {
                        cont.resume(Result.Error(Exception("Usuario nulo después de autenticarse con Google.")))
                    }
                }
                .addOnFailureListener { e ->
                    if (cont.isActive) {
                        cont.resume(Result.Error(e))
                    }
                }
        }
}
