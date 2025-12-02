package com.example.apptorneosajedrez.ui.login

import android.app.Activity
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

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by lazy {
        ViewModelProvider(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        setupListeners()
        setupObservers()
    }

    private fun setupListeners() {

        binding.username.addTextChangedListener {
            onCredentialsChanged()
        }

        binding.password.apply {
            addTextChangedListener {
                onCredentialsChanged()
            }

            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    performLogin()
                }
                false
            }
        }

        binding.btnLogin.setOnClickListener {
            binding.loading.visibility = android.view.View.VISIBLE
            performLogin()
        }

        binding.registerNow.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }
    }

    private fun setupObservers() {
        loginViewModel.loginFormState.observe(this, Observer { state ->
            state ?: return@Observer

            binding.btnLogin.isEnabled = state.isDataValid
            binding.username.error = state.usernameError?.let { getString(it) }
            binding.password.error = state.passwordError?.let { getString(it) }
        })

        loginViewModel.loginResult.observe(this, Observer { result ->
            result ?: return@Observer

            binding.loading.visibility = android.view.View.GONE

            result.error?.let { showLoginFailed(it) }
            result.success?.let { updateUiWithUser(it) }

            setResult(Activity.RESULT_OK)
            finish()
        })
    }

    private fun onCredentialsChanged() {
        val email = binding.username.text.toString()
        val password = binding.password.text.toString()
        loginViewModel.loginDataChanged(email, password)
    }

    private fun performLogin() {
        val email = binding.username.text.toString()
        val password = binding.password.text.toString()
        loginViewModel.login(email, password)
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        Toast.makeText(
            applicationContext,
            "$welcome ${model.displayName}",
            Toast.LENGTH_LONG
        ).show()
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun showLoginFailed(@StringRes errorRes: Int) {
        Toast.makeText(applicationContext, errorRes, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
    }
}
