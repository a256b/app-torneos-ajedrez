package com.example.apptorneosajedrez.data.model

import com.example.apptorneosajedrez.ui.login.LoggedInUserView

/**
 * Authentication result : success (user details) or error message.
 */
data class LoginResult(
    val success: LoggedInUserView? = null,
    val error: Int? = null
)