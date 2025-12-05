package com.example.apptorneosajedrez.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apptorneosajedrez.data.AuthRepository
import com.example.apptorneosajedrez.model.AppUser
import kotlinx.coroutines.launch

data class RegisterUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val registeredUser: AppUser? = null
)

class RegisterViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableLiveData(RegisterUiState())
    val uiState: LiveData<RegisterUiState> = _uiState

    fun register(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String,
        userType: String
    ) {
        if (fullName.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            _uiState.value = RegisterUiState(
                errorMessage = "Todos los campos son obligatorios"
            )
            return
        }

        if (password != confirmPassword) {
            _uiState.value = RegisterUiState(
                errorMessage = "Las contraseñas no coinciden"
            )
            return
        }

        _uiState.value = _uiState.value?.copy(isLoading = true, errorMessage = null)

        viewModelScope.launch {
            try {
                val user = authRepository.registerWithEmail(
                    fullName = fullName,
                    email = email,
                    password = password,
                    userType = userType
                )
                _uiState.value = RegisterUiState(
                    isLoading = false,
                    registeredUser = user
                )
            } catch (e: Exception) {
                _uiState.value = RegisterUiState(
                    isLoading = false,
                    errorMessage = e.message ?: "Error al registrar usuario"
                )
            }
        }
    }
}
