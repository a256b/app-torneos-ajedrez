package com.example.apptorneosajedrez.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.apptorneosajedrez.MainActivity
import com.example.apptorneosajedrez.R
import com.example.apptorneosajedrez.databinding.ActivityLoginBinding
import com.example.apptorneosajedrez.ui.register.RegisterActivity



import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope

import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.credentials.CustomCredential

import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException

import kotlinx.coroutines.launch
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    // ✅ Ahora usamos LoginViewModelFactory que crea AuthRepository internamente
    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory()
    }

    private lateinit var credentialManager: CredentialManager
    private lateinit var googleIdOption: GetGoogleIdOption
    private lateinit var credentialRequest: GetCredentialRequest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCredentialManager()
        setupUi()
        setupObservers()
    }

    private fun setupCredentialManager() {
        credentialManager = CredentialManager.create(this)

        googleIdOption = GetGoogleIdOption.Builder()
            // IMPORTANTE: este es tu "default_web_client_id" (web client id)
            .setServerClientId(getString(R.string.default_web_client_id))
            // true = solo cuentas ya autorizadas, false = permite crear nuevas
            .setFilterByAuthorizedAccounts(false)
            .build()

        credentialRequest = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()
    }

    private fun setupUi() {
        // Tu login tradicional (email/contraseña) seguiría igual aquí…

        binding.btnLoginGoogle.setOnClickListener {
            signInWithGoogle()
        }
    }

    private fun setupObservers() {
        // Observas loginResult como ya venías haciendo
        loginViewModel.loginResult.observe(this) { result ->
            result?.success?.let { loggedInUserView ->
                Toast.makeText(
                    this,
                    getString(R.string.welcome) + " " + loggedInUserView.displayName,
                    Toast.LENGTH_SHORT
                ).show()
                // Ir a MainActivity o lo que corresponda
                // startActivity(Intent(this, MainActivity::class.java))
                // finish()
            }

            result?.error?.let {
                Toast.makeText(this, getString(it), Toast.LENGTH_SHORT).show()
            }
        }
    }

    // ✅ Flujo moderno: pedir credenciales a Credential Manager
    private fun signInWithGoogle() {
        lifecycleScope.launch {
            try {
                val result = credentialManager.getCredential(
                    context = this@LoginActivity,
                    request = credentialRequest
                )

                val credential = result.credential

                if (credential is CustomCredential &&
                    credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
                ) {
                    // Extraer el token de Google
                    val googleIdTokenCredential =
                        GoogleIdTokenCredential.createFrom(credential.data)
                    val idToken = googleIdTokenCredential.idToken

                    // Enviar token al ViewModel
                    loginViewModel.loginWithGoogleIdToken(idToken)
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "La credencial no es de tipo Google ID Token",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: GetCredentialException) {
                // Usuario canceló, no hay credenciales, etc.
                Toast.makeText(
                    this@LoginActivity,
                    "No se pudo obtener la credencial: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: GoogleIdTokenParsingException) {
                Toast.makeText(
                    this@LoginActivity,
                    "Error al procesar el token de Google",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: Exception) {
                Toast.makeText(
                    this@LoginActivity,
                    "Error inesperado: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
