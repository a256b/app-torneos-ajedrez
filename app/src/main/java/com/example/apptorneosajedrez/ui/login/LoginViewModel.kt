package com.example.apptorneosajedrez.ui.login

import androidx.lifecycle.*
import android.util.Patterns
import com.example.apptorneosajedrez.data.LoginRepository
import com.example.apptorneosajedrez.data.Result
import com.example.apptorneosajedrez.R
import com.example.apptorneosajedrez.data.model.LoginFormState
import com.example.apptorneosajedrez.data.model.LoginResult
import kotlinx.coroutines.launch
import java.io.IOException

class LoginViewModel(
    private val loginRepository: LoginRepository
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    fun login(email: String, password: String) {
        // Opcional: podrías exponer un LiveData<Boolean> para mostrar un ProgressBar
        viewModelScope.launch {
            try {
                when (val result = loginRepository.login(email, password)) {
                    is Result.Success -> {
                        val displayName = result.data.displayName
                        _loginResult.value = LoginResult(
                            success = LoggedInUserView(displayName = displayName)
                        )
                    }

                    is Result.Error -> {
                        _loginResult.value = LoginResult(error = R.string.login_failed)
                    }
                }
            } catch (e: IOException) {
                _loginResult.value = LoginResult(error = R.string.login_failed)
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
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}
