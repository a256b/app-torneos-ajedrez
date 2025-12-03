package com.example.apptorneosajedrez.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apptorneosajedrez.data.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegisterViewModelFactory : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            val auth = FirebaseAuth.getInstance()
            val db = FirebaseFirestore.getInstance()
            val repository = AuthRepository(auth, db)
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
