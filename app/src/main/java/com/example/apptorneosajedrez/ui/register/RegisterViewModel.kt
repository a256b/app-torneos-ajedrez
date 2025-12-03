package com.example.apptorneosajedrez.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apptorneosajedrez.R
import com.example.apptorneosajedrez.data.AuthRepository
import com.example.apptorneosajedrez.data.Result
import com.example.apptorneosajedrez.model.RegisterFormState
import com.example.apptorneosajedrez.model.RegisterResult
import android.util.Patterns

class RegisterViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _registerForm = MutableLiveData<RegisterFormState>()
    val registerFormState: LiveData<RegisterFormState> = _registerForm

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    fun register(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        registerDataChanged(fullName, email, password, confirmPassword)
        val form = _registerForm.value
        if (form?.isDataValid != true) return

        _registerResult.value = RegisterResult(loading = true)

        val defaultUserType = "Aficionado"

        authRepository.registerUser(
            fullName = fullName,
            email = email,
            password = password,
            userType = defaultUserType
        ) { result ->
            when (result) {
                is Result.Success -> _registerResult.postValue(RegisterResult(success = result.data))
                is Result.Error -> _registerResult.postValue(
                    RegisterResult(error = result.exception.message ?: "Error al registrar")
                )
            }
        }
    }


    fun registerDataChanged(
        fullName: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        var fullNameError: Int? = null
        var emailError: Int? = null
        var passwordError: Int? = null
        var confirmPasswordError: Int? = null

        if (fullName.isBlank()) {
            fullNameError = R.string.invalid_full_name
        }

        if (!isEmailValid(email)) {
            emailError = R.string.invalid_email
        }

        if (!isPasswordValid(password)) {
            passwordError = R.string.invalid_password
        }

        if (confirmPassword != password) {
            confirmPasswordError = R.string.passwords_not_match
        }

        val isValid = fullNameError == null &&
                emailError == null &&
                passwordError == null &&
                confirmPasswordError == null

        _registerForm.value = RegisterFormState(
            fullNameError = fullNameError,
            emailError = emailError,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError,
            isDataValid = isValid
        )
    }

    private fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordRegex = Regex(
            pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$"
        )
        return passwordRegex.matches(password)
    }
}
