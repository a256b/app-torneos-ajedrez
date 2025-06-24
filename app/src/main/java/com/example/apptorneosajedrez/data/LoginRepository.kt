package com.example.apptorneosajedrez.data

import com.example.apptorneosajedrez.data.model.LoggedInUser
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class LoginRepository(
    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
) {

    var user: LoggedInUser? = firebaseAuth.currentUser?.toLoggedInUser()
        private set

    val isLoggedIn: Boolean
        get() = firebaseAuth.currentUser != null

    suspend fun login(email: String, password: String): Result<LoggedInUser> {
        return try {
            val authResult = firebaseAuth
                .signInWithEmailAndPassword(email, password)
                .await()
            val fUser = authResult.user
                ?: throw Exception("Usuario nulo tras autenticación")
            val loggedInUser = fUser.toLoggedInUser()
            user = loggedInUser
            Result.Success(loggedInUser)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    fun logout() {
        firebaseAuth.signOut()
        user = null
    }

    private fun com.google.firebase.auth.FirebaseUser.toLoggedInUser() =
        LoggedInUser(
            userId = uid,
            displayName = displayName ?: email.orEmpty()
        )
}
