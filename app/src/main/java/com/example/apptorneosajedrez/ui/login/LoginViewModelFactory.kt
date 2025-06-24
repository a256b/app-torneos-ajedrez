package com.example.apptorneosajedrez.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apptorneosajedrez.data.LoginRepository

/**
 * ViewModelProvider.Factory para rhorinstanciar LoginViewModel
 * usando el LoginRepository que se conecta a Firebase Auth.
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            val repository = LoginRepository()
            return LoginViewModel(loginRepository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
