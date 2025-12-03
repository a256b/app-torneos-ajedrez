// RegisterActivity.kt
package com.example.apptorneosajedrez.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import com.example.apptorneosajedrez.databinding.ActivityRegisterBinding
import com.example.apptorneosajedrez.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private val registerViewModel: RegisterViewModel by lazy {
        ViewModelProvider(this, RegisterViewModelFactory())
            .get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupListeners()
    }


    private fun setupObservers() {
        registerViewModel.registerFormState.observe(this) { state ->
            state ?: return@observe

            binding.btnRegister.isEnabled = state.isDataValid

            state.fullNameError?.let { binding.fullName.error = getString(it) }
            state.emailError?.let { binding.username.error = getString(it) }
            state.passwordError?.let { binding.password.error = getString(it) }
            state.confirmPasswordError?.let { binding.confirmPassword.error = getString(it) }
        }

        registerViewModel.registerResult.observe(this) { result ->
            result ?: return@observe

            binding.loading.visibility = if (result.loading) View.VISIBLE else View.GONE

            result.error?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }

            result.success?.let { user ->
                Toast.makeText(this, "Usuario creado: ${user.fullName}", Toast.LENGTH_LONG).show()
                // aquí podrías ir a MainActivity, cerrar esta Activity, etc.
                // startActivity(Intent(this, MainActivity::class.java))
                // finish()
            }
        }
    }

    private fun setupListeners() {
        // Cada vez que cambia un campo, se valida el formulario
        val watcher: (String) -> Unit = {
            registerViewModel.registerDataChanged(
                fullName = binding.fullName.text.toString(),
                email = binding.username.text.toString(),
                password = binding.password.text.toString(),
                confirmPassword = binding.confirmPassword.text.toString()
            )
        }

        binding.fullName.addTextChangedListener { watcher(it.toString()) }
        binding.username.addTextChangedListener { watcher(it.toString()) }
        binding.password.addTextChangedListener { watcher(it.toString()) }
        binding.confirmPassword.addTextChangedListener { watcher(it.toString()) }

        binding.btnRegister.setOnClickListener {
            val fullName = binding.fullName.text.toString().trim()
            val email = binding.username.text.toString().trim()
            val password = binding.password.text.toString()
            val confirmPassword = binding.confirmPassword.text.toString()

            registerViewModel.register(
                fullName = fullName,
                email = email,
                password = password,
                confirmPassword = confirmPassword
            )
        }

        binding.loginNow.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }
}
