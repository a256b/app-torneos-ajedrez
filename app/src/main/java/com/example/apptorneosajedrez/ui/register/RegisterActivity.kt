package com.example.apptorneosajedrez.ui.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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

        setupUserTypeSpinner()
        setupObservers()
        setupListeners()
    }

    private fun setupUserTypeSpinner() {
        // Ejemplo simple de tipos de usuario
        val userTypes = listOf("player", "organizer", "admin")
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            userTypes
        )
        binding.userTypeSpinner.adapter = adapter
    }

    private fun setupObservers() {
        registerViewModel.uiState.observe(this, Observer { state ->
            binding.loading.visibility = if (state.isLoading) View.VISIBLE else View.GONE

            state.errorMessage?.let { msg ->
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }

            state.registeredUser?.let {
                Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
                // Vuelvo al login
                val intent = Intent(this, LoginActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        })
    }

    private fun setupListeners() = with(binding) {
        btnRegister.setOnClickListener {
            performRegister()
        }

        confirmPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                performRegister()
                true
            } else {
                false
            }
        }

        haveAccount.setOnClickListener {
            finish() // vuelve al LoginActivity
        }
    }

    private fun performRegister() {
        val fullName = binding.fullName.text.toString().trim()
        val email = binding.username.text.toString().trim()
        val password = binding.password.text.toString()
        val confirmPassword = binding.confirmPassword.text.toString()
        val userType = binding.userTypeSpinner.selectedItem?.toString() ?: "player"

        registerViewModel.register(
            fullName = fullName,
            email = email,
            password = password,
            confirmPassword = confirmPassword,
            userType = userType
        )
    }
}
