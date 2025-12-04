package com.example.apptorneosajedrez.ui.login

import androidx.lifecycle.*
import android.util.Patterns
import com.example.apptorneosajedrez.data.Result
import com.example.apptorneosajedrez.R
import com.example.apptorneosajedrez.data.model.LoginFormState
import com.example.apptorneosajedrez.data.model.LoginResult
import kotlinx.coroutines.launch
import java.io.IOException

import androidx.lifecycle.*
import com.example.apptorneosajedrez.data.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            when (val result = authRepository.loginWithEmailAndPassword(email, password)) {
                is Result.Success -> {
                    val user = result.data
                    _loginResult.value = LoginResult(
                        success = LoggedInUserView(
                            displayName = user.fullName ?: user.email ?: "Usuario"
                        )
                    )
                }
                is Result.Error -> {
                    _loginResult.value = LoginResult(error = R.string.login_failed)
                }
            }
        }
    }

    fun loginWithGoogleIdToken(idToken: String) {
        viewModelScope.launch {
            when (val result = authRepository.loginWithGoogleIdToken(idToken)) {
                is Result.Success -> {
                    val user = result.data
                    _loginResult.value = LoginResult(
                        success = LoggedInUserView(
                            displayName = user.fullName ?: user.email ?: "Usuario"
                        )
                    )
                }
                is Result.Error -> {
                    _loginResult.value = LoginResult(error = R.string.login_failed)
                }
            }
        }
    }


    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(username).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 1
    }
}
